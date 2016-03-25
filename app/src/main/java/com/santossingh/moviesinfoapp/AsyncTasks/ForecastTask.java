package com.santossingh.moviesinfoapp.AsyncTasks;

import android.os.AsyncTask;

import com.santossingh.moviesinfoapp.Database.MovieModel;
import com.santossingh.moviesinfoapp.Interfaces.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stark on 24-03-2016.
 */
public class ForecastTask extends AsyncTask<String, String, List<MovieModel>> {
    private final String LOG_TAG = ForecastTask.class.getSimpleName();
    public AsyncResponse delegate = null;
    private String API_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxx";
    private List<MovieModel> movieModelList;

    public ForecastTask(AsyncResponse listener) {
        delegate = listener;
    }

    public ForecastTask() {
        super();
    }

    @Override
    protected List<MovieModel> doInBackground(String... params) {
        if (params.length == 0) {
            return null;
        }

        String TopRated = "http://api.themoviedb.org/3/discover/movie?" +
                "certification_country=US&certification=" +
                "R&sort_by=vote_average.desc&api_key=" + API_KEY;
        String Popularity = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + API_KEY;

        if (params[0].equals("TOP")) {
            params[0]=TopRated;
        }else{
            params[0]=Popularity;
        }

        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            JSONObject jsonObject = new JSONObject(buffer.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            movieModelList= new ArrayList<>();
            //adding JSON Array data into MovieModel Class object
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject finalObject = jsonArray.getJSONObject(i);
                MovieModel movieModel = new MovieModel();
                movieModel.setId(finalObject.getInt("id"));
                movieModel.setTitle(finalObject.getString("title"));
                movieModel.setPoster_path(finalObject.getString("poster_path"));
                movieModel.setRelease_date(finalObject.getString("release_date"));
                movieModel.setVote_average((float) finalObject.getDouble("vote_average"));
                movieModel.setOverview(finalObject.getString("overview"));
                movieModelList.add(movieModel);
            }
            return movieModelList;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute( List<MovieModel> movieModels) {
        delegate.processFinish(movieModels);
    }
}
