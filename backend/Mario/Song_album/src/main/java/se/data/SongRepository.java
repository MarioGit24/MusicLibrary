package se.data;

import org.springframework.data.jpa.repository.JpaRepository;

import se.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long> {}
