package me.weekbelt.movieapp.domain.movie;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.weekbelt.movieapp.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter @NoArgsConstructor
@Entity
public class Movie extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String titleEnglish;

    @Column(nullable = false)
    private Integer year;

    @Column
    private Double rating;

    @Column(nullable = false)
    private Integer runtime;

    @Column
    private String summary;

    @Lob
    @Column(length = 4000)
    private String description;

    @Builder
    public Movie(String title, String titleEnglish, Integer year, Double rating, Integer runtime, String summary, String description) {
        this.title = title;
        this.titleEnglish = titleEnglish;
        this.year = year;
        this.rating = rating;
        this.runtime = runtime;
        this.summary = summary;
        this.description = description;
    }
}
