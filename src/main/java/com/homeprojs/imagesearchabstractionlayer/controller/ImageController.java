package com.homeprojs.imagesearchabstractionlayer.controller;

import com.homeprojs.imagesearchabstractionlayer.model.DummyImage;
import com.homeprojs.imagesearchabstractionlayer.model.Image;
import com.homeprojs.imagesearchabstractionlayer.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Level;
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

    @GetMapping("queryall/{keyword}")
    public List<Image> getImageByKeyword(@PathVariable("keyword") String keyword) {
        logger.log(Level.INFO, String.format("Responding with images for %s...", keyword));
        return this.imageService.queryAllImages(keyword);
    }

    @GetMapping("query/{keyword}")
    public List<Image> getImageByKeyword(@PathVariable("keyword") String keyword, @RequestParam int page) {
        logger.log(Level.INFO, String.format("Responding with images for %s, getting page: %d", keyword, page));
        List<Image> images = this.imageService.getImagesByPage(keyword, page);
        logger.info("arr size: " + images.size());
        return images;
    }

    @GetMapping("recent")
    public List<String> getRecentTerms() {
        return this.imageService.getRecentTerms();
    }

    @GetMapping("jsonholder")
    public List<DummyImage> getFromJsonHolder() {
        logger.info("Fetching all images... from Json holder");
        return this.imageService.getAllImages();
    }
}
