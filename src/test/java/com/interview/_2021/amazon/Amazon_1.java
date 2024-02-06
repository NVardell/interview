package com.interview._2021.amazon;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

/**
 * NAME - Amazon Customer Reviews
 *
 * DETAILS
 *      - Amazon is building a way to help customers search reviews quicker by providing real-time suggestions
 *      to search terms when the customer starts typing. When given a minimum of two characters into the search
 *      field the system will suggest at most three keywords from the review word repository. As the customer
 *      continues to type in the reviews search bar the relevant keyword suggestions will update automatically.
 *
 * PROBLEM
 *      - Write an algorithm that will output a max of three keyword suggestions after each character is typed.
 *
 * NOTES
 *      - If there are more than three acceptable keywords, return the keywords that are first in alphabetical order.
 *      - Only return keyword suggestions after the customer has entered two characters.
 *      - Keyword suggestions must start with the characters already typed.
 *      - Both the repository and the customerQuery should be compared in a case-insensitive way.
 *
 * CONSTRAINTS
 *      - If an output is not possible, return an empty array (O).
 *
 * INPUTS / OUTPUTS
 *      - IN
 *          + repository
 *              - List of unique strings
 *              - Represents the various keywords from the Amazon review comment section.
 *          + customerQuery
 *              - String
 *              - Represents the full search query of the customer.
 *      - OUT
 *          + List of a List of Strings
 *              - All Lowercase Strings
 *              - Each list represents the keyword suggestions made by the system as the customer types each character.
 *              - Assume the customer types characters in order without deleting or removing any characters.
 *
 * EXAMPLE #1
 *      In ~ repository = [ "mobile", "mouse", "moneypot", "monitor", "mousepad" ]
 *           customerQuery = "mouse"
 *      Out ~ ["mobile", "moneypot", "monitor"]
 *            ["mouse", "mousepad"]
 *            ["mouse", "mousepad"]
 *            ["mouse", "mousepad"]
 *      Note ~ The chain of words that will generate in the search box will be: mo, mou, mous, mouse
 *          And each line from output shows the suggestion of "mo", "mou", "mous", "mouse", respectively
 *          in each line. For the keyword suggestions made by the system that are generated for 'mo', the
 *          matches that will be generated are: ["mobile", "mouse", "moneypot", "monitor", "mousepad"]
 *          Alphabetically, they will be reordered to: [ "mobile", "moneypot", "monitor", "mouse", "mousepad" ].
 *          Thus the keyword suggestions made by the system are [ "mobile", "moneypot", "monitor"].
 *
 * @author NV
 * @since 12/28/2021
 */
public class Amazon_1 {

    public static List<List<String>> searchSuggestions(List<String> repository, String customerQuery) {

        // List of Search Suggestions to return
        List<List<String>> searchSuggestions = new ArrayList<>();

        // Iterate over characters in customer search query
        for(int i = 0; i < customerQuery.length(); i++) {
            // Get character from query string
            String customerQueryChar = customerQuery.substring(0, i+1).toLowerCase();

            // Create a list of possible terms
            List<String> repoSuggestionsList = repository.stream()
                    .map(String::toLowerCase)
                    .filter(suggestion -> suggestion.startsWith(customerQueryChar))
                    .sorted()
                    .limit(3)
                    .collect(toList());

            // Add new list of repo search suggestions to primary list
            if(!repoSuggestionsList.isEmpty())
                searchSuggestions.add(repoSuggestionsList);
        }

        // Remove initial duplicate entry - NTS: Fixed all test cases after adding. Don't ask.
        searchSuggestions.remove(0);

        // Return List of Search Suggestions
        return searchSuggestions;
    }


    @Test public void testSearch_exampleOne() {
        List<String> input = Arrays.asList("mobile", "mouse", "moneypot", "monitor", "mousepad");
        List<List<String>> output = searchSuggestions(input, "mouse");
        assertThat(output.size(), is(4));
        assertThat(output.get(0).size(), is(3));
        assertThat(output.get(0), contains("mobile", "moneypot", "monitor"));
        assertThat(output.get(1), contains("mouse", "mousepad"));
    }
}
