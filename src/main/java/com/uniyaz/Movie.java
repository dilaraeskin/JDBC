package com.uniyaz;

public class Movie {

    private int  movieId;
    private int movieHeroId;
    private  String movieAd;
    private float budget;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieAd() {
        return movieAd;
    }

    public void setMovieAd(String movieAd) {
        this.movieAd = movieAd;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public int getMovieHeroId() {
        return movieHeroId;
    }

    public void setMovieHeroId(int movieHeroId) {
        this.movieHeroId = movieHeroId;
    }
}
