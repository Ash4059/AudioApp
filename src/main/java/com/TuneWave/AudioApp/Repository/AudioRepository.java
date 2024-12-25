package com.TuneWave.AudioApp.Repository;

import com.TuneWave.AudioApp.Entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepository extends JpaRepository<Audio, Long> {
}
