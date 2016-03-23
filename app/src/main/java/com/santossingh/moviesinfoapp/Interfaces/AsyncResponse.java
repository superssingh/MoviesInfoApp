package com.santossingh.moviesinfoapp.Interfaces;

import com.santossingh.moviesinfoapp.Database.MovieModel;

import java.util.List;

/**
 * Created by Stark on 24-03-2016.
 */
public interface AsyncResponse {
    void processFinish(List<MovieModel> movieModels);
}
