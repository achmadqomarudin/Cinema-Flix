package com.example.achmadqomarudin.cinemaflix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class DetailActivity extends AppCompatActivity {

    public TextView tv_Title, tv_Rating, tv_Date, tv_Vote, tv_Popularity, tv_Overview;
    public ImageView iv_ImageHeader, iv_ImagePoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tv_Title = (TextView) findViewById(R.id.tv_title);
        tv_Rating = (TextView) findViewById(R.id.tv_rating);
        tv_Date = (TextView) findViewById(R.id.tv_date);
        tv_Vote = (TextView) findViewById(R.id.tv_vote);
        tv_Popularity = (TextView) findViewById(R.id.tv_popularity);
        tv_Overview = (TextView) findViewById(R.id.tv_overview);
        iv_ImageHeader = (ImageView) findViewById(R.id.imageHeader);
        iv_ImagePoster = (ImageView) findViewById(R.id.imagePoster);

        Intent i = getIntent();

        tv_Title.setText(i.getStringExtra("title"));
        tv_Rating.setText(i.getStringExtra("vote_average"));
        tv_Date.setText(i.getStringExtra("release_date"));
        tv_Vote.setText(i.getStringExtra("vote_count"));
        tv_Popularity.setText(i.getStringExtra("popularity"));
        tv_Overview.setText(i.getStringExtra("overview"));


        Glide.with(DetailActivity.this).load(i.getStringExtra("img"))
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(iv_ImageHeader);

        Glide.with(DetailActivity.this).load(i.getStringExtra("img"))
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(iv_ImagePoster);

        System.out.println("Rating " + i.getStringExtra("vote_average"));
        System.out.println("Date " + i.getStringExtra("release_date"));
        System.out.println("Vote " + i.getStringExtra("vote_count"));
        System.out.println("Popularity " + i.getStringExtra("popularity"));
    }
}
