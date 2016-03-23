package com.santossingh.moviesinfoapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.santossingh.moviesinfoapp.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @Bind(R.id.movieTitle) TextView movie_Title;
    @Bind(R.id.moviePoster) ImageView movie_Poster;
    @Bind(R.id.releaseDate) TextView movie_ReleaseDate;
    @Bind(R.id.rating_Percent) TextView movie_UsersRating;
    @Bind(R.id.ratingBar) RatingBar movie_RatingStars;
    @Bind(R.id.overViewDetail) TextView movie_Overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        //Getting Extra_text via Intent and set it in movieTitle TextView-------------------
        Intent intent= getIntent();
        String movie_name=intent.getStringExtra("movie_Name");
        String movie_poster=intent.getStringExtra("poster_Path");
        String release_date=intent.getStringExtra("release_Date");
        Float users_rating=intent.getFloatExtra("users_Rating", 0);
        String overview=intent.getStringExtra("overview");

        Picasso.with(DetailActivity.this)
                .load("http://image.tmdb.org/t/p/w185/" + movie_poster)
                .resize(250, 320)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(movie_Poster);

        movie_Title.setText(movie_name);
        movie_ReleaseDate.setText(release_date);
        movie_UsersRating.setText(users_rating+"/10");
        movie_RatingStars.setRating(users_rating*5/10);
        movie_Overview.setText(overview);
    }

}