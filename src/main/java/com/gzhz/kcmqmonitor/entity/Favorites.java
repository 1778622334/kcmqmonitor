package com.gzhz.kcmqmonitor.entity;

public class Favorites {
    private String id;
    private String[] favorites;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getFavorites() {
        return favorites;
    }

    public void setFavorites(String[] favorites) {
        this.favorites = favorites;
    }
}
