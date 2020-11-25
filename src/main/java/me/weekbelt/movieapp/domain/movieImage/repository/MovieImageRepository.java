package me.weekbelt.movieapp.domain.movieImage.repository;

import me.weekbelt.movieapp.domain.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<Long, Movie>, MovieImageRepositoryCustom {
}
