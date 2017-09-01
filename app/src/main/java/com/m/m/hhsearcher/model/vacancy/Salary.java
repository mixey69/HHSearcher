
package com.m.m.hhsearcher.model.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Salary {

    @SerializedName("to")
    @Expose
    public Object to;
    @SerializedName("from")
    @Expose
    public Integer from;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("gross")
    @Expose
    public Boolean gross;

}
