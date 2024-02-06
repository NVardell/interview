package com.interview._2021.amazon;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NAME - Amazon Fresh Promo
 *
 * PROBLEM
 *      - Amazon Fresh is running a promotion in which customers receive prizes for purchasing a secret
 *      combination of fruits. The combination will change each day, and the team running the promotion
 *      wants to use a code list to make it easy to change the combination. The code list contains
 *      groups of fruits. Both the order of the groups within the code list and the order of the fruits
 *      within the groups matter. However, between the groups of fruits, any number, and type of fruit
 *      is allowable. The term 'anything' is used to allow for any type of fruit to appear in that
 *      location within the group.
 *
 * NOTES
 *      - The term 'anything' in the 'codeList' maintains the following:
 *          + Must exist.
 *          + ANY fruit.
 *          + Single value.
 *
 * CONSTRAINTS
 *      - If 'codeList' is empty; it is assumed the customer is a winner.
 *
 * INPUTS / OUTPUTS
 *      - IN
 *          + codeList
 *              - List of Strings w/ space-separated values
 *              - Represents the order & grouping of specific fruits that must be purchased to win.
 *          + shoppingCart
 *              - List of Strings
 *              - Represents the order in which a customer purchases fruit.
 *      - OUT
 *          + Integer
 *              0 = Loser
 *              1 = Winner
 *
 * EXAMPLE #1
 *      In ~ codeList = [[apple, apple], [banana, anything, banana]]
 *           shoppingCart = [orange, apple, apple, banana, orange, banana]
 *      Out ~ 1
 *      Note ~ The second group contains 'anything' so any fruit can be ordered in place Of 'anything'
 *          in the shoppingCart. The customer is a winner because the groups & the order of groups in
 *          the codeList is also maintained in the shoppingCart.
 * EXAMPLE #2
 *      In ~ codeList = [[apple, apple], [banana, anything, banana]]
 *           shoppingCart = [banana, orange, banana, apple, apple]
 *      Out ~ 0
 *      Note ~ The customer is not a winner as the customer has added the fruits in order of groups but group [banana, orange, banana] is not following the group [apple, apple] in the codeList.
 * EXAMPLE #3
 *      In ~ codeList = [[apple, apple], [banana, anything, banana]]
 *           shoppingCart = [apple, banana, apple, banana, orange, banana]
 *      Out ~ 0
 *      Note ~ The customer is not a winner as the customer has added the fruits in an order which is not following the order of fruit names in the first group.
 * EXAMPLE #4
 *      In ~ codeList = [[apple, apple], [apple, apple, banana]]
 *           shoppingCart = [apple, apple, apple, banana]
 *      Out ~ 0
 *      Note ~ The customer is not a winner as the first 2 fruits form group 1, all three fruits would form group 2, but can't because it would contain all fruits of group 1.
 * EXAMPLE #5
 *      In ~ codeList = [[apple, apple], [banana, anything, banana]]
 *           shoppingCart = [apple, apple, orange, orange, banana, apple, banana, banana]
 *      Out ~ 1
 *      Note ~ The customer is a winner because the groups & the order of groups in the codeList is also maintained in the shoppingCart.
 *
 * @author NV
 * @since 12/28/2021
 */
public class Amazon_2 {

    private static final int WINNER = 1, LOSER = 0;
    private static final String ANYTHING = "anything";

