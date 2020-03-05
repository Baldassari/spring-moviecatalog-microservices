package io.springquickstart.moviecatalogservice.resource;

import io.springquickstart.moviecatalogservice.models.CatalogItem;
import io.springquickstart.moviecatalogservice.models.Movie;
import io.springquickstart.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        // get all rated movie ids;
        List<Rating> ratings = Arrays.asList(
                new Rating("transformers", 4),
                new Rating("matrix", 5)
        );
        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "action", 4);
        }).collect(Collectors.toList());
        // for each movie ID, call moe info service and get details
    }
}
