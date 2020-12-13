package me.weekbelt.movieapp.web;

import lombok.RequiredArgsConstructor;
import me.weekbelt.movieapp.domain.movie.form.MovieParam;
import me.weekbelt.movieapp.domain.movie.form.MovieResponse;
import me.weekbelt.movieapp.domain.movie.service.MovieService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequestMapping(value = "/movies", produces = MediaTypes.HAL_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class MovieApiController {

    private final MovieService movieService;

//    @GetMapping
//    public ResponseEntity<?> getMovies(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)
//                                               Pageable pageable) {
//
//
//        return ResponseEntity.ok().build();
//    }

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody @Valid MovieParam movieParam,
                                         @RequestParam(required = false) MultipartFile movieImageMultipartFile,
                                         Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Long saveMovieId = movieService.createMovie(movieParam, movieImageMultipartFile);

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(MovieApiController.class).slash(saveMovieId);
        URI createdUri = webMvcLinkBuilder.toUri();
        List<Link> links = Arrays.asList(
                webMvcLinkBuilder.withSelfRel(),
                webMvcLinkBuilder.withRel("update-event"),
                linkTo(MovieApiController.class).withRel("query-events")
        );

        MovieResponse movieResponse = movieService.findMovieResponseByMovieId(saveMovieId);

        EntityModel<MovieResponse> movieResource = EntityModel.of(movieResponse, links);
        return ResponseEntity.created(createdUri).body(movieResource);
    }

}
