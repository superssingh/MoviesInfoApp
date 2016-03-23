package com.santossingh.moviesinfoapp.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.santossingh.moviesinfoapp.Activities.DetailActivity;
import com.santossingh.moviesinfoapp.Adapters.CustomAdapter;
import com.santossingh.moviesinfoapp.AsyncTasks.ForecastTask;
import com.santossingh.moviesinfoapp.Database.MovieModel;
import com.santossingh.moviesinfoapp.Interfaces.AsyncResponse;
import com.santossingh.moviesinfoapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stark on 24-03-2016.
 */
public class ForecastFragment extends Fragment implements AsyncResponse {
    private List<MovieModel> movieModels;
    private static final String STATE_MOVIES ="state_movies";
    private CustomAdapter customAdapter=null;
    private GridView gridView=null;
    private View rootView=null;
    private ForecastTask forecastTask;
    //Constructor
    public ForecastFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelableArrayList(STATE_MOVIES, (ArrayList<? extends Parcelable>)
          //      movieModels);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.most_Popular:
                if(item.isChecked()){
                    item.setChecked(false);
                }else{
                    item.setChecked(true);
                    return true;
                }
            case R.id.High_rated:
                if(item.isChecked()){
                    item.setChecked(false);
                }else{
                    item.setChecked(true);

                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.activity_main, container, false);
        gridView=(GridView)rootView.findViewById(R.id.gridView);
        movieModels=new ArrayList<MovieModel>();

            forecastTask = new ForecastTask(this);
            forecastTask.delegate = this;
            forecastTask.execute("Popular");

        customAdapter = new CustomAdapter(getActivity(), movieModels);
        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String movie_name = movieModels.get(position).getTitle();
                String poster_path = movieModels.get(position).getPoster_path();
                String release_date = movieModels.get(position).getRelease_date();
                Float users_rating = movieModels.get(position).getVote_average();
                String overview = movieModels.get(position).getOverview();

                Intent intent = new Intent(getActivity(), DetailActivity.class)
                        .putExtra("movie_Name", movie_name)
                        .putExtra("poster_Path", poster_path)
                        .putExtra("release_Date", release_date)
                        .putExtra("users_Rating", users_rating)
                        .putExtra("overview", overview);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void processFinish(List<MovieModel> movieModels) {
        this.movieModels=movieModels;
    }
}
