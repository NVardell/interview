package com.interview._2019.amazon;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Sample Amazon Interview Question - From Sebs
 *
 * @author Nate Vardell
 * @since 8/24/2019
 */
public class Amazon {
    static int[] twoSum(int[] nums, int target) {

        if(nums == null || nums.length < 2)
            return null;

        // Index pair to return
        int[] indexPair = null;

        // Temp Compliment Value
        int compliment;

        // Create map to store song duration compliments & their index in array
        // Key - Compliment (Target - Value)
        // Value - Index of Value in 'nums' array
        Map<Integer, Integer> compliments = new HashMap<>();

        for(int i=0; i<nums.length; i++) {
            compliment = target-nums[i];
            System.out.println("Compliment = " + compliment);
            if(compliments.containsKey(compliment)) {
                System.out.println("Contains Key - " + compliment);
                if(indexPair == null) {
                    indexPair = new int[]{compliments.get(compliment), i};
                    System.out.println("\t\tSetting return pair of - [" + indexPair[0] + ", " + indexPair[1] + "]");
                }
                if(compliment <= nums[indexPair[0]]) {
                    indexPair[0] = compliments.get(compliment);
                    indexPair[1] = i;
                    System.out.println("\t\tSetting NEW return pair of - [" + indexPair[0] + ", " + indexPair[1] + "]");
                }
            } else {
                System.out.println("Adding new compliment to map - [" + compliment + ", " + i + "]");
                compliments.put(nums[i], i);
            }
        }
        return indexPair;
    }


    @Test public void testTwoSum() {
        int[] output = twoSum((new int[]{3, 4, 2, 7, 1, 8, 11, 15}), 9);
        assertThat(output.length, is(2));
        assertThat(output[0], is(4));
        assertThat(output[1], is(5));
    }
}
