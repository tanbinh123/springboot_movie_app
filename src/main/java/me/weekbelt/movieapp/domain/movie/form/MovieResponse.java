package me.weekbelt.movieapp.domain.movie.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.weekbelt.movieapp.domain.movieImage.form.MovieImageResponse;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class MovieResponse {

    private final Long movieId;

    private final String title;

    private final String titleEnglish;

    private final List<String> genres;

    private final Integer year;

    private final Double rating;

    private final Integer runtime;

    private final String summary;

    private final String description;

    private final MovieImageResponse movieImageResponse;

    private final LocalDateTime createdDatetime;

    private final LocalDateTime modifiedDateTime;


}
