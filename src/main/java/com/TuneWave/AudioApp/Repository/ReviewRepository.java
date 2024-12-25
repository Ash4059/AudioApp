package com.TuneWave.AudioApp.Repository;

import com.TuneWave.AudioApp.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByAudioId(Long audioId);
}
