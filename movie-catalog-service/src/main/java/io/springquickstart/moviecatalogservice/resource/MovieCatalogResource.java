package io.springquickstart.moviecatalogservice.resource;

import io.springquickstart.moviecatalogservice.models.CatalogItem;
import io.springquickstart.moviecatalogservice.models.Movie;
import io.springquickstart.moviecatalogservice.models.Rating;
import io.springquickstart.moviecatalogservice.models.UserRatings;
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
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        // get all rated movie ids;
        UserRatings userRatings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId, UserRatings.class);

        return userRatings.getUserRatings().stream().map(rating -> {
            // for each movie ID, call movie info service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "action", 4);
        }).collect(Collectors.toList());
    }
}
