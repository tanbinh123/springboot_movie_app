package me.weekbelt.movieapp.domain.movie.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

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

    private final Integer year;

    private final Double rating;

    private final Integer runtime;

    @NotBlank
    private final String summary;

    private final String description;

    private final MultipartFile movieImage;
}
