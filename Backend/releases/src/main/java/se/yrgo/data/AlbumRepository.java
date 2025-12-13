package se.yrgo.data;

import org.springframework.data.jpa.repository.JpaRepository;

import se.yrgo.domain.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {}
