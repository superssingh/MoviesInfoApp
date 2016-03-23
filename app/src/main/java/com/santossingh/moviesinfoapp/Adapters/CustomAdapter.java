package com.santossingh.moviesinfoapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.santossingh.moviesinfoapp.Database.MovieModel;
import com.santossingh.moviesinfoapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Stark on 24-03-2016.
 */
public class CustomAdapter extends ArrayAdapter<MovieModel> {
    private static final String LOG_TAG=CustomAdapter.class.getSimpleName();
    @Bind(R.id.imageView)
    ImageView imageView;

    public CustomAdapter(Context context, List<MovieModel> objects)
    {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_image_fragment, parent, false);
        }
        MovieModel movieModel= new MovieModel();
        movieModel=getItem(position);
        ButterKnife.bind(this, convertView);
        // set image in imageView by Picasso API
        Picasso.with(convertView.getContext())
                .load("http://image.tmdb.org/t/p/w185/"+movieModel.getPoster_path())
                .resize(240, 330)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(imageView);

        return convertView;
    }
}
