package com.practice.interview.streams.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
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

    public static void intersection_of_two_lists() {
        List<Integer> list1 = List.of(1,2,3,4,5);
        List<Integer> list2 = List.of(6,3,2,10, 11);

        List<Integer> union = list1.stream().filter(list2::contains).toList();
        System.out.println(union);
    }

    //Important - iterate streams from seed
    public static void generate_nth_fibonacci() {
        int n = 10;
        List<Integer> list = new ArrayList<>(List.of(0,1));
        IntStream.range(2,n).forEach(i ->
                list.add(list.get(i-1) + list.get(i - 2))
        );

        List<Integer> list2 = Stream.iterate(List.of(0,1), l -> List.of(l.get(1), l.get(0) + l.get(1))
        ).limit(n).map(l -> l.getFirst()).toList();
        System.out.println(list2);
    }

    //Important - Collector.of example (not Collectors utility)
    public static void join_person_names() {
        List<String> list = List.of("Aaba_1", "cab_2");
        String ans = list.stream().collect(
                Collector.of(
                        ArrayList<String>::new,
                        (l, s) -> l.add(s.split("_")[0]),
                        (l1,l2) -> {
                            l1.addAll(l2);
                            return l1;
                        },
                        l -> String.join(",", l)
                )
        );

        System.out.println(ans);
    }

    public static void group_string_by_first_character_and_count() {
        List<String> list = List.of("a1", "b1", "c1", "a2", "b2");
        Map<Character, Long> ans = list.stream().collect(
                Collectors.groupingBy(s -> s.charAt(0), Collectors.counting())
        );

        System.out.println(ans);
    }

    public static void list_to_map() {
        List<String> list = List.of("a1", "b2", "c3", "d4", "e5");
        Map<Character, String> map = list.stream().collect(Collectors.toMap(
                s -> s.charAt(0),
                Function.identity()
        ));

        System.out.println(map);
    }

    public static void multiply_array_elements() {
        Integer [] arr = {1,2,3,4,5};
        int ans = Arrays.stream(arr).reduce( (i, j) -> i*j).get();
        System.out.println(ans);
    }

    public static void main (String [] args) {
        multiply_array_elements();
    }
}
