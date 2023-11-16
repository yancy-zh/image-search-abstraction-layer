package com.homeprojs.imagesearchabstractionlayer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


/**
 * @author Yao Zhang
 * @date 2023-11-06
 * # @apiNote
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "imageUrl")
    @JsonProperty("image_url")
    private String imageUrl;
    @Column(name = "url")
    @JsonProperty("url")
    private String url;
    @Column(name = "alt")
    @JsonProperty("alt")
    private String alt;
    @Column(name = "type")
    @JsonProperty("type")
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

    public void setImageUrl(String image_url) {
        this.imageUrl = image_url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", url='" + url + '\'' +
                ", alt='" + alt + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
