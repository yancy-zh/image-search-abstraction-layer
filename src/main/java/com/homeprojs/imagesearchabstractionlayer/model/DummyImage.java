package com.homeprojs.imagesearchabstractionlayer.model;

/**
 * @author Yao Zhang
 * @date 2023-11-09
 * # @apiNote
 */
public class DummyImage {
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    private int albumId;
    private int id;
    private String title;
    private String url;
    private String thumbnailUrl;
}
