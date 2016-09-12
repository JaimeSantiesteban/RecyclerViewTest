package com.mac.training.recyclerviewtest.model;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 9/8/2016.
 */
public class Movie {

    public static final String baseImageUrl = "http://image.tmdb.org/t/p/w300/";

    @SerializedName("original_title")
    private String title;
    @SerializedName("original_language")
    private String genre;
    @SerializedName("release_date")
    private String year;
    @SerializedName("poster_path")
    private String imageMovie;

    public Movie() {
    }

    public Movie(String title, String genre, String year, String imageMovie) {

        this.title = title;
        this.genre = genre;
        this.year = year;
        this.imageMovie = imageMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImageMovie() {
        return imageMovie;
    }

    public void setImageMovie(String imageMovie) {
        this.imageMovie = imageMovie;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                ", imageMovie='" + imageMovie + '\'' +
                '}';
    }
}
