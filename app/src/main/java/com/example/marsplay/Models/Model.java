package com.example.marsplay.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    private boolean expanded;
    @SerializedName("response")
    @Expose
    public Responseo reesponse;

    public Responseo getResponse() {
        return reesponse;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }
}
