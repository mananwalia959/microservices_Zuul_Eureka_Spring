package io.manan.micro.resource;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.manan.micro.models.Rating;
import io.manan.micro.models.UserRatings;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
	
	@GetMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {
		return new Rating(movieId,4);
	}
	
	 
	@GetMapping("/users/{userId}")
	public UserRatings getRatings(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(
				new Rating("1234",4),
				new Rating("5678",3)
				);
		
		UserRatings userRating = new UserRatings();
		userRating.setRatings(ratings);
		
		return userRating;
		
	}
	
}
