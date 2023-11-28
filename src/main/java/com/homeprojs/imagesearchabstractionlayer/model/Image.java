package com.homeprojs.imagesearchabstractionlayer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yao Zhang
 * @date 2023-11-06
 * # @apiNote
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty
    private String url;
    @JsonProperty
    private String alt;
    @JsonProperty
    private String type;


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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }
}
