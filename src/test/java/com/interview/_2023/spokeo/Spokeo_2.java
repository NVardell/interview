package com.interview._2023.spokeo;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * NAME
 *      - Minimum Length Subarray
 * NOTES
 *      - For an array of 'n' positive integers 'arr[n]' and an integer 'k',
 *        a sub-array is 'good' if it consists of at least 'k' distinct integers.
 * PROBLEM
 *      - Find the minimum length subarray that is good.
 *      - If there is no such subarray, return -1.
 * EXAMPLE #1
 *      In  ~ arr=[2, 2, 1, 1, 3]
 *            k=3
 *      Out ~ 4
 *      Note ~ The sub-arrays with at least k=3 distinct integers are...
 *             [2, 2, 1, 1, 3] and [2, 1, 1,3].
 *             Return 4, the minimum length of a good subarray.
 *
 * @author NV
 * @since 1/3/2024
 */
@Log4j2 public class Spokeo_2 {


    /**
     * Find the minimum length of a subarray containing 'k' unique elements.
     *
     * @param arr - Array of integers to partition
     * @param k - # of distinct elements required
     * @return x - Minimum length of 'good' subarray; -1 if none
     */
    private static int findMinimumLengthSubarray(List<Integer> arr, int k) {

        int minLength = -1;         // Subarray length, return value
        int length = arr.size();    // Input integer array size

        // Loop Integer List
        for (int x=0; x<length; x++) {

            // Track current sub-array length
            int currentLength = 1;

            // Create map for distinct # count
            Map<Integer, Integer> map = new HashMap<>();
            map.put(arr.get(x), 1);

            // Loop remaining integer array
            for (int y = x + 1; y < length; y++) {
                int z = arr.get(y);  // Store temp value
                currentLength++;     // Increment current sub-array length

                map.putIfAbsent(z, 1);  // Add # to map it absent

                // If we have enough distinct #'s, set min length
                if (map.size() >= k) {
                    if (minLength == -1)
                        minLength = currentLength;
                    else if (currentLength < minLength)
                        minLength = currentLength;
                }
            }
        }

        return minLength;
    }
    @Test void testStuff() {
        assertThat(findMinimumLengthSubarray(Arrays.asList(1,2), 4), is(-1));
        assertThat(findMinimumLengthSubarray(Arrays.asList(2, 2, 1, 1, 3), 3), is(4));
        assertThat(findMinimumLengthSubarray(Arrays.asList(3, 2, 3, 3, 1, 3), 3), is(4));
    }
}
