
package com.m.m.hhsearcher.model.vacancy_item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item {

    @SerializedName("salary")
    @Expose
    public Salary salary;
    @SerializedName("snippet")
    @Expose
    public Snippet snippet;
    @SerializedName("archived")
    @Expose
    public Boolean archived;
    @SerializedName("premium")
    @Expose
    public Boolean premium;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("area")
    @Expose
    public Area area;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("alternate_url")
    @Expose
    public String alternateUrl;
    @SerializedName("apply_alternate_url")
    @Expose
    public String applyAlternateUrl;
    @SerializedName("relations")
    @Expose
    public List<Object> relations = null;
    @SerializedName("employer")
    @Expose
    public Employer employer;
    @SerializedName("response_letter_required")
    @Expose
    public Boolean responseLetterRequired;
    @SerializedName("published_at")
    @Expose
    public String publishedAt;
    @SerializedName("address")
    @Expose
    public Address address;
    @SerializedName("department")
    @Expose
    public Object department;
    @SerializedName("sort_point_distance")
    @Expose
    public Object sortPointDistance;
    @SerializedName("type")
    @Expose
    public Type type;
    @SerializedName("id")
    @Expose
    public String id;

}
