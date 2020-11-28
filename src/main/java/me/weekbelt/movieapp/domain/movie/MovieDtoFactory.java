package me.weekbelt.movieapp.domain.movie;

import me.weekbelt.movieapp.domain.movie.form.MovieListElement;
import me.weekbelt.movieapp.domain.movie.form.MovieParam;
import me.weekbelt.movieapp.domain.movie.form.MovieResponse;
import me.weekbelt.movieapp.domain.movieImage.MovieImage;
import me.weekbelt.movieapp.domain.movieImage.form.MovieImageResponse;

import java.util.List;

public class MovieDtoFactory {

    public static MovieListElement bindToMovieListElement(Movie movie, List<String> genres, MovieImage movieImage) {
        return MovieListElement.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genres(genres)
                .summary(movie.getSummary())
                .runtime(movie.getRuntime())
                .year(movie.getYear())
                .rating(movie.getRating())
                .movieImageUrl(movieImage.getFileInfo().getSaveFileName())
                .createdDateTime(movie.getCreatedDateTime())
                .modifiedDateTime(movie.getModifiedDateTime())
                .build();
    }

    public static Movie bindMovieParamToMovie(MovieParam movieParam) {
        return Movie.builder()
                .title(movieParam.getTitle())
                .titleEnglish(movieParam.getTitleEnglish())
                .description(movieParam.getDescription())
                .rating(movieParam.getRating())
                .runtime(movieParam.getRuntime())
                .summary(movieParam.getSummary())
                .year(movieParam.getYear())
                .build();
    }

    public static MovieResponse bindToMovieResponse(Movie movie, List<String> genres,
                                                    MovieImageResponse thumbnailImage, List<MovieImageResponse> mainImages) {
        return MovieResponse.builder()
                .movieId(movie.getId())
                .title(movie.getTitle())
                .titleEnglish(movie.getTitleEnglish())
                .year(movie.getYear())
                .rating(movie.getRating())
                .runtime(movie.getRuntime())
                .summary(movie.getSummary())
                .description(movie.getDescription())
                .createdDatetime(movie.getCreatedDateTime())
                .modifiedDateTime(movie.getCreatedDateTime())
                .genres(genres)
                .movieThumbImage(thumbnailImage)
                .movieMainImages(mainImages)
                .build();
    }
}
