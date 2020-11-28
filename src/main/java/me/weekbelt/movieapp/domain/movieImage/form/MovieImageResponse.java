package me.weekbelt.movieapp.domain.movieImage.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class MovieImageResponse {

    private final Long movieImageId;

    private final Long movieId;

    private final Long fileId;

    private final String fileName;

    private final String saveFileName;

    private final String contentType;

    private final LocalDateTime createdDateTime;

    private final LocalDateTime modifiedDateTime;
}
