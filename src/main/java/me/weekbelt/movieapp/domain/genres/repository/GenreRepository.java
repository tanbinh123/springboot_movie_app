package me.weekbelt.movieapp.domain.genres.repository;

import me.weekbelt.movieapp.domain.genres.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}
