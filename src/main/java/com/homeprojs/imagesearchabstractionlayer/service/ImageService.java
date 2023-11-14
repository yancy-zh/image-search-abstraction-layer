package com.homeprojs.imagesearchabstractionlayer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.homeprojs.imagesearchabstractionlayer.model.DummyImage;
import com.homeprojs.imagesearchabstractionlayer.model.Image;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
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

    public ImageService(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
    }

    public List<Image> queryImage(String keyword) throws JsonProcessingException {
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
            String typeStr = jsonElement.getAsJsonObject().get("type").getAsString();
            if ("organic".equals(typeStr)) {
                try {
                    JsonArray imgItems = jsonElement.getAsJsonObject().getAsJsonArray("images");
                    for (JsonElement jsonElement1 : imgItems) {
                        Image imageInfo = objectMapper.readValue(jsonElement1.getAsJsonObject().toString(), Image.class);
                        images.add(imageInfo);
                    }

                } catch (ClassCastException e) {
                    logger.info("err while dealing with item type: ");
                }
            }
        }
        return images;
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
}
