package com.homeprojs.imagesearchabstractionlayer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.homeprojs.imagesearchabstractionlayer.exception.TempDataFileCantBeDeletedException;
import com.homeprojs.imagesearchabstractionlayer.model.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * @author Yao Zhang
 * @date 2023-11-07
 * # @apiNote
 */
@Service
public class ImageService {

    Logger logger = Logger.getLogger(ImageService.class.getSimpleName());
    private final RestTemplate restTemplate;
    private HttpHeaders header;
    private static final String TEMP_FILE = "myObject.txt";
    private RecentActivity recentActivity = new RecentActivity();

    public ImageService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Image> queryAllImages(String keyword) {
        recentActivity.openWriterStream();
        recentActivity.saveSearchString(keyword);
        String urlStr = "https://api.dataforseo.com/v3/serp/google/organic/live/advanced";
        header = new HttpHeaders();
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        header.add("Authorization", "Basic eWFuY3lwcmltZWRvb3JAZ21haWwuY29tOjFjODI0MWNlZDg4YWUzNzE=");
        HttpEntity<String> entity = new HttpEntity<>("[\n" +
                "{\n" +
                "\"language_name\": \"English (United Kingdom)\",\n" +
                "\"location_name\": \"London,England,United Kingdom\",\n" +
                "\"keyword\": \"" + keyword + "\"\n" +
                "}\n" +
                "]", header);
        ResponseEntity<String> response = restTemplate.exchange(urlStr, HttpMethod.POST, entity, String.class);
        JsonObject jsonObject = JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject();
        JsonArray tasks = jsonObject.getAsJsonArray("tasks");
        JsonObject temp = tasks.get(0).getAsJsonObject();
        JsonArray result = temp.getAsJsonArray("result");
        JsonArray items = result.get(0).getAsJsonObject().getAsJsonArray("items");
        List<Image> images = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (JsonElement jsonElement : items) {
            logger.info("in items loop");
            try {
                String typeStr = jsonElement.getAsJsonObject().get("type").getAsString();
                if ("organic".equals(typeStr) && !jsonElement.getAsJsonObject().getAsJsonArray("images").isJsonNull()) {
                    try {
                        JsonArray imgItems = jsonElement.getAsJsonObject().getAsJsonArray("images");
                        for (JsonElement jsonElement1 : imgItems) {
                            Image imageInfo = objectMapper.readValue(jsonElement1.getAsJsonObject().toString(), Image.class);
                            images.add(imageInfo);
                        }
                    } catch (ClassCastException | JsonProcessingException e) {
                        logger.info("err while parsing json: ");
                    }
                }
            } catch (ClassCastException e) {
                logger.info("err while getting Type attr");
            }

        }
        return images;
    }

    @Async
    public CompletableFuture<List<Image>> queryImage(String keyword) {
        String urlStr = "https://api.dataforseo.com/v3/serp/google/organic/live/advanced";
        header = new HttpHeaders();
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        header.add("Authorization", "Basic eWFuY3lwcmltZWRvb3JAZ21haWwuY29tOjFjODI0MWNlZDg4YWUzNzE=");
        HttpEntity<String> entity = new HttpEntity<>("[\n" +
                "{\n" +
                "\"language_name\": \"English (United Kingdom)\",\n" +
                "\"location_name\": \"London,England,United Kingdom\",\n" +
                "\"keyword\": \"" + keyword + "\"\n" +
                "}\n" +
                "]", header);
        ResponseEntity<String> response = restTemplate.exchange(urlStr, HttpMethod.POST, entity, String.class);
        JsonObject jsonObject = JsonParser.parseString(Objects.requireNonNull(response.getBody())).getAsJsonObject();
        JsonArray tasks = jsonObject.getAsJsonArray("tasks");
        JsonObject temp = tasks.get(0).getAsJsonObject();
        JsonArray result = temp.getAsJsonArray("result");
        JsonArray items = result.get(0).getAsJsonObject().getAsJsonArray("items");
        List<Image> images = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        for (JsonElement jsonElement : items) {
            logger.info("in items loop");
            try {
                String typeStr = jsonElement.getAsJsonObject().get("type").getAsString();
                if ("organic".equals(typeStr) && !jsonElement.getAsJsonObject().getAsJsonArray("images").isJsonNull()) {
                    try {
                        JsonArray imgItems = jsonElement.getAsJsonObject().getAsJsonArray("images");
                        for (JsonElement jsonElement1 : imgItems) {
                            Image imageInfo = objectMapper.readValue(jsonElement1.getAsJsonObject().toString(), Image.class);
                            images.add(imageInfo);
                        }
                    } catch (ClassCastException | JsonProcessingException e) {
                        logger.info("err while parsing json: ");
                    }
                }
            } catch (ClassCastException e) {
                logger.info("err while getting Type attr");
            }

        }

        try {
            Files.deleteIfExists(Paths.get(TEMP_FILE));
        } catch (IOException e) {
            throw new TempDataFileCantBeDeletedException(e.getMessage());
        }
        // save images to local storage
        WriterReader writerReader = new WriterReader();
        writerReader.writeToFile(images, TEMP_FILE);
        return CompletableFuture.completedFuture(images);
    }

    public List<Image> getImagesByPage(String keyword, int pageIdx) {
        recentActivity.openWriterStream();
        recentActivity.saveSearchString(keyword);
        recentActivity.closeWriter();
        long start = System.currentTimeMillis();
        this.queryImage(keyword);
        logger.info("request done, elapsed time: " + (System.currentTimeMillis() - start));
        WriterReader writerReader = new WriterReader();
        List<Image> images = writerReader.readToObj(TEMP_FILE);
        logger.info("read obj done, elapsed time: " + (System.currentTimeMillis() - start));
        Pagination<Image> myPagination = new Pagination<>(images, 10);
        return myPagination.getPaginated(pageIdx);
    }


    public List<DummyImage> getAllImages() {
        String urlstr = "https://jsonplaceholder.typicode.com/photos";
        header = new HttpHeaders();
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("body", header);
        ResponseEntity<String> response = restTemplate.exchange(urlstr, HttpMethod.GET, entity, String.class);
        DummyImage[] images = new Gson().fromJson(response.getBody(), DummyImage[].class);
        return Arrays.asList(images);
    }

    public List<String> getRecentTerms() {
        return recentActivity.getRecentTerms();
    }
}
