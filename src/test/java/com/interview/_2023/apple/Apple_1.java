package com.interview._2023.apple;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NAME
 *      - Smallest String
 * NOTES
 *      - You have a string consisting of zeros, ones, and asterisks.
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

        StringBuilder sb = new StringBuilder(s);
        boolean looping = true, match;

        while(looping) {
            match=false;
            for (int i = 0; i < sb.length() - 1; i++)
                if (sb.charAt(i) == '0' && sb.charAt(i + 1) == '1'
                        || sb.charAt(i) == '1' && sb.charAt(i + 1) == '0') {
                    sb.delete(i, i + 2);
                    match = true;
                    break;
                }

            if(sb.length()==1 || !match)
                looping=false;
        }

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

    @Test void testSolution_4() {
        assertThat(solution("111*010101"), is(4));
    }

    @Test void testSolution_5() {
        assertThat(solution("11010110"), is(2));
    }

    @Test void testSolution_6() {
        assertThat(solution("10*011*00"), is(5));
    }
}
