package com.interview._2023.spokeo;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NAME
 *      - Equal Team Size
 * NOTES
 *      - HackerRank is organizing a hackathon for all its employees.
 *      - A Hackathon is a team event, and there are 'n' teams taking part.
 *      - The number of employees in the ith team is denoted by teamSize[i].
 *      - In order to maintain uniformity, the team size of at most 'k' teams can be reduced.
 *      - Find the maximum number of teams of equal size that can be formed
 *        if team size is reduced optimally.
 * CONSTRAINTS
 *      -
 * EXAMPLE #1
 *      In ~ teamSize = [ 1, 2, 2, 3, 4 ]    k = 2
 *      Out ~ 4
 *      Note ~ The team size of two teams can be reduced to 2.
 *             Thus, teamSize = [ 1, 2, 2, 2, 2 ]
 *             Max # teams with equal size = 4.
 *
 * @author NV
 * @since 1/3/2024
 */
public class Spokeo_1 {

    private int solution(List<Integer> teams, int k) {

        int totalTeams = teams.size();

        Map<Integer, Integer> teamSize = new HashMap<>();

        if(k>=totalTeams)
            return totalTeams;

        for(int x : teams) {
            if (teamSize.get(x) != null) {
                int temp = teamSize.get(x);
                teamSize.put(x, ++temp);
            } else
                teamSize.put(x, 1);
        }

        int max = 0;
        for(Map.Entry<Integer, Integer> e : teamSize.entrySet()) {
            System.out.println("Key = " + e.getKey() +
                    ", Value = " + e.getValue());
            if(e.getValue() > max)
                max = e.getValue();
        }

        return max+k;
    }

    @Test void testSolution_1() {
        assertThat(solution(Arrays.asList(1, 2, 2, 3, 4), 2), is(4));
    }

    @Test void testSolution_2() {
        assertThat(solution(Arrays.asList(1, 2, 3, 4), 1), is(2));
    }

    @Test void testSolution_3() {
        assertThat(solution(Arrays.asList(1, 2, 3, 4, 5, 6, 7), 10), is(7));
    }

}
