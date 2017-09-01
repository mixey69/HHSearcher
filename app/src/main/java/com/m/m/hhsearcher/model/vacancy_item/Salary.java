
package com.m.m.hhsearcher.model.vacancy_item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Salary {

    @SerializedName("to")
    @Expose
    public Integer to;
    @SerializedName("gross")
    @Expose
    public Boolean gross;
    @SerializedName("from")
    @Expose
    public Integer from;
    @SerializedName("currency")
    @Expose
    public String currency;

    @Override
    public String toString() {
        String result;
        if (to == null && from == null){
            result = "з/п не указана";
        }else if (to != null){
            result = "до " + to + " " + currency;
        }else {
            result = "от " + from + " " + currency;
        }
        return result;
    }
}
