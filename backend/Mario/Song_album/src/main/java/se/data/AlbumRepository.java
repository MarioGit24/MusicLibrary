package se.data;

import org.springframework.data.jpa.repository.JpaRepository;

import se.domain.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {}
