package com.interview._2023.attentive;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NAME
 *      - Follow the Path
 * NOTES
 *      - You are given a list of strings, representing a 2-dimensional grid.
 *      - The first character of the first string is the upper left tile, (0, 0).
 *      - The grid ALWAYS has the same size.
 *          + Width  = 15 (Each string contains 15 characters.)
 *          + Height = 10 (List contains 10 strings.)
 *      - The grid contains a unique path made of stars (*) & lowercase letters.
 *      - The path can move in four directions. (Up, Down, Left, Right.)
 *      - The path may start || end with a letter
 * PROBLEM
 *      - Given a list of strings, follow the path recording & returning a
 *        string containing all the letters encountered along the way.
 *
 * @author NV
 * @since 1/10/2024
 */
class Attentive_1 {

    /**
     * Follow the path and return all of the letters collected along the way!
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
