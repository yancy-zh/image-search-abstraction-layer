package com.homeprojs.imagesearchabstractionlayer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.homeprojs.imagesearchabstractionlayer.model.DummyImage;
import com.homeprojs.imagesearchabstractionlayer.model.Image;
import com.homeprojs.imagesearchabstractionlayer.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author Yao Zhang
 * @date 2023-11-07
 * # @apiNote
 */
@RestController
@RequestMapping("images")
public class ImageController {
    /* retrieve images by keyword */
    @Autowired
    private ImageService imageService;

    Logger logger = Logger.getLogger(ImageController.class.getSimpleName());

    @GetMapping("hello")
    public String getHello() {
        return "Hello World!";
    }

    @GetMapping("query/{keyword}")
    public List<Image> getImageByKeyword(@PathVariable("keyword") String keyword) throws JsonProcessingException {
        logger.info("Responding with images for " + keyword + "...");
        return this.imageService.queryImage(keyword);
    }

    @GetMapping("/query/findPaginated")
    public ResponseEntity<Map<String, Object>> getImageByPagination(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        return imageService.findPaginated(paging);
    }

    @GetMapping("jsonholder")
    public List<DummyImage> getFromJsonHolder() {
        logger.info("Fetching all images... from Json holder");
        return this.imageService.getAllImages();
    }
}
