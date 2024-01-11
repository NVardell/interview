package com.interview._2023.attentive;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NAME
 *      -
 * NOTES
 *      -
 * CONSTRAINTS
 *      -
 * EXAMPLE #1
 *      In ~
 *      Out ~
 *      Note ~
 *
 * @author NV
 * @since 1/10/2024
 */
public class Attentive_1 {

    /**
     *
     * @param gridMap - List of 10 strings (each having 15 characters) defining a 2D grid.
     * @return result - String made with the encountered letters, while following the path.
     */
    private String solution(List<String> gridMap) {
        // Return String
        StringBuilder result = new StringBuilder();

        // Works High Level - Need to Add Logic for following *'s
        for(String s : gridMap) {
            for(char c : s.toCharArray()) {
                if(c != '*' && Character.isLetter((Character)c))
                    result.append(c);
            }
        }

        // Return aggregated path characters string
        return result.toString();
    }

    @Test void testSolution_SimplePath() {
        List<String> gridMap = Arrays.asList(
                "***            ",
                "  *            ",
                "  *            ",
                "  **c**od**i** ",
                "             * ",
                "    e        * ",
                "    *        n ",
                "    **m*a*g**g ",
                "               ",
                "               "
        );
        assertThat(solution(gridMap), is("codinggame"));
    }

    @Test void testSolution_PathTouchesItself() {
        List<String> gridMap = Arrays.asList(
                "***  ui*ck *wn ",
                "  t  *   * o * ",
                "  *  q   b * f ",
                "  *he*   r** * ",
                " *    **p    * ",
                " *    e m    o ",
                " c    * u    x ",
                " *te*d* ***j** ",
                "               ",
                "               "
        );
        assertThat(solution(gridMap), is("thequickbrownfoxjumpedetc"));
    }

    @Test void testSolution_PathOnBorders() {
        List<String> gridMap = Arrays.asList(
                "* o**n*m*lk*j**",
                "*             i",
                "*             h",
                "a             g",
                "              *",
                "              *",
                "b             *",
                "              f",
                "              *",
                "cde************"
        );
        assertThat(solution(gridMap), is("abcdefghijklmno"));
    }

    @Test void testSolution_ShortestPossiblePath() {
        List<String> gridMap = Arrays.asList(
                "*              ",
                "w              ",
                "               ",
                "               ",
                "               ",
                "               ",
                "               ",
                "               ",
                "               ",
                "               "
        );
        assertThat(solution(gridMap), is("w"));
    }

    @Test void testSolution_OnlyLetters() {
        List<String> gridMap = Arrays.asList(
                "aa             ",
                " aaaa          ",
                "    a   aaaa   ",
                "    a   a  a   ",
                "    a   h  a   ",
                "    aaaaa  a   ",
                "           a   ",
                "           a   ",
                "      aaaaaa   ",
                "               "
        );
        assertThat(solution(gridMap), is("aaaaaaaaaaaaaahaaaaaaaaaaaaaaaa"));
    }
}
