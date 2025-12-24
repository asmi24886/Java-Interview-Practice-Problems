package com.practice.interview.streams.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamPracticeSet3 {

    public static void convert_list_of_string_to_uppercase_then_concatenate() {
        List<String> list = List.of("i", "am", "a", "good", "boy");
        String res = list.stream().map(String::toUpperCase).collect(Collectors.joining(" "));
        System.out.println(res);
    }

    public static void concat_two_streams() {
        Stream<String> s1 = Stream.of("a", "b");
        Stream<String> s2 = Stream.of("c", "d");
        System.out.println(Stream.concat(s1, s2).toList());
    }

    public static void middle_character_of_string() {
        String s = "education";
        char c = (char) s.chars().skip((s.length() - 1)/2).findFirst().getAsInt();

        //s.charAt(s.length()/2);
        System.out.println(c);
    }

    public static void print_distinct_number_starting_with_1_desc() {
        int [] arr = {12,34,11,34,67,121,121,52,78,114,565,1643,11};
        int [] res = Arrays.stream(arr)
                .filter(it -> (it+"").charAt(0) == '1').distinct() //Could also use String.valueOF(it)
                .boxed().sorted(Comparator.reverseOrder())
                .mapToInt(i->i).toArray();

        System.out.println(Arrays.stream(res).boxed().toList());
    }

    // Imp
    public static void count_of_substring() {
        String s = "byebyeBirdiebye";
        String sub = "bye";
        AtomicInteger i = new AtomicInteger();
        AtomicInteger count = new AtomicInteger();

        s.chars().forEach(c -> {
            char thisChar = (char) c;
            char subChar = sub.charAt(i.get());

            if(thisChar == subChar) i.getAndIncrement();
            if(i.get() == sub.length()) {
                count.getAndIncrement();
                i.set(0);
            }
        });

        System.out.println(count);
    }

    //IMP
    public static void count_of_substring_2() {
        String s = "byebyeBirdiebye";
        String sub = "bye";
        long res = IntStream.range(0, s.length() - sub.length() + 1).filter(i -> {
            return s.substring(i,i + sub.length()).equals(sub);
        }).count();
        System.out.println(res);
    }

    //HARD
    public static void department_with_highest_employees() {
        List<List<String>> list = List.of(
                List.of("A", "HR", "50000"),
                List.of("A", "IT", "70000"),
                List.of("A", "IT", "80000"),
                List.of("A", "FIN", "60000"),
                List.of("A", "IT", "75000"),
                List.of("A", "HR", "55000"),
                List.of("A", "FIN", "65000"),
                List.of("A", "IT", "72000")
        );

        String res = list.stream().collect(
                Collectors.groupingBy(it -> it.get(1), Collectors.counting())
        ).entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println(res);
    }

    public static void department_with_highest_average_salary() {
        List<List<String>> list = List.of(
                List.of("A", "HR", "50000"),
                List.of("A", "IT", "70000"),
                List.of("A", "IT", "80000"),
                List.of("A", "FIN", "60000"),
                List.of("A", "IT", "75000"),
                List.of("A", "HR", "55000"),
                List.of("A", "FIN", "65000"),
                List.of("A", "IT", "72000")
        );

       Map<String, Double> map  = list.stream().collect(
                Collectors.groupingBy(
                        it -> it.get(1),
                        Collectors.averagingInt(it -> Integer.parseInt(it.get(2)) )
                )
        );
        System.out.println(map);
    }

    public static void character_with_maximum_frequency() {
        String s = "javadeveloper";
        char res = s.chars().mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(c -> c , Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println(res);
    }
    public static void map_of_string_with_equivalent_length() {
        List<String> list = List.of("Orange", "Banana", "Kiwi");
        Map<String, Integer> res = list.stream().collect(Collectors.toMap(s -> s, String::length));
        System.out.println(res);
    }
    public static void main(String[] args) {
        map_of_string_with_equivalent_length();
    }
}
