package com.example.myapplication.viewmodel;
import android.app.Dialog;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.model.GitHubSearchItemModel;
import com.example.myapplication.model.GithubSearchModel;
import com.example.myapplication.network.ApiService;
import com.example.myapplication.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {
    private MutableLiveData<List<GitHubSearchItemModel>> githubList;

    public SearchViewModel() {
        githubList = new MutableLiveData<>();

    }

    public MutableLiveData<List<GitHubSearchItemModel>> getSearchObserver(){
        return  githubList;
    }

    public void Search(String searchValue, Dialog dialog){
        ApiService apiService = RetroInstance.getRetroClient().create(ApiService.class);
        Call<GithubSearchModel> call = apiService.searchGithubRepo(searchValue);
        call.enqueue(new Callback<GithubSearchModel>() {
            @Override
            public void onResponse(Call<GithubSearchModel> call, Response<GithubSearchModel> response) {
                if(response.body()!=null){
                    githubList.postValue(response.body().getItems());
                }
                dialog.cancel();
            }

            @Override
            public void onFailure(Call<GithubSearchModel> call, Throwable t) {
                githubList.postValue(null);
                dialog.cancel();
            }
        });
    }
}
