package me.weekbelt.movieapp.domain.movieImage;

import me.weekbelt.movieapp.domain.movieImage.form.MovieImageResponse;

public class MovieImageDtoFactory {
    public static MovieImageResponse bindToMovieImageResponse(MovieImage movieImage) {
        return MovieImageResponse.builder()
                        .movieImageId(movieImage.getId())
                        .movieId(movieImage.getMovie().getId())
                        .fileId(movieImage.getFileInfo().getId())
                        .fileName(movieImage.getFileInfo().getFileName())
                        .contentType(movieImage.getFileInfo().getContentType())
                        .saveFileName(movieImage.getFileInfo().getSaveFileName())
                        .createdDateTime(movieImage.getFileInfo().getCreatedDateTime())
                        .modifiedDateTime(movieImage.getFileInfo().getModifiedDateTime())
                        .build();
    }
}
