package com.teama.walkinclinic;

import android.media.Rating;

public class Review {
    Rating starRating;
    String clientComment;

    public Review(Rating review, String comment){
        this.starRating = review;
        this.clientComment = comment;
    }

    public Rating getStarRating() {
        return starRating;
    }
    public String getClientComment() {
        return clientComment;
    }

    public void setMonth(Rating rating) {
        this.starRating = rating;
    }
    public void setDate(String comment ) { this.clientComment = comment; }

    public String toString(){
        return "Rating: " + this.starRating + "   Comment: " + clientComment;
    }
}
