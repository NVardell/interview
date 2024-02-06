package com.interview._2021.amazon;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * NAME - Code Question 2
 *
 * DETAILS
 *      - Amazon Web Services (AWS) has several data centers which have multiple processors
 *      that perform computations. In one such data center, these processors are placed in
 *      a sequence with their IDs denoted by 1, 2, .., n. Each processor consumes a certain
 *      amount of power to boot up, denoted by bootingPower[i]. After booting, a processor
 *      uses processingPower[i] of power to run the processes. For maximum utilization, the
 *      data center wishes to group these processors into clusters. Clusters can only be
 *      formed of processors located adjacent to each other.
 *      For example, processors 2, 3, 4, 5 can form a cluster, but 1, 3, 4 cannot.
 *
 *      The net power consumption of a cluster of k processors (i, i+1, ..., i+k-1) is defined as:
 *          = max(bootingPower[i], bootingPower[i+1], ..., bootingPower[i+k-1])
 *            + sum(processingPower[i], processingPower[i+1], ..., processingPower[i+k-1])
 *            * k Clusters
 *      That is, net power consumption = maximum booting power among the k processors
 *          + (sum of processing power of processors) * k.
 *
 *      A cluster of processors is said to be sustainable if its net power consumption does
 *      not exceed a given threshold value powerMax. Given the booting power consumption and
 *      the processing power consumption of n processors denoted by bootingPower and
 *      processingPower respectively, and the threshold value powerMax, find the maximum
 *      possible number of processors which can be grouped together to form a sustainable cluster.
 *
 * NOTES
 *      - It is not mandatory for all clusters of size k to be sustainable.
 *      - If no clusters can be formed, return 0.
 *
 * CONSTRAINTS
 *      - 1 ≤ n ≤ 10⁵
 *      - 1 ≤ powerMax ≤ 10¹⁴
 *      - 1 ≤ processingPower[i] ≤ 10⁴
 *      - 1 ≤ bootingPower[i] ≤ 10⁹
 *      - Length of processingPower = length of bootingPower
 *
 * INPUTS / OUTPUTS
 *      - IN
 *          + processingPower (List<Integer>)
 *              - Power consumption of each processor running processes
 *          + bootingPower (List<Integer>)
 *              - Power consumption of each processor booting up
 *          + powerMax (Long)
 *              - Threshold power consumption
 *      - OUT
 *          + Integer
 *              - Maximum cluster sustainable size
 *              - If no cluster exists, return 0.
 *
 * EXAMPLE #1
 *      In ~ processingPower = [2, 1, 3, 4, 5]
 *           bootingPower = [3, 6, 1, 3, 4]
 *           powerMax = 25
 *      Out ~ 3
 *      Note ~ If k = 2, any adjacent pair can be chosen. The highest usage is [4, 5]
 *          with net power consumption = 4 + (4 + 5) * 2 = 22.
 *          Next, try k = 3.
 *              • Maximum booting power = max(3, 6, 1) = 6
 *              • Sum of processing powers = 2 + 1 + 3 = 6
 *              • Thus, net power consumption = 6 + 6 * 3 = 24 <= powerMax
 *          Thus, we can group k = 3 processors to form a sustainable cluster.
 *          Note that the minimum power consumption to form a cluster of k = 4 processors
 *          is 46, by forming a cluster of the first 4 processors. Since this cost is
 *          greater than the threshold, we cannot form a cluster with 4 processors.
 *          The maximum possible cluster size is 3.
 *
 *
 * EXAMPLE #2
 *      In ~ processingPower = [4, 1, 4, 5, 3]
 *           bootingPower = [8, 8, 10, 9, 12]
 *           powerMax = 33
 *      Out ~ 2
 *      Note ~ Here processingPower= [4, 1, 4, 5, 3], bootingPower= [8, 8, 10, 9, 12] and
 *          powerMax= 33. We can form a cluster of size k = 2, consisting of the first 2
 *          processors. The net power consumption is = max(8, 8) + (4 + 1) *2 = 18,
 *          which is less than the threshold. There are other clusters of size 2 which can
 *          be formed as well. However, the minimum net power consumption for a cluster of
 *          size 3 is 37 (the first 3 processors, max(8, 8, 10) + (4+ 1 +4) *3 = 37).
 *          This is greater than the threshold (33), thus not sustainable.
 *          The maximum possible sustainable cluster size is 2.
 *
 * EXAMPLE #3
 *      In ~ processingPower = [10, 8, 7]
 *           bootingPower = [11, 12, 19]
 *           powerMax = XXX
 *      Out ~ 0
 *      Note ~ It is not possible to form any sustainable cluster for the given processors
 *          with net power consumption less than or equal to powerMax, thus the answer is 0.
 *
 * @author NV
 * @since 12/30/2021
 */
public class Amazon_4 {

    /**
     * Complete the 'maxSubarrayLength' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY badges as parameter.
     */
    private static int findMaxClusterSize(List<Integer> processingPower, List<Integer> bootingPower, long powerMax) {
        int k = processingPower.size();

        // Start off with clusters = # of processors
        int cluster = k;

        // Test different cluster sizes on processors
        while(cluster >= 2) {

            // Loop to test if cluster can be formed with power value less than threshold
            for (int i = 0; i < k; i++) {

                // Break before index out of bounds exception throws
                if(i+cluster > k) {
                    break;
                } else {
                    int powSum = 0, bootMax = 0;

                    // Calculate total processing power & compare it to threshold
                    for (int j = i; j < cluster+i; j++) {
                        powSum+=processingPower.get(j);
                        bootMax = Math.max(bootMax, bootingPower.get(j));
                    }

                    if(powSum * cluster + bootMax <= powerMax) {
                        // Returning largest possible cluster size (Since we started off with all and worked our way down.)
                        return cluster;
                    }
                }
            }
            // Decrement cluster size & reiterate with smaller cluster size
            cluster--;
        }

        return 0;
    }

    @Test public void testSolution_exampleOne() {
        assertThat(findMaxClusterSize(List.of(2, 1, 3, 4, 5), List.of(3, 6, 1, 3, 4), 25L), is(3));
    }
    @Test public void testSolution_exampleTwo() {
        assertThat(findMaxClusterSize(List.of(4, 1, 4, 5, 3), List.of(8, 8, 10, 9, 12), 33L), is(2));
    }
    @Test public void testSolution_exampleThree() {
        assertThat(findMaxClusterSize(List.of(10, 8, 7), List.of(11, 12, 19), 6L), is(0));
    }
}
