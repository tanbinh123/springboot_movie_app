package me.weekbelt.movieapp.domain.movie.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.weekbelt.movieapp.domain.movie.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static me.weekbelt.movieapp.domain.movie.QMovie.movie;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieRepositoryCustomImpl implements MovieRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Movie> findAllMovie(Pageable pageable) {
        QueryResults<Movie> results = queryFactory
                .selectFrom(movie)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchResults();

        List<Movie> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
