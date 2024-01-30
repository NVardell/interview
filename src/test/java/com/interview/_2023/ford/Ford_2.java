package com.interview._2023.ford;

import com.google.gson.Gson;
import com.interview.common.outlet.FoodOutlet;
import com.interview.common.outlet.FoodOutlets;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NAME
 *      - Finest Food Outlets
 * NOTES
 *      - Use the HTTP GET method to retrieve information form a database of food outlets.
 *      - Query https://jsonmock.hackerrank.com/api/food_outlets?city={city}
 *      - The query result is paginated.
 *      - To access additional pages, append '&page={page#}' to the URL
 * PROBLEM
 *      - Given the city name & minimum vote count, find the food outlet with the highest rating
 *        whose vote count is greater than or equal to the required minimum votes.
 *      - In case of a ratings tie, return the one with the most votes.
 * EXAMPLE #1
 *      In ~   City=Seattle  votes=500
 *      Out ~  Cafe Juanita
 *      Note ~ In Seattle, results are filtered to those with votes >= 500.
 *             There are 4 food outlets whose rating is 4.9.
 *             Cafe Juanita has 16203 votes.
 *
 * @author NV
 * @since 1/3/2024
 */
public class Ford_2 {

    private static final String URL = "https://jsonmock.hackerrank.com/api/food_outlets?";

    /**
     * Retrieve restaurant w/ highest rating given a city & min vote count
     *
     * @param city  - Name of the city whose outlets have to be filtered
     * @param votes - Min # of votes required
     * @return string - Name of best restaurant
     */
    @SneakyThrows private String bestRestaurant(String city, int votes) {

        int page = 0, totalPages = 0, bestRestaurantVotes = 0;
        String bestRestaurantName = "";
        double bestRestaurantRating = 0;

        // Iterate all GET response pages
        while(page <= totalPages) {

            // Setup HTTP GET Request
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(new URI(URL
                            + "city=" + city
                            + "&page=" + page++))
                    .GET()
                    .build();

            // Send HTTP GET request
            HttpResponse<String> res = HttpClient.newHttpClient()
                    .send(req, HttpResponse.BodyHandlers.ofString());

            // Extract food outlets from HTTP GET response & set total pages
            FoodOutlets foodOutlets = new Gson().fromJson(res.body(), FoodOutlets.class);
            totalPages = foodOutlets.getTotal_pages();

            // Iterate food outlets for current page
            for(FoodOutlet restaurant : foodOutlets.getData()) {
                int restaurantVotes = restaurant.getUser_rating().getVotes();
                double restaurantRating = restaurant.getUser_rating().getAverage_rating();

                // Check that current restaurant has required votes & test rating
                if(votes <= restaurantVotes) {
                    if(bestRestaurantRating < restaurantRating) {
                        bestRestaurantVotes = restaurantVotes;
                        bestRestaurantName = restaurant.getName();
                        bestRestaurantRating = restaurantRating;
                    } else if(bestRestaurantRating == restaurantRating && bestRestaurantVotes < restaurantVotes) {
                        bestRestaurantVotes = restaurantVotes;
                        bestRestaurantName = restaurant.getName();
                        bestRestaurantRating = restaurantRating;
                    }
                }
            }
        }

        return bestRestaurantName;
    }

    @Test void testSolution() {
        assertThat(bestRestaurant("Seattle", 500), is("Cafe Juanita"));
        assertThat(bestRestaurant("Chicago", 4000), is("AB's - Absolute Barbecues"));
        assertThat(bestRestaurant("Boston", 750), is("Uncle Jack's"));
        assertThat(bestRestaurant("Miami", 453), is("Pirates of Grill"));
    }
}
