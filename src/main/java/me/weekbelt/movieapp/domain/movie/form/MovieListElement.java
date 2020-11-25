package me.weekbelt.movieapp.domain.movie.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class MovieListElement {

    private final Long id;

    private final String title;

    private final List<String> genres;

    private final String summary;

    private final Integer year;

    private final Double rating;

    private final Integer runtime;

    private final String movieImageUrl;

    private final LocalDateTime createdDateTime;

    private final LocalDateTime modifiedDateTime;
}
