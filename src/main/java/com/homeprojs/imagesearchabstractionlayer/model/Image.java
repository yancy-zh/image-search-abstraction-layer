package com.homeprojs.imagesearchabstractionlayer.model;

/**
 * @author Yao Zhang
 * @date 2023-11-06
 * # @apiNote
 */
public class Image {
    private String image_url;
    private String url;
    private String alt;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getImageUrl() {
        return image_url;
    }

    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
