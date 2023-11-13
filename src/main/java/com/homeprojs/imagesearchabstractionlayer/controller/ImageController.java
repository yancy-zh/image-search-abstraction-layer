package com.homeprojs.imagesearchabstractionlayer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.homeprojs.imagesearchabstractionlayer.model.DummyImage;
import com.homeprojs.imagesearchabstractionlayer.model.Image;
import com.homeprojs.imagesearchabstractionlayer.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.System.*;

/**
 * @author Yao Zhang
 * @date 2023-11-07
 * # @apiNote
 */
@RestController
@RequestMapping("images")
public class ImageController {
    /* retrieve images by keyword */
    @Resource
    private ImageService imageService;
    Logger logger = Logger.getLogger(ImageController.class.getSimpleName());

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;

    }

    @GetMapping("hello")
    public String getHello() {
        return "Hello World!";
    }

    @GetMapping("query/{keyword}")
    public List<Image> getImageByKeyword(@PathVariable("keyword") String keyword) throws JsonProcessingException {
        logger.info("Responding with images for " + keyword + "...");
        return this.imageService.queryImage(keyword);
    }

    @GetMapping("jsonholder")
    public List<DummyImage> getFromJsonHolder() {
        logger.info("Fetching all images... from Json holder");
        return this.imageService.getAllImages();
    }
}
