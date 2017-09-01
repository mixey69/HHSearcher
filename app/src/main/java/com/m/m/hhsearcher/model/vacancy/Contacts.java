
package com.m.m.hhsearcher.model.vacancy;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contacts {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phones")
    @Expose
    public List<Phone> phones = null;

}
