
package com.m.m.hhsearcher.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metro {

    @SerializedName("line_name")
    @Expose
    public String lineName;
    @SerializedName("station_id")
    @Expose
    public String stationId;
    @SerializedName("line_id")
    @Expose
    public String lineId;
    @SerializedName("lat")
    @Expose
    public Double lat;
    @SerializedName("station_name")
    @Expose
    public String stationName;
    @SerializedName("lng")
    @Expose
    public Double lng;

}
