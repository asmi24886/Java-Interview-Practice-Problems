package com.practice.interview.streams.set1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPracticeSet1 {

    public static void word_with_max_length () {
        String str = "I am learning string in java";

        int length = Arrays.stream(str.split(" ")).max(Comparator.comparingInt(String::length)).get().length();
        System.out.println(length);

        int length2 = Arrays.stream(str.split(" ")).sorted().toList().getLast().length(); //toArray can also be done

        int length3 = Arrays.stream(str.split(" ")).sorted(Comparator.reverseOrder()).findFirst().get().length();
    }

    public static void remove_duplicates() {
        String str = "dabcadefg";

        String simpleStr = str.chars().distinct().mapToObj(Character::toString).collect(Collectors.joining(""));

        String simpleStr2 = Arrays.stream(str.split("")).distinct().collect(Collectors.joining(""));
        System.out.println(simpleStr);
    }

    public static void character_appearing_only_single_time() {

        //remove any occurenc of character that appears more than 1 tim
        String str = "dabcadefg";

        String newStr = str.chars().collect(
                HashMap<Character, Integer>::new,
                (map, c) -> map.put((char) c, map.getOrDefault((char) c, 0) + 1),
                (map1, map2) -> map2.forEach(
                        (k, v) -> map1.merge(k, v, Integer::sum)
                )
        )
                .entrySet().stream()
                .filter(e -> e.getValue() == 1).map(e -> Character.toString(e.getKey()))
                .collect(Collectors.joining(""));

        String newStr2 = str.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1).collect(Collectors.mapping((e -> e.getKey().toString()), Collectors.joining("")));

        String newStr3 = str.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1).map((e -> e.getKey().toString())).collect(Collectors.joining(""));

        String newStr4 = str.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1).toList().stream().map(e -> e.getKey().toString()).collect(Collectors.joining(""));

        System.out.println(newStr);
    }

    //Important - see how Comparator.comparing can use key extractor and another comparator; better than comparingInt if you have to extract key and stuff
    public static void second_highest_length_string() {
        String str = "I am learning stream api in java";
        String ans = Arrays.stream(str.split(" ")).sorted(Comparator.comparing(String::length, Comparator.reverseOrder())).toList().get(1);

        String ans2 = Arrays.stream(str.split(" ")).sorted( Comparator.comparingInt(String::length).reversed() ).skip(1).findFirst().get();

        System.out.println(ans);
    }

    public static void second_highest_length() {
        String str = "I am learning stream api in java";
        int length = Arrays.stream(str.split(" ")).map(String::length).sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
        System.out.println(length);
    }

    public static void occurrence_of_each_word() {
        String str = "I am learning stream api in java java";
        Map<String, Long> map = Arrays.stream(str.split(" ")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
    }

    public static void main(String [] args) {
        occurrence_of_each_word();
    }
}
