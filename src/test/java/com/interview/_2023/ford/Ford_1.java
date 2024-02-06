package com.interview._2023.ford;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NAME
 *      - Bingo
 * NOTES
 *      - In the popular game of Bingo, a player has a card represented by a matrix
 *        of size n x m that contains all the numbers from the range 1 to n x m.
 *      - The numbers are called out from an array 'arr' of all numbers from 1 to n x m,
 *        in sequential order starting from the first element.
 *      - When a number is called out, the player marks the corresponding number on the game card.
 * PROBLEM
 *      - Find the first number in the sequence at which either a row or column is completely marked.
 * EXAMPLE #1
 *      In ~   mat = [[3, 1, 8],
 *                    [4, 6, 2],
 *                    [7, 5, 9]]
 *             arr = [5, 4, 8, 7, 6, 1, 9, 2, 3]
 *      Out ~  1
 *      Note ~ The 2nd column is completely marked after 1 is called, return 1.
 *
 * @author NV
 * @since 1/3/2024
 */
public class Ford_1 {

    private int solution(List<List<Integer>> board, List<Integer> arr) {

        int length = board.size(), width = board.get(0).size();

        int[] columns = new int[length], rows = new int[width];

        int[][] bingo = new int[length][width];

        for(int x : arr) {

            System.out.println("looking for x=" + x);
            for(int i=0; i<length; i++) {
                System.out.println("testing row#" + i);
                List<Integer> tempList = board.get(i);
                for(int j=0; j<width; j++) {
                    System.out.println("testing row#" + i + "\tj=" + j + "\tint=" + tempList.get(j));
                    if(x == tempList.get(j))
                        bingo[i][j] = 1;
                }
            }

            if(checkBoard(length, width, bingo))
                return x;
        }



        return 0;
    }

    private boolean checkBoard(int length, int width, int[][] board) {

        System.out.println("CHECKING BOARD\n\n");

        boolean test = false;

        // Check rows
        for(int[] row : board) {
            int sum = Arrays.stream(row).boxed()
                    .reduce(0, Integer::sum);
            System.out.println(Arrays.toString(row) + "\t" + sum);
            if(sum == width)
                return true;
        }

        // Check columns
        for(int x=0; x<width; x++) {

            int sum = 0;

            for (int y=0; y<length; y++) {
                sum+=board[y][x];
            }

            System.out.println(sum);

            if(sum == length)
                return true;
        }

        return false;
    }

    @Test void testSolution_1() {
        assertThat(solution(Arrays.asList(
                                Arrays.asList(3, 1, 8),
                                Arrays.asList(4, 6, 2),
                                Arrays.asList(7, 5, 9)),
                        Arrays.asList(5, 4, 8, 7, 6, 1, 9, 2, 3)),
                is(1));
    }

    @Test void testSolution_2() {
        assertThat(solution(Arrays.asList(
                                Arrays.asList(1, 6),
                                Arrays.asList(2, 4),
                                Arrays.asList(5, 3)),
                        Arrays.asList(2, 4, 3, 1, 5, 6)),
                is(4));
    }
}
