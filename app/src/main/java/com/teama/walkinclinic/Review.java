package com.teama.walkinclinic;

import android.media.Rating;

public class Review {
    float starRating;
    String clientComment;
    String reviewuid;

    public Review(float review, String comment, String uid){
        this.starRating = review;
        this.clientComment = comment;
        this.reviewuid = uid;
    }

    public Review(){};

    public void setReviewuid(String id){
        this.reviewuid = id;
    }

    public String getReviewuid(){
        return this.reviewuid;
    }

    public float getStarRating() {
        return starRating;
    }
    public String getClientComment() {
        return clientComment;
    }

    public void setMonth(float rating) {
        this.starRating = rating;
    }
    public void setDate(String comment ) { this.clientComment = comment; }

    public String toString(){
        return "Rating: " + this.starRating + "   Comment: " + clientComment;
    }
}
