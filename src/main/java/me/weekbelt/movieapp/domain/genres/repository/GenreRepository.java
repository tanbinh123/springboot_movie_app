package me.weekbelt.movieapp.domain.genres.repository;

import me.weekbelt.movieapp.domain.genres.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Long, Genre> {
}
