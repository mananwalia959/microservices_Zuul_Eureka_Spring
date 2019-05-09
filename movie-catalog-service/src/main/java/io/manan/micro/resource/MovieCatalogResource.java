package io.manan.micro.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.manan.micro.models.CatalogItem;
import io.manan.micro.models.Movie;
import io.manan.micro.models.UserRatings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient.Builder webClientBuilder;
	
	@GetMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){ 
		
		UserRatings ratings = restTemplate.getForObject("http://movie-ratings-data-service/ratingsdata/users/"+ userId, UserRatings.class);		
		
		return ratings.getRatings().stream().map(rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+ rating.getMovieId(), Movie.class);
			
//			Movie movie = webClientBuilder
//					.build()
//					.get()
//					.uri("http://localhost:8082/movies/"+ rating.getMovieId())
//					.retrieve()
//					.bodyToMono(Movie.class)
//					.block();
//					
			return new CatalogItem(movie.getName(),"Test",rating.getRating());
			
		} )
		.collect(Collectors.toList());
				
		
//		return Collections.singletonList(
//				new CatalogItem("TransFormers","Test",4)
//				);
		
		
	}
}
