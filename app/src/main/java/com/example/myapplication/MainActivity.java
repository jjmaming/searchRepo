package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.SearchAdapter;
import com.example.myapplication.model.GitHubSearchItemModel;
import com.example.myapplication.viewmodel.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SearchViewModel searchViewModel;
    private SearchAdapter adapter;
    private Button btnSearch;
    private EditText etSeach;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        searchViewModel.getSearchObserver().observe(this, new Observer<List<GitHubSearchItemModel>>() {
            @Override
            public void onChanged(List<GitHubSearchItemModel> gitHubSearchItemModels) {
                adapter.setSearchAdapter(gitHubSearchItemModels);
            }
        });

    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvSearch);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        etSeach = (EditText) findViewById(R.id.etSearch);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new SearchAdapter(this, new ArrayList());
        recyclerView.setAdapter(adapter);
        btnSearch.setOnClickListener(view -> {
            if (dialog == null) {
                dialog = ProgressDialog.show(this, "",
                        "Loading. Please wait...", true);
            } else {
                dialog.show();
            }
            searchViewModel.Search(etSeach.getText().toString(), dialog);
        });
    }
}
