package me.weekbelt.movieapp.domain.movie.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.movieapp.domain.MovieGenre.MovieGenre;
import me.weekbelt.movieapp.domain.MovieGenre.repository.MovieGenreRepository;
import me.weekbelt.movieapp.domain.genres.Genre;
import me.weekbelt.movieapp.domain.movie.Movie;
import me.weekbelt.movieapp.domain.movie.form.MovieListElement;
import me.weekbelt.movieapp.domain.movie.repository.MovieRepository;
import me.weekbelt.movieapp.domain.movieImage.ImageType;
import me.weekbelt.movieapp.domain.movieImage.MovieImage;
import me.weekbelt.movieapp.domain.movieImage.repository.MovieImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieImageRepository movieImageRepository;

    public Page<MovieListElement> findAllMovieListElement(Pageable pageable) {
        Page<Movie> moviePage = movieRepository.findAllMovie(pageable);
        return moviePage.map(movie -> {
            List<String> genres = getGenresStr(movie);
            MovieImage movieImage = movieImageRepository.findMovieImageByMovieIdAndType(movie.getId(), ImageType.THUMB).get(0);

            return getMovieListElement(movie, genres, movieImage);
        });
    }

    private List<String> getGenresStr(Movie movie) {
        List<MovieGenre> movieGenres = movie.getMovieGenres();
        return movieGenres.stream().map(movieGenre -> movieGenre.getGenre().getName()).collect(Collectors.toList());
    }

    private MovieListElement getMovieListElement(Movie movie, List<String> genres, MovieImage movieImage) {
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


}
