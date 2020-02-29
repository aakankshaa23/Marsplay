package com.example.marsplay.Models;

import com.example.marsplay.Models.Doc;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Responseo {

    @SerializedName("numFound")
    @Expose
     Integer numFound;
    @SerializedName("start")
    @Expose
     Integer start;
    @SerializedName("maxScore")
    @Expose
     Float maxScore;
    @SerializedName("docs")
    @Expose
     List<Doc> docs = null;

    public Integer getNumFound() {
        return numFound;
    }

    public void setNumFound(Integer numFound) {
        this.numFound = numFound;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Float getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Float maxScore) {
        this.maxScore = maxScore;
    }

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

}

