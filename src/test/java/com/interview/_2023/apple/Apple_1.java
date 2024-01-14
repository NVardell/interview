package com.interview._2023.apple;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NAME
 *      - Smallest String
 * NOTES
 *      - You hae a string consisting of zeros, ones, and asterisks.
 *        Consider the following operation:
 *          + Choose any two adjacent positions in the string
 *          + If one of them is 0, and the other is 1, remove them.
 * PROBLEM
 *      - Find the length of the smallest string that can be obtained after
 *        applying this operation multiple times.
 * EXAMPLE #1
 *      In ~ "010"      Out ~ 1
 *
 * @author NV
 * @since 1/14/2024
 */
public class Apple_1 {

    private int solution(String s) {

        int length = s.length();

        StringBuilder sb = new StringBuilder(s);



        return sb.length();
    }

    @Test void testSolution_1() {
        assertThat(solution("01010"), is(1));
    }

    @Test void testSolution_2() {
        assertThat(solution("111000"), is(0));
    }

    @Test void testSolution_3() {
        assertThat(solution("111*000"), is(7));
    }
}
