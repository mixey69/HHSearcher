
package com.m.m.hhsearcher.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Employer {

    @SerializedName("logo_urls")
    @Expose
    public LogoUrls logoUrls;
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
    @SerializedName("blacklisted")
    @Expose
    public Boolean blacklisted;

}
