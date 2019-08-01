package com.example.sunsettest.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {

    @SerializedName("stations")
    private List<Stations> Stations;

    @SerializedName("items")
    private List<Items> Items;

    public List<com.example.sunsettest.Models.Stations> getStations() {
        return Stations;
    }

    public List<com.example.sunsettest.Models.Items> getItems() {
        return Items;
    }

}
