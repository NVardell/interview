package com.interview._2019.facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Facebook - Live Code Challenge
 *
 * @author Nate Vardell
 * @since 9/23/2019
 */
public class Facebook {

    public static void main(String[] args) {
        int[] a1 = new int[]{1,2,3};
        int[] a2 = new int[]{1,2,3,4,5,6};

        List<Integer> commonElements = getListOfCommonElements(a1, a2);
        System.out.println(commonElements);
    }

    private static List<Integer> getListOfCommonElements(int[] a1, int[] a2) {

        if(a1 == null || a2 == null)
            return Collections.emptyList();

        List<Integer> intersectionsList = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(i < a1.length && j < a2.length) {
            if(a1[i] < a2[j])
                i++;
            else if(a1[i] > a2[j])
                j++;
            else {
                intersectionsList.add(a1[i]);
                i++;
                j++;
            }
        }

        return intersectionsList;
    }

}
