package com.TuneWave.AudioApp.Reviews.impl;

import com.TuneWave.AudioApp.Audio.Audio;
import com.TuneWave.AudioApp.Audio.AudioService;
import com.TuneWave.AudioApp.Reviews.Review;
import com.TuneWave.AudioApp.Reviews.ReviewRepository;
import com.TuneWave.AudioApp.Reviews.ReviewService;
import com.TuneWave.AudioApp.User.User;
import com.TuneWave.AudioApp.User.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImplementation implements ReviewService {

    private final AudioService audioService;
    ReviewRepository reviewRepository;

    public ReviewServiceImplementation(AudioService audioService, ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        this.audioService = audioService;
    }

    @Override
    public List<Review> getAllReview(Long audioId) {
        return reviewRepository.findByAudioId(audioId);
    }

    @Override
    public boolean addReview(Long audioId, Review review) {
        Audio audio = audioService.getAudioById(audioId);
        if (audio != null) {
            review.setAudio(audio);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long audioId, Long Id) {
        List<Review> reviews = reviewRepository.findByAudioId(audioId);
        return reviews.stream()
                .filter(review -> review.getId() == Id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long audioId, Review review) {
        Audio audio = audioService.getAudioById(audioId);
        if (audio != null) {
            Optional<Review> reviewOptional = reviewRepository.findById(review.getId());
            if (reviewOptional.isPresent()) {
                Review oReview = reviewOptional.get();
                oReview.setAudio(audio);
                oReview.setRating(review.getRating());
                oReview.setReview(review.getReview());
                reviewRepository.save(oReview);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long audioId, Long id) {
        Audio audio = audioService.getAudioById(audioId);
        if (audio != null) {
            Optional<Review> reviewOptional = reviewRepository.findById(id);
            if (reviewOptional.isPresent()) {
                audio.getReviews().remove(reviewOptional.get());
                reviewRepository.delete(reviewOptional.get());
                audioService.updateAudio(audio);
                return true;
            }
        }
        return false;
    }
}
