package me.weekbelt.movieapp.domain.movieImage.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.weekbelt.movieapp.domain.fileInfo.QFileInfo;
import me.weekbelt.movieapp.domain.movie.QMovie;
import me.weekbelt.movieapp.domain.movieImage.ImageType;
import me.weekbelt.movieapp.domain.movieImage.MovieImage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static me.weekbelt.movieapp.domain.fileInfo.QFileInfo.fileInfo;
import static me.weekbelt.movieapp.domain.movie.QMovie.movie;
import static me.weekbelt.movieapp.domain.movieImage.QMovieImage.movieImage;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieImageRepositoryCustomImpl implements MovieImageRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<MovieImage> findMovieImageByMovieIdAndType(Long movieId, ImageType type) {
        return queryFactory
                .selectFrom(movieImage)
                .join(movieImage.movie, movie).fetchJoin()
                .join(movieImage.fileInfo, fileInfo).fetchJoin()
                .where(movieIdEq(movieId), typeEq(type))
                .fetch();
    }

    private BooleanExpression movieIdEq(Long movieId) {
        return movieId != null ? movieImage.movie.id.eq(movieId) : null;
    }

    private BooleanExpression typeEq(ImageType type) {
        return type != null ? movieImage.type.eq(type) : null;
    }
}
