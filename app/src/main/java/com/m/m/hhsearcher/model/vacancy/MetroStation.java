
package com.m.m.hhsearcher.model.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetroStation {

    @SerializedName("station_id")
    @Expose
    public String stationId;
    @SerializedName("station_name")
    @Expose
    public String stationName;
    @SerializedName("line_id")
    @Expose
    public String lineId;
    @SerializedName("line_name")
    @Expose
    public String lineName;
    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("lng")
    @Expose
    public Double lng;

}
