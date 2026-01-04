package com.practice.interview.streams.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class StreamPracticeSet4 {

    public static void check_if_number_appears_twice() {
        int [] arr = new int [] {1,2,3,4,5,4};

        // Can also use distinct and count for stream
        System.out.println(arr.length == Arrays.stream(arr).collect(HashSet<Integer>::new, HashSet<Integer>::add, HashSet::addAll).size());
    }
    public static void main(String[] args) {
        check_if_number_appears_twice();
    }
}
