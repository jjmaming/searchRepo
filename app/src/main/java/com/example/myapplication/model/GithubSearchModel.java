package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GithubSearchModel {
    @SerializedName("total_count")
    double totalCount;
    @SerializedName("incomplete_results")
    boolean incompleteResults;
    @SerializedName("items")
    List<GitHubSearchItemModel> items;

    public List<GitHubSearchItemModel> getItems() {
        return items;
    }
}
