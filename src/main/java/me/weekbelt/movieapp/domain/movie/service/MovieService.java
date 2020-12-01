package me.weekbelt.movieapp.domain.movie.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.movieapp.domain.MovieGenre.MovieGenre;
import me.weekbelt.movieapp.domain.MovieGenre.repository.MovieGenreRepository;
import me.weekbelt.movieapp.domain.fileInfo.FileInfo;
import me.weekbelt.movieapp.domain.fileInfo.repository.FileInfoRepository;
import me.weekbelt.movieapp.domain.genres.Genre;
import me.weekbelt.movieapp.domain.genres.repository.GenreRepository;
import me.weekbelt.movieapp.domain.movie.Movie;
import me.weekbelt.movieapp.domain.movie.MovieDtoFactory;
import me.weekbelt.movieapp.domain.movie.form.MovieListElement;
import me.weekbelt.movieapp.domain.movie.form.MovieParam;
import me.weekbelt.movieapp.domain.movie.form.MovieResponse;
import me.weekbelt.movieapp.domain.movie.repository.MovieRepository;
import me.weekbelt.movieapp.domain.movieImage.ImageType;
import me.weekbelt.movieapp.domain.movieImage.MovieImage;
import me.weekbelt.movieapp.domain.movieImage.MovieImageDtoFactory;
import me.weekbelt.movieapp.domain.movieImage.form.MovieImageResponse;
import me.weekbelt.movieapp.domain.movieImage.repository.MovieImageRepository;
import me.weekbelt.movieapp.util.FileUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final GenreRepository genreRepository;
    private final FileInfoRepository fileInfoRepository;
    private final MovieImageRepository movieImageRepository;
    private final FileUtils fileUtils;

    @Transactional(readOnly = true)
    public Page<MovieListElement> findAllMovieListElement(Pageable pageable) {
        Page<Movie> moviePage = movieRepository.findAllMovie(pageable);
        return moviePage.map(movie -> {
            List<String> genres = movie.getGenreNameList();
            MovieImage movieImage = movieImageRepository.findMovieImageByMovieIdAndType(movie.getId(), ImageType.THUMB).get(0);

            return MovieDtoFactory.bindToMovieListElement(movie, genres, movieImage);
        });
    }

    public MovieResponse findMovieResponseByMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() ->
                new IllegalArgumentException("해당하는 영화 정보가 존재하지 않습니다."));

        List<String> genres = movie.getGenreNameList();

//        MovieImage movieImage = movieImageRepository.findMovieImageByMovieIdAndType(movieId, ImageType.THUMB).get(0);
        List<MovieImage> imageList = movieImageRepository.findMovieImageByMovieIdAndType(movieId, ImageType.THUMB);
        if (imageList.size() == 0) {
            return MovieDtoFactory.bindToMovieResponse(movie, genres, null);
        } else {
            MovieImageResponse movieImageResponse = MovieImageDtoFactory.bindToMovieImageResponse(imageList.get(0));
            return MovieDtoFactory.bindToMovieResponse(movie, genres, movieImageResponse);
        }


    }

    public Long createMovie(MovieParam movieParam, MultipartFile movieImageMultipartFile) {
        // Movie 엔티티 생성
        Movie movie = MovieDtoFactory.bindMovieParamToMovie(movieParam);
        // Movie 엔티티 저장
        movieRepository.save(movie);

        // MovieGenre 엔티티 저장
        movieParam.getGenres().forEach(name -> {
            Genre genre = genreRepository.findByName(name)
                    .orElseThrow(() -> new IllegalArgumentException(name + "이라는 장르는 존재하지 않습니다."));

            MovieGenre movieGenre = MovieGenre.builder()
                    .movie(movie)
                    .genre(genre)
                    .build();

            movieGenreRepository.save(movieGenre);
        });


        // 썸네일 FileImage 엔티티 저장
        if (movieImageMultipartFile != null) {
            FileInfo movieImageFileInfo = fileUtils.saveFileAtStorage(movieImageMultipartFile);
            fileInfoRepository.save(movieImageFileInfo);

            MovieImage movieImage = MovieImage.builder()
                    .fileInfo(movieImageFileInfo)
                    .movie(movie)
                    .type(ImageType.THUMB)
                    .build();
            movieImageRepository.save(movieImage);
        }

        return movie.getId();
    }


}
