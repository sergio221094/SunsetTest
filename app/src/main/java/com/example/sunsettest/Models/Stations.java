package com.example.sunsettest.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Stations {

    @SerializedName("id")
    String id;
    @SerializedName("name")
    String name;
    @SerializedName("desc")
    String desc;
    @SerializedName("items")
    List<Items> items;

    public Stations(String id, String name, String desc, List<Items> items) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public List<Items> getItems() {
        return items;
    }
}
