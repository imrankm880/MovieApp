package com.example.movieapp.model;

import com.google.gson.annotations.SerializedName;

public class MovieModel {
    @SerializedName("title")
    private String title;
//    @SerializedName("year")
   private double voteScore;
//    private String date;


    public MovieModel(String title, double voteScore) {
        this.title = title;
        this.voteScore = voteScore;
//        this.date = date;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
//
    public double getVoteScore() {
        return voteScore;
    }

    public void setVoteScore(double voteScore) {
        this.voteScore = voteScore;
    }

//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }


}
