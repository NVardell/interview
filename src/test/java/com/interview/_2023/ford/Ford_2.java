package com.interview._2023.ford;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

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

    private static final String URL = "https://jsonmock.hackerrank.com/api/food_outlets?city=";

    /**
     *
     * @param city  - Name of the city whose outlets have to be filtered
     * @param votes - Min # of votes required
     * @return string - Name of best restaurant
     */
    private String bestRestaurant(String city, int votes) {
        return "";
    }

    @Test void testSolution_2() throws IOException, URISyntaxException, InterruptedException {
        assertThat(bestRestaurant("Seattle", 500), is("Cafe Juanita"));
    }
}
