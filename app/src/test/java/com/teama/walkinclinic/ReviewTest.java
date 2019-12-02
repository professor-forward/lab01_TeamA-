package com.teama.walkinclinic;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReviewTest {

        @Test
        public void checkStarRating(){
            float starRating = 0.5f;
            String clientComment = "Clinic good";
            String reviewuid = "abc123";

            Review review1 = new Review(starRating, clientComment, reviewuid);
            assertEquals("Check the rating", "0.5", Float.toString(review1.getStarRating()));
        }
        @Test
        public void checkClientComment(){
            float starRating = 0.5f;
            String clientComment = "Clinic good";
            String reviewuid = "abc123";

            Review review1 = new Review(starRating, clientComment, reviewuid);
            assertEquals("Check the comment", "Clinic good", review1.getClientComment());
        }
        @Test
        public void checkReviewUid(){
            float starRating = 0.5f;
            String clientComment = "Clinic good";
            String reviewuid = "abc123";

            Review review1 = new Review(starRating, clientComment, reviewuid);
            assertEquals("Check the reviewuid", "abc123", review1.getReviewuid());
        }

    }


