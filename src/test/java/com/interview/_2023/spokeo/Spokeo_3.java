package com.interview._2023.spokeo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.interview.common.outlet.FoodOutlet;
import com.interview.common.outlet.FoodOutlets;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NAME
 *      - REST API: The Best Restaurant
 * NOTES
 *      - Use the HTTP GET method to retrieve information from a database of restaurant information.
 *        Query https://jsonmock.hackerrank.com/api/food_outlets to find all the records.  The query
 *        result is paginated and can be further accessed by appending to the query string '?page=num'
 *        where 'num' is the page number.
 *      - The response is a JSON object with the following 5 fields:
 *          + page:        The current page of the results. (Number)
 *          + per_page:    The maximum number of results returned per page. (Number)
 *          + total:       The total number of results. (Number)
 *          + total_pages: The total number of pages with results. (Number)
 *          + data:        Empty array or single object array that contains the food outlets' records.
 *      - In data, each food outlet has the following schema:
 *          + id:             Outlet id (Number)
 *          + name:           The name of the outlet (String)
 *          + co:             The city in which the outlet is located (String)
 *          + estimated_cost: The estimated cost of the food in the particular outlet (Number).
 *          + user_rating:    An object containing the user ratings for the outlet.
 *              - average_rating: The average user rating for the outlet (Number)
 *              - votes:          The number of people who voted for the outlet (Number)
 * PROBLEM
 *      - Given the city name, city, and estimated_cost cost as function arguments...
 *      - Find the food outlet in the city that has the highest rating and whose estimated_cost is at most cost.
 *          + If there are multiple restaurants that tie for the highest rating,
 *            return the lexicographically smallest of them.
 * EXAMPLE #1
 *      In ~  Seattle, 120
 *      Out ~ TBC Sky Lounge
 *
 * @author NV
 * @since 1/3/2024
 */
public class Spokeo_3 {

    private static final String URL = "https://jsonmock.hackerrank.com/api/food_outlets?";

    /**
     * Given a city & cost, return the best restaurant in town!
     *
     * API URL: https://jsonmock.hackerrank.com/api/food_outlets?page={page_number}&city={city}
     * API Example
     *          HTTP GET - https://jsonmock.hackerrank.com/api/food_outlets?city=chicago&page=4
     *                  {
     *                      "page": 4,
     *                      "per_page": 10,
     *                      "total": 40,
     *                      "total_pages": 4,
     *                      "data": [
     *                      {
     *                          "id": 531, "city": "Chicago",
     *                          "name": "Publiq", "estimated_cost": 140,
     *                          "user_rating": { "average_rating": 4, "votes": 1426 }
     *                      },
     *                      ...,
     *                      {
     *                          "id": 540, "city": "Chicago",
     *                          "name": "Effingut Brewerkz", "estimated_cost": 170,
     *                          "user_rating": { "average_rating": 4.6, "votes": 2421 }
     *                      }]
     *                  }
     *
     * @param city - Name of the city whose outlets have to be filtered
     * @param cost - Max estimated cost of an outlet
     * @return restaurant - Highest rating @ cost
     */
    public static String bestRestaurant_v1(String city, int cost) throws IOException, URISyntaxException, InterruptedException {

        String bestRestaurant = "";
        double bestRestaurantRating = 0;
        int page = 1, totalPages = 1;

        // Iterate GET responses for every result page
        while(page <= totalPages) {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(new URI(URL
                            + "city=" + city
                            + "&page=" + page++))
                    .GET()
                    .build();

            HttpResponse<String> res = HttpClient.newHttpClient()
                    .send(req, HttpResponse.BodyHandlers.ofString());

            // Convert GET req to workable JSON Object & set total pages
            JsonObject jsonRes = new Gson().fromJson(res.body(), JsonObject.class);
            totalPages = jsonRes.get("total_pages").getAsInt();

            // Extract array of food outlets (restaurants)
            JsonArray data = jsonRes.getAsJsonArray("data");

            // Iterate list of restaurants
            for(JsonElement e : data) {
                String currentRestaurantName = e.getAsJsonObject().get("name").getAsString();
                int estimatedCost = e.getAsJsonObject().get("estimated_cost").getAsInt();
                double rating = e.getAsJsonObject().get("user_rating").getAsJsonObject().get("average_rating").getAsDouble();

                // Compare estimate to input cost & set best restaurant
                if(estimatedCost <= cost) {
                    if(bestRestaurantRating < rating) {
                        bestRestaurantRating = rating;
                        bestRestaurant = currentRestaurantName;
                    }
                    else if(bestRestaurantRating == rating) {
                        // Compare restaurant names to find lexicographical/alphabetical precedence
                        // (This is dumb. Should be outlet with most votes. Logically, that makes the most sense.)
                        if(0 < bestRestaurant.compareTo(currentRestaurantName)) {
                            bestRestaurantRating = rating;
                            bestRestaurant = currentRestaurantName;
                        }
                    }
                }
            }
        }

        return bestRestaurant;
    }

    @Test void testStuff_v1() throws IOException, URISyntaxException, InterruptedException {
        assertThat(bestRestaurant_v1("Seattle", 120), is("TBC Sky Lounge"));
        assertThat(bestRestaurant_v1("Austin", 10), is("Kadak Bhagat"));
        assertThat(bestRestaurant_v1("Chicago", 150), is("AB's - Absolute Barbecues"));
    }



    public static String bestRestaurant_v2(String city, int cost) throws IOException, URISyntaxException, InterruptedException {

        String bestRestaurant = "";
        int page = 1, totalPages = 1;
        double bestRestaurantRating = 0;

        while(page <= totalPages) {
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(new URI(URL
                            + "city=" + city
                            + "&page=" + page++))
                    .GET()
                    .build();

            HttpResponse<String> res = HttpClient.newHttpClient()
                    .send(req, HttpResponse.BodyHandlers.ofString());

            FoodOutlets foodOutlets = new Gson().fromJson(res.body(), FoodOutlets.class);
            totalPages = foodOutlets.getTotal_pages();

            for(FoodOutlet restaurant : foodOutlets.getData()) {
                if(cost >= restaurant.getEstimated_cost()) {
                    double restRating = restaurant.getUser_rating().getAverage_rating();

                    if(bestRestaurantRating < restRating) {
                        bestRestaurantRating = restRating;
                        bestRestaurant = restaurant.getName();
                    } else if(bestRestaurantRating == restRating) {
                        if(0 < bestRestaurant.compareTo(restaurant.getName())) {
                            bestRestaurantRating = restRating;
                            bestRestaurant = restaurant.getName();
                        }
                    }
                }
            }
        }

        return bestRestaurant;
    }

    @Test void testStuff_v2() throws IOException, URISyntaxException, InterruptedException {
        assertThat(bestRestaurant_v2("Seattle", 120), is("TBC Sky Lounge"));
        assertThat(bestRestaurant_v2("Austin", 10), is("Kadak Bhagat"));
        assertThat(bestRestaurant_v2("Chicago", 150), is("AB's - Absolute Barbecues"));
    }
}
