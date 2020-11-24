package me.weekbelt.movieapp.domain.movieImage;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.movieapp.domain.fileInfo.FileInfo;
import me.weekbelt.movieapp.domain.movie.Movie;

import javax.persistence.*;

@Getter @NoArgsConstructor
@Entity
public class MovieImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private FileInfo fileInfo;

    @Column(nullable = false, length = 2)
    private String type;

    @Builder
    public MovieImage(Movie movie, FileInfo fileInfo, String type) {
        this.movie = movie;
        this.fileInfo = fileInfo;
        this.type = type;
    }
}
