package me.weekbelt.movieapp.domain.movie.repository;

import me.weekbelt.movieapp.domain.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Long, Movie>, MovieRepositoryCustom {
}
