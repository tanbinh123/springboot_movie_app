package me.weekbelt.movieapp.domain.movieImage.repository;

import me.weekbelt.movieapp.domain.movieImage.ImageType;
import me.weekbelt.movieapp.domain.movieImage.MovieImage;

import java.util.List;

public interface MovieImageRepositoryCustom {

    List<MovieImage> findMovieImageByMovieIdAndType(Long movieId, ImageType type);
}
