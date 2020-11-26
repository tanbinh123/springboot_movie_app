package me.weekbelt.movieapp.domain.movieImage.repository;

import me.weekbelt.movieapp.domain.movieImage.MovieImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long>, MovieImageRepositoryCustom {
}
