package com.interview._2021.amazon;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * NAME - Code Question 1
 *
 * DETAILS
 *      - Amazon Web Services (AWS) offers learning opportunities for computer science
 *      students in a series of courses. Upon completing a course, AWS awards the student
 *      with an electronic learning badge. Before signing up for further courses, a student
 *      assigns each badge a value based on interest:
 *          • 1 means that the student is interested
 *          • -1 means that the student is not interested
 *
 * PROBLEM
 *      - Find a SubArray of maximum length such that the product of all the elements in
 *      the SubArray is 1. A SubArray is a contiguous group of elements in an array.
 *
 * CONSTRAINTS
 *      - 2 ≤ n ≤ 2*10⁵
 *      - badges[i] is 1 or -1
 *      - There will be at least one non-empty SubArray that satisfies the given condition.
 *
 * INPUTS / OUTPUTS
 *      - IN
 *          + badges
 *              - Int Array of size n
 *              - Represents the student's interests in each of the subjects.
 *              - Either 1 or -1
 *      - OUT
 *          + Integer
 *              - Maximum length SubArray with a product of 1
 *
 * EXAMPLE #1
 *      In ~ badges = [1, -1, -1, 1, 1, -1]
 *      Out ~ 5
 *      Note ~ These are a few of the SubArrays whose product is equal to 1:
 *              • Beginning & ending indices (O, 0), length of the SubArray is 1
 *              • Indices (O, 4), length of the SubArray is 5
 *              • Indices (1, 4), length of the SubArray is 4
 *              • Indices (1, 2), length of the SubArray is 2
 *           The maximum SubArray length whose product is equal to 1 is length 5. Return 5.
 *
 * EXAMPLE #2
 *      In ~ badges = [1, -1, -1, -1, 1, 1]
 *      Out ~ 4
 *      Note ~ These are a few of the SubArrays whose product is equal to 1:
 *              • SubArray with indices from (0, 2), length of the SubArray is 3.
 *              • SubArray with indices from (I, 2), length of the SubArray is 2.
 *              • SubArray with indices from (2, 5), length of the SubArray is 4.
 *              • SubArray with indices from (4, 5), length of the SubArray is 2.
 *          The maximum SubArray length whose product is equal to 1 is length 4.
 *
 * EXAMPLE #3
 *      In ~ badges = [-1, 1, -1, 1]
 *      Out ~ 4
 *      Note ~ Here, the optimal solution is to choose the entire array as the SubArray because its product is 1.
 *
 * @author NV
 * @since 12/30/2021
 */
public class Amazon_3 {

    private static int maxSubArrayLength(List<Integer> badges) {
        int maxLength = 0;
        int badgesListSize = badges.size();

        for(int i = 0; i < badgesListSize; i++) {
            int subArraySum = badges.get(i);

            for(int x = i+1; x < badgesListSize; x++) {
                subArraySum *= badges.get(x);

                if(subArraySum == 1) {
                    maxLength = Math.max(maxLength, x - i + 1);
                }
            }
        }

        return maxLength;
    }

    @Test public void testSolution_exampleOne() {
        assertThat(maxSubArrayLength(List.of(1, -1, -1, 1, 1, -1)), is(5));
    }

    @Test public void testSolution_exampleTwo() {
        assertThat(maxSubArrayLength(List.of(1, -1, -1, -1, 1, 1)), is(4));
    }

    @Test public void testSolution_exampleThree() {
        assertThat(maxSubArrayLength(List.of(-1, 1, -1, 1)), is(4));
    }
}
