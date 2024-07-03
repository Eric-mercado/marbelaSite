package com.karaoke.marbella.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KaraokeRepository extends JpaRepository<Karaoke, Integer> {

    List<Karaoke> findAllByCancion(String cancion);
}
