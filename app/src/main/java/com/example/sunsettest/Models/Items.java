package com.example.sunsettest.Models;

import com.google.gson.annotations.SerializedName;
import android.os.Parcel;
import android.os.Parcelable;

public class Items implements Parcelable {

    @SerializedName("id")
    int id;
    @SerializedName("order")
    int order;
    @SerializedName("name")
    String name;
    @SerializedName("address")
    String address;
    @SerializedName("description")
    String description;
    @SerializedName("lat")
    String lat;
    @SerializedName("lon")
    String lon;
    @SerializedName("type")
    String type;
    @SerializedName("capacity")
    int capacity;
    @SerializedName("bikes")
    int bikes;
    @SerializedName("places")
    String places;
    @SerializedName("picture")
    String picture;
    @SerializedName("bikes_state")
    String bikes_state;
    @SerializedName("places_state")
    String places_state;
    @SerializedName("closed")
    int closed;
    @SerializedName("cdo")
    int cdo;


    public Items(int id, int order, String name, String address, String description, String lat, String lon, String type, int capacity, int bikes, String places, String picture, String bikes_state, String places_state, int closed, int cdo) {
        this.id = id;
        this.order = order;
        this.name = name;
        this.address = address;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.type = type;
        this.capacity = capacity;
        this.bikes = bikes;
        this.places = places;
        this.picture = picture;
        this.bikes_state = bikes_state;
        this.places_state = places_state;
        this.closed = closed;
        this.cdo = cdo;
    }

    public int getId() {
        return id;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBikes() {
        return bikes;
    }

    public String getPlaces() {
        return places;
    }

    public String getPicture() {
        return picture;
    }

    public String getBikes_state() {
        return bikes_state;
    }

    public String getPlaces_state() {
        return places_state;
    }

    public int getClosed() {
        return closed;
    }

    public int getCdo() {
        return cdo;
    }

    protected Items(Parcel in) {
        id = in.readInt();
        order = in.readInt();
        name = in.readString();
        address = in.readString();
        description = in.readString();
        lat = in.readString();
        lon = in.readString();
        type = in.readString();
        capacity = in.readInt();
        bikes = in.readInt();
        places = in.readString();
        picture = in.readString();
        bikes_state = in.readString();
        places_state = in.readString();
        closed = in.readInt();
        cdo = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(order);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(description);
        dest.writeString(lat);
        dest.writeString(lon);
        dest.writeString(type);
        dest.writeInt(capacity);
        dest.writeInt(bikes);
        dest.writeString(places);
        dest.writeString(picture);
        dest.writeString(bikes_state);
        dest.writeString(places_state);
        dest.writeInt(closed);
        dest.writeInt(cdo);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Items> CREATOR = new Parcelable.Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };
}