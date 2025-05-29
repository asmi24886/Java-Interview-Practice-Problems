package com.practice.interview.streams.practice;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPracticeSet2 {

    public static void group_string_by_middle_character() {
        String [] arr = {"ewe", "jji", "jhj", "kwk", "aha"};
        Map<Character, List<String>> map = Arrays.stream(arr).collect(Collectors.groupingBy(str -> str.charAt(str.length()/2)));
        System.out.println(map);
    }

    public static void sum_of_elements() {
        int sum  = List.of(1,2,3,4,5).stream().reduce((a,b) -> a+b).get();
        System.out.println(sum );
    }

    public static void convert_to_squares() {
        List<Integer> ints = List.of(1,2,3,4,5).stream().map(i -> i*i).toList();
        System.out.println(ints);
    }

    public static void union_of_two_list_of_integers() {
        List<Integer> list1 = List.of(1,2,3,4,5);
        List<Integer> list2 = List.of(6,5,4,3,2,1);

        List<Integer> finalList = Stream.concat(list1.stream(), list2.stream()).distinct().toList();

        System.out.println(finalList);
    }

    public static void k_th_smallest_element() {
        List<Integer> list = List.of(7,1,6,2,1,3,4,5);
        int k=3;

        int ans = list.stream().distinct().sorted().skip(k-1).findFirst().get();
        System.out.println(ans);
    }

    public static void remove_non_numeric_characters() {
        List<String> list = List.of("ab123", "1a2b3c", "a1bc23de");

        List<Integer> ansList = list.stream().map(str ->
                Integer.parseInt(
                        str.chars().filter(Character::isDigit).mapToObj(c -> String.valueOf((char)c)).collect(Collectors.joining())
                )
        ).toList();

        System.out.println(ansList);
    }

    public static void find_string_containing_only_digits() {
        List<String> list = List.of("123", "abc", "123", "abc", "45");
        List<String> ansList = list.stream().filter(str ->
                str.chars().allMatch(Character::isDigit)
        ).toList();
        System.out.println(ansList);
    }
    public static void main (String [] args) {
        find_string_containing_only_digits();
    }
}
