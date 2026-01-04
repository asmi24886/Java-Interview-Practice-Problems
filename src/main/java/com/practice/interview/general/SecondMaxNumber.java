package com.practice.interview.general;

import java.util.Comparator;
import java.util.List;

public class SecondMaxNumber {
    public static void main(String [] args) {
        System.out.println(solution_stream());
    }

    public static int solution() {
        List<Integer> list = List.of(5, 9, 3, 6, 8, 7);

        int max = Integer.MIN_VALUE;
        int max2 = max;

        for( int i : list ) {
            if(i > max) {
                max2 = max;
                max = i;
            }
            else if(i > max2) {
                max2 = i;
            }
        }

        return max2;
    }

    public static int solution_stream() {
        List<Integer> list = List.of(5, 9, 3, 6, 8, 7);
        return list.stream().sorted(Comparator.reverseOrder()).limit(2).toList().get(1);
    }
}
