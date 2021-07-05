package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.GitHubSearchItemModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<GitHubSearchItemModel> githubItemList;

    public SearchAdapter(Context context, List<GitHubSearchItemModel> githubItemList) {
        this.context = context;
        this.githubItemList = githubItemList;
    }

    public void setSearchAdapter(List<GitHubSearchItemModel> githubItemList) {
        this.githubItemList = githubItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        GitHubSearchItemModel item = githubItemList.get(position);
        holder.tvName.setText(String.valueOf(item.getName()));
        holder.tvDescription.setText(item.getDescription());
        holder.container.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl()));
            context.startActivity(browserIntent);
        });
    }

    @Override
    public int getItemCount() {
        if (githubItemList != null && !githubItemList.isEmpty()) {
            return githubItemList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDescription;
        RelativeLayout container;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            container = itemView.findViewById(R.id.container);
        }
    }
}
