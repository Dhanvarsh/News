package com.news.news.GroupModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.news.news.Models.Article;

import java.util.ArrayList;


public class Detail {
    @SerializedName("articles")
    @Expose
    private ArrayList<Data> data = null;
    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }





}
