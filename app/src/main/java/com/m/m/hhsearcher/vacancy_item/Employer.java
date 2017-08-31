
package com.m.m.hhsearcher.vacancy_item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employer {

    @SerializedName("logo_urls")
    @Expose
    public LogoUrls logoUrls;
    @SerializedName("vacancies_url")
    @Expose
    public String vacanciesUrl;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("alternate_url")
    @Expose
    public String alternateUrl;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("trusted")
    @Expose
    public Boolean trusted;

}
