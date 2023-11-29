package com.homeprojs.imagesearchabstractionlayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homeprojs.imagesearchabstractionlayer.model.Image;
import com.homeprojs.imagesearchabstractionlayer.model.Pagination;
import com.homeprojs.imagesearchabstractionlayer.model.WriterReader;
import com.homeprojs.imagesearchabstractionlayer.service.ImageService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaginationTest {
    Logger logger = Logger.getLogger(ImageService.class.getSimpleName());

    @Test
    void testGetPaginated1() {
        List<Integer> lsOfIntegers = new ArrayList<>();
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);

        Pagination<Integer> myPagination = new Pagination<>(lsOfIntegers);
        assertEquals(Arrays.asList(1, 1, 1), myPagination.getPaginated(1));
    }

    @Test
    void testGetPaginated2() {
        List<Integer> lsOfIntegers = new ArrayList<>();
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);

        Pagination<Integer> myPagination = new Pagination<>(lsOfIntegers);
        assertEquals(Arrays.asList(2, 2, 2), myPagination.getPaginated(2));
    }

    @Test
    void testGetPaginated3() {
        List<Integer> lsOfIntegers = new ArrayList<>();
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);

        Pagination<Integer> myPagination = new Pagination<>(lsOfIntegers);
        assertEquals(Arrays.asList(3, 3, 3), myPagination.getPaginated(3));
    }

    @Test
    void testGetPaginated4() {
        List<Integer> lsOfIntegers = new ArrayList<>();
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(1);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(2);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(3);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);
        lsOfIntegers.add(4);

        Pagination<Integer> myPagination = new Pagination<>(lsOfIntegers);
        assertEquals(Arrays.asList(4, 4, 4), myPagination.getPaginated(4));
    }

    @Test
    void testGetPaginatedWithJson() {
        WriterReader writerReader = new WriterReader();
        List<Image> lsOfImgs = writerReader.readToObj("myObject.txt");
        List<Image> lsPage1 = null;
        String page1 = "{\n" +
                "        \"url\": null,\n" +
                "        \"alt\": \"tesla model y from www.tesla.com\",\n" +
                "        \"type\": \"images_element\",\n" +
                "        \"image_url\": \"https://api.dataforseo.com/cdn/i/11281017-6940-0139-0000-57a09b9a4ba5:0\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"url\": null,\n" +
                "        \"alt\": \"tesla model y from www.tesla.com\",\n" +
                "        \"type\": \"images_element\",\n" +
                "        \"image_url\": \"https://api.dataforseo.com/cdn/i/11281017-6940-0139-0000-57a09b9a4ba5:1\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"url\": null,\n" +
                "        \"alt\": \"tesla model y from www.carwow.co.uk\",\n" +
                "        \"type\": \"images_element\",\n" +
                "        \"image_url\": \"https://api.dataforseo.com/cdn/i/11281017-6940-0139-0000-57a09b9a4ba5:2\"\n" +
                "    }";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            lsPage1 = Arrays.asList(objectMapper.readValue(page1, Image[].class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Pagination<Image> myPagination = new Pagination<>(lsOfImgs);
        assertEquals(myPagination.getPaginated(1), lsPage1);
    }
}
