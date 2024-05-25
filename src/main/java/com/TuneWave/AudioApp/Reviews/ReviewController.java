package com.TuneWave.AudioApp.Reviews;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/audios/{audioId}/reviews")
public class ReviewController {
    
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReview(@PathVariable Long audioId) {
        List<Review> reviews = reviewService.getAllReview(audioId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@PathVariable Long audioId,@RequestBody Review review) {
        boolean bAddReview = reviewService.addReview(audioId, review);
        if(bAddReview){
            return new ResponseEntity<>("Review added successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Review> findById(@PathVariable Long audioId, @PathVariable Long Id){
        Review review = reviewService.getReviewById(audioId, Id);
        return new ResponseEntity<>(review, review != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<String> updateReview(@PathVariable Long audioId, @RequestBody Review review){
        boolean bUpdateReview = reviewService.updateReview(audioId, review);
        if(bUpdateReview){
            return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long audioId, @PathVariable Long Id){
        boolean bDeleteReview = reviewService.deleteReview(audioId, Id);
        if(bDeleteReview){
            return new ResponseEntity<>("Review deleted successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);
    }
}
