package me.weekbelt.movieapp.web;

import lombok.RequiredArgsConstructor;
import me.weekbelt.movieapp.domain.movie.form.MovieParam;
import me.weekbelt.movieapp.domain.movie.form.MovieResponse;
import me.weekbelt.movieapp.domain.movie.service.MovieService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<?> createMovie(@RequestBody @Valid MovieParam movieParam, @RequestParam(required = false) MultipartFile movieImageMultipartFile) {
        Long saveMovieId = movieService.createMovie(movieParam, movieImageMultipartFile);
        URI createdUri = linkTo(MovieApiController.class).slash(saveMovieId).toUri();

        MovieResponse movieResponse = movieService.findMovieResponseByMovieId(saveMovieId);
        return ResponseEntity.created(createdUri).body(movieResponse);
    }

}
