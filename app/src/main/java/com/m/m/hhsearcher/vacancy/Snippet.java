
package com.m.m.hhsearcher.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snippet {

    @SerializedName("requirement")
    @Expose
    public String requirement;
    @SerializedName("responsibility")
    @Expose
    public String responsibility;

}
