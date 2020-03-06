package io.springquickstart.ratingdataservice.resources;

import io.springquickstart.ratingdataservice.models.Rating;
import io.springquickstart.ratingdataservice.models.UserRatings;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResources {
    @RequestMapping("/{movieId}")
    public Rating getMovieRating(@PathVariable("movieId") String movieId) {
        return new Rating(movieId, 3);
    }
    @RequestMapping("/users/{usersId}")
    public UserRatings getUserRating(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("3124", 5)
        );

        UserRatings userRatings = new UserRatings();
        userRatings.setUserRatings(ratings);

        return userRatings;
    }
}
