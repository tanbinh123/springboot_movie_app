package me.weekbelt.movieapp.domain.movie.repository;

import me.weekbelt.movieapp.domain.movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieRepositoryCustom {
    Page<Movie> findAllMovie(Pageable pageable);
}
