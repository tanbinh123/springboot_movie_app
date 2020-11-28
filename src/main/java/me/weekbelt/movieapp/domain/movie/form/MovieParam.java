package me.weekbelt.movieapp.domain.movie.form;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
public class MovieParam {

    @NotBlank
    private String title;

    @NotBlank
    private String titleEnglish;

    private List<@NotBlank String> genres;

    @NotBlank
    private Integer year;

    @NotBlank
    private Double rating;

    @NotBlank
    private Integer runtime;

    @NotBlank
    private String summary;

    private String description;

    private MultipartFile thumbnailImage;

    private MultipartFile[] mainImages;
}
