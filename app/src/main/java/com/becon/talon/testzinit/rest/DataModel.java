package com.becon.talon.testzinit.rest;

/**
 * Created by Админ on 16.06.2016.
 */
public class DataModel {
    private int id;
    private String title;
    private String thumbnail;

    @Override
    public String toString() {
        return "DataModel{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
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

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public DataModel(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.thumbnail = image;
    }
}
