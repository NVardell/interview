package com.interview._2019.jpMorgan;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * NAME - Dungeon Escape
 * COMPANY - JPMC - Java Software Engineer
 *
 * DETAILS
 *      - You are lost in a dungeon and need to find the shortest path out.
 *      The dungeon is a grid of R rows and C columns.
 *      It's composed of two types of cells:
 *          • Passable cells where you can move up, down, left, or right
 *          • Impassable cells filled with solid rock
 * PROBLEM
 *      - Write a program to determine if it is possible to escape by finding the
 *      shortest path to the exit.
 *
 * CONSTRAINTS
 *      - You cannot move diagonally between cells.
 *      - If there is no path to escape print 'Trapped'.
 *
 * INPUTS / OUTPUTS
 *      - IN
 *          + A map of the dungeon shown as R lines with C characters in each line.
 *          + Every character corresponds to a cell of the dungeon's grid & designates its properties.
 *              • Passable cells are marked with a period '.'
 *              • Impassable cells are marked with a hash '#'
 *              • The start position is at cell 'S'
 *              • The exit is at cell 'E'
 *      - OUT
 *          + The same map of the dungeon but each '.' character must be replaced with an
 *          asterisk character along the shortest path from the start to the exit.
 *              - Maximum length SubArray with a product of 1
 *
 * EXAMPLE #1
 *      In ~                Out ~
 *        dungeon = [         shortPath = [
 *          ###...##..          ###***##..
 *          ....#....#          ..**#**..#
 *          #..###.#.#          #.*###*#.#
 *          S#.#.#.#..          S#*#.#*#..
 *          ...#...E#.          ***#..*E#.
 *        ]                   ]
 *
 * EXAMPLE #2
 *      In ~                Out ~
 *        dungeon = [         shortPath = [
 *         E##..#.#...         E##..#.#...
 *         .....#...##         *****#...##
 *         ####.###.#.         ####*###.#.
 *         ...........         ....*......
 *         .###.######         .###*######
 *         ..##.#....#         ..##*#.***#
 *         ...#..#.#..         ...#**#*#**
 *         ##..#.#..#.         ##..#*#*.#*
 *         ..#.#...##.         ..#.#***##*
 *         #...#.#..#S         #...#.#..#S
 *      ]                   ]
 *
 * @author Nate Vardell
 * @since 4/23/2021
 */
public class JPMC {

    private static int width, length;
    private static int[] start, finish;
    private static char[][] grid;
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    private static Map<String, int[]> parentNodes;
    private static List<String> lines, lines_expected;

    @Test public void exampleOne_Test() {
        lines = new ArrayList<String>() {{
            add("###...##..");
            add("....#....#");
            add("#..###.#.#");
            add("S#.#.#.#..");
            add("...#...E#.");
        }};
        lines_expected = new ArrayList<String>() {{
            add("###***##..");
            add("..**#**..#");
            add("#.*###*#.#");
            add("S#*#.#*#..");
            add("***#..*E#.");
        }};
        runner(lines, lines_expected);
    }

    @Test public void exampleTwo_Test() {
        lines = new ArrayList<String>() {{
            add("E##..#.#...");
            add(".....#...##");
            add("####.###.#.");
            add("...........");
            add(".###.######");
            add("..##.#....#");
            add("...#..#.#..");
            add("##..#.#..#.");
            add("..#.#...##.");
            add("#...#.#..#S");
        }};
        lines_expected = new ArrayList<String>() {{
            add("E##..#.#...");
            add("*****#...##");
            add("####*###.#.");
            add("....*......");
            add(".###*######");
            add("..##*#.***#");
            add("...#**#*#**");
            add("##..#*#*.#*");
            add("..#.#***##*");
            add("#...#.#..#S");
        }};
        runner(lines, lines_expected);
    }

    @Test public void trappedMap_Test() {
        lines = new ArrayList<String>() {{
            add("#S#.E");
        }};
        lines_expected = new ArrayList<String>() {{
            add("Trapped");
        }};
        runner(lines, lines_expected);
    }

    @Test public void shortMap_Test() {
        lines = new ArrayList<String>() {{
            add("#S..E");
        }};
        lines_expected = new ArrayList<String>() {{
            add("#S**E");
        }};
        runner(lines, lines_expected);
    }

    @Test public void mediumMap_Test() {
        lines = new ArrayList<String>() {{
            add("#S.#E");
            add("##...");
        }};
        lines_expected = new ArrayList<String>() {{
            add("#S*#E");
            add("##***");
        }};
        runner(lines, lines_expected);
    }

    private static void runner(List<String> lines, List<String> lines_expected) {
        if (findPath(createGrid(lines))) {
            addPathToMap(parentNodes);
            validateLines(lines, lines_expected);
        } else {
            assertThat(lines_expected.get(0), is("Trapped"));
        }
    }


    private static char[][] createGrid(List<String> lines) {

        length = lines.size();
        width = lines.get(0).length();
        grid = new char[length][width];

        int x = 0;
        for (String currentLine : lines) {
            for (int y = 0; y < width; y++) {
                char c = currentLine.charAt(y);
                grid[x][y] = c;

                if (c == 'S')
                    start = new int[]{x, y};
                if (c == 'E')
                    finish = new int[]{x, y};
            }
            x++;
        }

        return grid;
    }

    private static boolean findPath(char[][] grid) {
        parentNodes = new HashMap<>();

        boolean[][] visited = new boolean[length][width];
        visited[start[0]][start[1]] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int[] current = q.remove();

            if (grid[current[0]][current[1]] == 'E')
                return true;

            for (int[] neighbor : DIRECTIONS) {

                int newX = current[0] + neighbor[0];
                int newY = current[1] + neighbor[1];

                if (newX >= 0 && newY >= 0
                        && newX < length && newY < width
                        && (grid[newX][newY] == '.' || grid[newX][newY] == 'E')) {

                    if (!visited[newX][newY]) {
                        q.add(new int[]{newX, newY});   // Add unvisited spot to Queue
                        parentNodes.put(Arrays.toString(new int[]{newX, newY}), current);
                        visited[newX][newY] = true;     // Mark new spot as visited
                    }
                }
            }
        }

        return false;
    }

    private static void addPathToMap(Map<String, int[]> parentNodes) {

        int[] temp = parentNodes.get(Arrays.toString(finish));

        while (temp != start) {
            grid[temp[0]][temp[1]] = '*';
            temp = parentNodes.get(Arrays.toString(temp));
        }
    }


    private static void validateLines(List<String> lines, List<String> lines_expected) {
        assertThat(lines.size(), is(lines_expected.size()));

        for (int x = 0; x < length; x++) {
            assertThat(lines_expected, hasItem(String.valueOf(grid[x])));
        }
    }

}
