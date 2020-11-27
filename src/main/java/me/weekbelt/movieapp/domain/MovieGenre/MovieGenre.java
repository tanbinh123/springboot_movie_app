package me.weekbelt.movieapp.domain.MovieGenre;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.movieapp.domain.genres.Genre;
import me.weekbelt.movieapp.domain.movie.Movie;

import javax.persistence.*;


@Getter @NoArgsConstructor
@Entity
public class MovieGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Builder
    public MovieGenre(Genre genre, Movie movie) {
        this.genre = genre;
        this.movie = movie;
    }

}
