
package com.m.m.hhsearcher.model.vacancy_item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Snippet {

    @SerializedName("requirement")
    @Expose
    public String requirement;
    @SerializedName("responsibility")
    @Expose
    public String responsibility;

    @Override
    public String toString() {
        String result = requirement + responsibility;
        result = result.replaceAll("<(/|)highlighttext>","");
        return result;
    }
}
