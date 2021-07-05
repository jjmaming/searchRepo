package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class GitHubSearchItemModel {
    @SerializedName("id")
    double id;
    @SerializedName("name")
    String name;
    @SerializedName("full_name")
    String fullName;
    @SerializedName("description")
    String description;
    @SerializedName("html_url")
    String url;

    public double getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}
