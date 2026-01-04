package com.practice.interview.string;

import javax.naming.PartialResultException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringPracticeSet1 {

    public static void longest_common_prefix_substring() {
        List<String> list = List.of("abc", "abcde", "ab", "abcd", "abcdef");
        final String[] ref = {list.get(0)};

        IntStream.range(1, list.size()).forEach(i -> {
            String s = list.get(i);
            while(!s.startsWith(ref[0])) {
                ref[0] = ref[0].substring(0, ref[0].length() - 1);
            }
        });

        //return ref[0];

        System.out.println(ref[0]);

    }

    public static void first_non_repeated_character_1() {
        String s = "swiss";
        HashMap<Character, int []> map = new HashMap<>();
        IntStream.range(0, s.length()).forEach(i -> {
            char c = s.charAt(i);
            map.putIfAbsent(c, new int [2]);
            int [] v = map.get(c);
            v[0] = i;
            v[1] = v[1] + 1;
        });

        int i = map.values().stream()
                .sorted(
                        Comparator.comparing((int [] arr) -> arr[1])
                                .thenComparing((int [] arr) -> arr[1])
                ).toList().get(0)[0];

        char c = s.charAt(i);
        System.out.println(c);
    }

    public static void first_non_repeated_character_2() {
        String s = "swiss";
        HashMap<Character, int []> map = new HashMap<>();
        PriorityQueue<int []> pq = new PriorityQueue<>(
                Comparator.comparing((int [] arr) -> arr[1])
                        .thenComparing((int [] arr) -> arr[0])
        );
        IntStream.range(0, s.length()).forEach(i -> {
            char c = s.charAt(i);
            map.putIfAbsent(c, new int [2]);
            int [] v = map.get(c);
            v[0] = i;
            v[1] = v[1] + 1;

            if(pq.peek() != null && s.charAt(pq.peek()[0]) == s.charAt(i)) {
                pq.poll();
            }
            pq.offer(v);
        });

        System.out.println(s.charAt(pq.peek()[0]));
    }

    public static void remove_all_whitespaces() {
        String s = "    I   am a       good \n" +
                "  boy";

        // s.replaceAll("\\s", "");
        String s1 = s.chars().filter(i -> !Character.isWhitespace(i)).mapToObj(i -> (char)i).collect(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append
        ).toString();
        System.out.println(s1);
    }

    public static void count_vowels_and_consonants() {
        String s = "Java is fun";
        Set<Character> vowels = Set.of('a','e','i','o','u');
        Map<Boolean, Long> map = s.chars().filter(Character::isLetter).mapToObj(i -> (char) i) //this needs to be done because primitive collectors dont have advanced methods
                .collect( Collectors.partitioningBy(vowels::contains, Collectors.counting()) );

        System.out.println(map);
    }

    public static void sort_strings_by_length_and_flag() {
        List<String> list = List.of("apple", "kiwi", "banana", "fig");
        boolean ASC = false;

        Comparator<String> comp = ASC ? Comparator.comparing(String::length) : Comparator.comparing(String::length, Comparator.reverseOrder());
        List<String> res = list.stream().sorted(comp).toList();
        System.out.println(res);

    }
    public static void main(String[] args) {
        sort_strings_by_length_and_flag();
    }
}
