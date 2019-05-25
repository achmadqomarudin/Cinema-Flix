package com.example.achmadqomarudin.cinemaflix;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by Achmad Qomarudin on 04/12/2017.
 */

public class AdapterRecyclerView extends RecyclerView.Adapter {

    public Context context;
    public List<ModelData> data;
    public ViewHolder holderList;

    public AdapterRecyclerView(List<ModelData> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.row_item, parent, false);
        return holderList = new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holderList.tv_nama.setText(data.get(position).getTitle());

        Glide.with(context).load("https://image.tmdb.org/t/p/w500"+data.get(position).getPoster_path())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(holderList.iv_foto);

        Log.e("TAG", "keluar data : "+"https://image.tmdb.org/t/p/w500"+data.get(position).getBackdrop_path());

        holderList.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(context, DetailActivity.class);
                detail.putExtra("title", data.get(position).getTitle());
                detail.putExtra("img", "https://image.tmdb.org/t/p/w500"+data.get(position).getPoster_path());
                detail.putExtra("vote_count", data.get(position).getVote_count());
                detail.putExtra("vote_average", data.get(position).getVote_average());
                detail.putExtra("popularity", data.get(position).getPopularity());
                detail.putExtra("release_date", data.get(position).getRelease_date());
                detail.putExtra("overview", data.get(position).getOverview());
                context.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
