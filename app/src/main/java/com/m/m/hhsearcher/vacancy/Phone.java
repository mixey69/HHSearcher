
package com.m.m.hhsearcher.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phone {

    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("number")
    @Expose
    public String number;
    @SerializedName("comment")
    @Expose
    public Object comment;

}