    private static int shoppingCartChallenge(List<String> codeList, List<String> shoppingCart) {

        boolean isFirst = true, anythingIsSet = false;
        int totalCodes = 0, codeGroup = 0, totalGroups = 0;
        String anything = ANYTHING;

        Deque<String> tempCodeGroup = new ArrayDeque<>();
        Deque<String> currentCodeGroup = new ArrayDeque<>();
        List<List<String>> codeListGroups = new ArrayList<>();

        // No codes = Winning
        if(codeList.isEmpty() && !shoppingCart.isEmpty())
            return WINNER;

        // Create Code Groups from Code List
        for (String codes : codeList) {

            // Create New List of Codes, separating on spaces
            List<String> codesGroup = List.of(codes.split(" "));
            codeListGroups.add(codesGroup);

            // Add list size to total codes counter
            totalCodes += codesGroup.size();

            // Increment Total Groups Counter
            totalGroups++;
        }

        // More Codes than items in cart = Losing
        if(totalCodes > shoppingCart.size() || shoppingCart.isEmpty())
            return LOSER;


        // Iterate over items in shopping cart
        for (String cartItem : shoppingCart) {

            if(tempCodeGroup.isEmpty() && codeGroup == totalGroups) {
                return WINNER;
            } else if(tempCodeGroup.isEmpty()) {
                tempCodeGroup.addAll(codeListGroups.get(codeGroup));
                currentCodeGroup.addAll(codeListGroups.get(codeGroup++));;
                isFirst=true;
            }

            String currentCode = tempCodeGroup.peek();

            // If anything has already been used, use its value instead
            if(anythingIsSet && Objects.equals(currentCode, ANYTHING))
                currentCode = anything;

            // Check if Current Code is the first in its group
            if(isFirst) {
                // Check if group code & shopping cart items match
                if(cartItem.equals(currentCode)) {
                    tempCodeGroup.pop();
                    isFirst = false;
                } else if(Objects.equals(currentCode, ANYTHING)) {
                    tempCodeGroup.pop();
                    anything = cartItem;
                    anythingIsSet = true;
                    isFirst = false;
                }
            } else {
                // Check if group code & shopping cart items match
                if(cartItem.equals(currentCode)) {
                    tempCodeGroup.pop();
                } else if(Objects.equals(currentCode, ANYTHING)) {
                    tempCodeGroup.pop();
                    anything = cartItem;
                    anythingIsSet = true;
                } else {
                    tempCodeGroup.clear();
                    tempCodeGroup.addAll(currentCodeGroup);
                }
            }
        }

        return (tempCodeGroup.isEmpty() && codeGroup == totalGroups) ? WINNER : LOSER;
    }


    @Test public void testShoppingCartChallenge_withEmptyLists() {
        assertThat(shoppingCartChallenge(Collections.emptyList(), Collections.emptyList()), is(LOSER));
    }

    @Test public void testShoppingCartChallenge_withEmptyCodeList() {
        assertThat(shoppingCartChallenge(Collections.emptyList(), List.of("Fruity")), is(WINNER));
    }

    @Test public void testShoppingCartChallenge_exampleOne() {
        List<String> inputCodeList = List.of("apple apple", "banana anything banana");
        List<String> inputShoppingCart = List.of("orange", "apple", "apple", "banana", "orange", "banana");

        assertThat(shoppingCartChallenge(inputCodeList, inputShoppingCart), is(WINNER));
    }

    @Test public void testShoppingCartChallenge_exampleTwo() {
        List<String> inputCodeList = List.of("apple apple", "banana anything banana");
        List<String> inputShoppingCart = List.of("banana", "orange", "banana", "apple", "apple");
        assertThat(shoppingCartChallenge(inputCodeList, inputShoppingCart), is(LOSER));
    }

    @Test public void testShoppingCartChallenge_exampleThree() {
        List<String> inputCodeList = List.of("apple apple", "banana anything banana");
        List<String> inputShoppingCart = List.of("apple", "banana", "apple", "banana", "apple", "banana", "orange", "banana");
        assertThat(shoppingCartChallenge(inputCodeList, inputShoppingCart), is(LOSER));
    }

    @Test public void testShoppingCartChallenge_exampleFour() {
        List<String> inputCodeList = List.of("apple apple", "apple apple banana");
        List<String> inputShoppingCart = List.of("apple", "apple", "apple", "banana");
        assertThat(shoppingCartChallenge(inputCodeList, inputShoppingCart), is(LOSER));
    }

    @Test public void testShoppingCartChallenge_exampleFive() {
        List<String> inputCodeList = List.of("apple apple", "banana anything banana");
        List<String> inputShoppingCart = List.of("apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana");
        assertThat(shoppingCartChallenge(inputCodeList, inputShoppingCart), is(WINNER));
    }
}
