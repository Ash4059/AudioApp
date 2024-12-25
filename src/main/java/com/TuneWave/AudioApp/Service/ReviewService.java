package com.TuneWave.AudioApp.Service;

import com.TuneWave.AudioApp.Entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReview(Long audioId);
    boolean addReview(Long audioId, Review review);
    Review getReviewById(Long audioId,Long Id);
    boolean updateReview(Long audioId,Review review);
    boolean deleteReview(Long audioId,Long id);
}
