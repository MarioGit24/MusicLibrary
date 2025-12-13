package se.yrgo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import se.yrgo.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long> {}
