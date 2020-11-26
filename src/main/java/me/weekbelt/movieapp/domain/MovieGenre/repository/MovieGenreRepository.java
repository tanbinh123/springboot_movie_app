package me.weekbelt.movieapp.domain.MovieGenre.repository;

import me.weekbelt.movieapp.domain.MovieGenre.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, Long> {
}
