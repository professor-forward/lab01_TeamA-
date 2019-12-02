package com.teama.walkinclinic;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest2 {
    @Test
    public void checkStarRating(){
        float starRating = 0.5f;
        String clientComment = "";
        String reviewuid = "123abc";

        Review review2 = new Review(starRating, clientComment, reviewuid);
        assertEquals("Check the rating", "0.5", Float.toString(review2.getStarRating()));
    }
    @Test
    public void checkClientComment(){
        float starRating = 0.5f;
        String clientComment = "";
        String reviewuid = "123abc";

        Review review2 = new Review(starRating, clientComment, reviewuid);
        assertEquals("Check the comment", "", review2.getClientComment());
    }
    @Test
    public void checkReviewUid(){
        float starRating = 0.5f;
        String clientComment = "";
        String reviewuid = "123abc";

        Review review2 = new Review(starRating, clientComment, reviewuid);
        assertEquals("Check the reviewuid", "123abc", review2.getReviewuid());
    }
}
