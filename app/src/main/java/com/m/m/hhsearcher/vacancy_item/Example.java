
package com.m.m.hhsearcher.vacancy_item;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("clusters")
    @Expose
    public Object clusters;
    @SerializedName("items")
    @Expose
    public List<Item> items = null;
    @SerializedName("pages")
    @Expose
    public Integer pages;
    @SerializedName("arguments")
    @Expose
    public Object arguments;
    @SerializedName("found")
    @Expose
    public Integer found;
    @SerializedName("alternate_url")
    @Expose
    public String alternateUrl;
    @SerializedName("per_page")
    @Expose
    public Integer perPage;
    @SerializedName("page")
    @Expose
    public Integer page;

}
