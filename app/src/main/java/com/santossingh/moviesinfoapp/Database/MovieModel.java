package com.santossingh.moviesinfoapp.Database;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Stark on 24-03-2016.
 */
public class MovieModel implements Parcelable {
    private int id;
    private String title;
    private String poster_path;
    private String release_date;
    private float vote_average;
    private String overview;

    private List<MovieModel> models;
    public MovieModel(){

    }

    public List<MovieModel> getModels() {
        return models;
    }

    public void setModels(List<MovieModel> models) {
        this.models = models;
    }

    public MovieModel(int id, String title, String poster_path, String release_date, float vote_average, String overview) {
        this.id = id;
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.overview = overview;
    }

    protected MovieModel(Parcel in) {
        id = in.readInt();
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        vote_average = in.readFloat();
        overview = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }
    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getVote_average() {
        return vote_average;
    }
    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(title);
        out.writeString(poster_path);
        out.writeString(release_date);
        out.writeFloat(vote_average);
        out.writeString(overview);
    }

}
