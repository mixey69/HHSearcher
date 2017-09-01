
package com.m.m.hhsearcher.model.vacancy;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("street")
    @Expose
    public String street;
    @SerializedName("building")
    @Expose
    public String building;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("lng")
    @Expose
    public Double lng;
    @SerializedName("raw")
    @Expose
    public String raw;
    @SerializedName("metro_stations")
    @Expose
    public List<MetroStation> metroStations = null;

}
