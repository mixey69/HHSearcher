
package com.m.m.hhsearcher.model.vacancy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Vacancy {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("branded_description")
    @Expose
    public String brandedDescription;
    @SerializedName("key_skills")
    @Expose
    public List<KeySkill> keySkills = null;
    @SerializedName("schedule")
    @Expose
    public Schedule schedule;
    @SerializedName("accept_handicapped")
    @Expose
    public Boolean acceptHandicapped;
    @SerializedName("experience")
    @Expose
    public Experience experience;
    @SerializedName("address")
    @Expose
    public Address address;
    @SerializedName("alternate_url")
    @Expose
    public String alternateUrl;
    @SerializedName("apply_alternate_url")
    @Expose
    public String applyAlternateUrl;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("department")
    @Expose
    public Department department;
    @SerializedName("employment")
    @Expose
    public Employment employment;
    @SerializedName("salary")
    @Expose
    public Salary salary;
    @SerializedName("archived")
    @Expose
    public Boolean archived;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("area")
    @Expose
    public Area area;
    @SerializedName("published_at")
    @Expose
    public String publishedAt;
    @SerializedName("employer")
    @Expose
    public Employer employer;
    @SerializedName("response_letter_required")
    @Expose
    public Boolean responseLetterRequired;
    @SerializedName("type")
    @Expose
    public Type type;
    @SerializedName("response_url")
    @Expose
    public Object responseUrl;
    @SerializedName("test")
    @Expose
    public Test test;
    @SerializedName("specializations")
    @Expose
    public List<Specialization> specializations = null;
    @SerializedName("contacts")
    @Expose
    public Contacts contacts;
    @SerializedName("billing_type")
    @Expose
    public BillingType billingType;
    @SerializedName("allow_messages")
    @Expose
    public Boolean allowMessages;
    @SerializedName("premium")
    @Expose
    public Boolean premium;

}
