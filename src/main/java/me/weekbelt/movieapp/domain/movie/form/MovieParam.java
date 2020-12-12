package me.weekbelt.movieapp.domain.movie.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class MovieParam {

    @NotBlank
    private final String title;

    @NotBlank
    private final String titleEnglish;

    private final List<@NotBlank String> genres;

    @Min(0)
    private final Integer year;

    @Min(0)
    private final Double rating;

    @Min(0)
    private final Integer runtime;

    @NotBlank
    private final String summary;

    private final String description;

}
