
package com.m.m.hhsearcher.model.vacancy_item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Address {

    @SerializedName("building")
    @Expose
    public String building;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("description")
    @Expose
    public Object description;
    @SerializedName("metro")
    @Expose
    public Metro metro;
    @SerializedName("metro_stations")
    @Expose
    public List<MetroStation> metroStations = null;
    @SerializedName("raw")
    @Expose
    public Object raw;
    @SerializedName("street")
    @Expose
    public String street;
    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("lng")
    @Expose
    public Double lng;
    @SerializedName("id")
    @Expose
    public String id;

}
