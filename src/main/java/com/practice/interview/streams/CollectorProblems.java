package com.practice.interview.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectorProblems {

    //Stream a string of characters, check and partition by digit and letters
    public static void collector_problem1() {
        String s = "Aa34";

        //Method 1 - Collectors work on objects and boxed types only
        IntStream intStream = s.chars();
        Map<Boolean, List<Integer>> result1 = intStream.boxed().collect(Collectors.partitioningBy(Character::isDigit));

        //Method 2
        Stream<Character> characterStream = s.chars().mapToObj(it -> (char) it); //Important because it deals with primitive type. mapToObject automatically boxes the char stream to Stream<Character>
        //Method 2
        Stream<Character> characterStream1 = Arrays.stream(s.split("")).map(it -> it.charAt(0));

        Map<Boolean, List<Character>> result = characterStream.collect(Collectors.partitioningBy(Character::isDigit));

        System.out.println(result.entrySet());
    }

    //Collector averaging double, counting, summarizing double example
    public static void collector_problem2() {
        Double result = Stream.iterate(2.0, it -> it*2).limit(5).collect(Collectors.averagingDouble(value -> value));
        Long result2 = Stream.iterate(2.0, it -> it*2).limit(5).collect(Collectors.counting());
        DoubleSummaryStatistics result3 = Stream.iterate(2.0, it -> it*2).limit(5).collect(Collectors.summarizingDouble(value -> value));
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
    }

    //Grouping by simple, with mapping, with MapFactory/reduction, toMap, joining, reducing
    public static void collector_problem3() {

        System.out.println(Stream.of(1,2,3,4,5).collect(Collectors.groupingBy(i -> i % 2)));
        //Grouping and Mapping
        System.out.println(Stream.of(1,2,3,4,5).collect( Collectors.groupingBy( integer -> integer % 2, Collectors.mapping( i-> i*10, Collectors.toList() ) ) ));
        LinkedHashMap<Integer, Double> result = Stream.of(1, 2, 3, 4, 5).collect(Collectors.groupingBy(integer -> integer % 2, LinkedHashMap::new, Collectors.averagingInt(i -> i)));
        System.out.println(result); //Limitation of type inference

        System.out.println(Stream.of(1,2,3,4,5).collect(Collectors.toMap(k -> k, v ->  (char) v.intValue())));

        System.out.println(Stream.of(1,2,3,4,5).collect(Collectors.reducing(10, (r, i) -> r * i)));

        //Only String.valueOf checks null safety
        System.out.println(Stream.of(1,2,3,4,5).map(i -> Integer.toString(i)).collect(Collectors.joining(" - ")));
    }

    //Binary search, can also be done on Arrays.binarySearch(T[] array, T key, Comparator<T> comparator)
    public static void collector_binary_search() {

        List<Integer> result = Stream.of(1, 2, 3, 4, 5).toList();
        System.out.println( Collections.binarySearch(result, 4) );

        List<Boxed> result1 = Stream.of(1, 2, 3, 4, 5).map(Boxed::new).toList();
        System.out.println( Collections.binarySearch(result1, new Boxed(4), Comparator.comparingInt(bx -> bx.value)) ); //Can also be done lime (bx1, bx2) -> bx1.value - bx2.value

        List<Boxed2> result2 = Stream.of(1, 2, 3, 4, 5).map(Boxed2::new).toList();
        System.out.println( Collections.binarySearch(result2, new Boxed2(4)) ); //Using comparable
    }

    //Using comparators
    public static void collector_sorting() {
        List<Integer> result = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        result.sort(Comparator.reverseOrder());
        System.out.println(result);

        List<Integer> result2 = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        result2.sort(Comparator.comparing(i -> i));
        System.out.println(result2);

        List<Integer> result3 = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());
        result3.sort((i, j) -> j - i); //reverse order //natural sorting on collections
        System.out.println(result3);

        //Boxed sort with 3 arguments :)
        List<Boxed> result4 = Stream.of(1, 2, 3, 4, 5).map(Boxed::new).collect(Collectors.toList());
        result4.sort(Comparator.comparing(bx -> bx.value, Comparator.reverseOrder()));
        System.out.println(result4);

        //Boxed sort with comparable
        List<Boxed2> result5 = Stream.of(1, 2, 3, 4, 5).map(Boxed2::new).collect(Collectors.toList());
        result5.sort(Comparator.reverseOrder());
        System.out.println(result5);

        //Can also be done with Collections class which is kind of useless but ok
        List<Boxed> result6 = Stream.of(1, 2, 3, 4, 5).map(Boxed::new).collect(Collectors.toList());
        Collections.sort(result6, Comparator.comparing(bx -> bx.value, Comparator.reverseOrder()));
        System.out.println(result4);
    }

    public static void collector_character_stream_simple() {
        String input = "aabbcccddeeeffff";
        Map<Character, Long> map = input.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Character, Long> filteredMap = map.entrySet().stream().filter(e -> e.getValue() % 2 == 0).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
        System.out.println(filteredMap);
    }

    public static void collector_character_stream_then_counting() {
        String input = "aabbcccddeeeffff";
        Map<Character, Long> filteredMap = input.chars().mapToObj(i -> (char) i).collect(
                        Collectors.collectingAndThen(
                                Collectors.groupingBy(Function.identity(), Collectors.counting()),
                                map -> map.entrySet().stream().filter(e -> e.getValue() % 2 == 0).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                        )
                );
    }

    public static void collector_character_stream_flatmap() {
        String input = "aabbcccddeeeffff";
        Map<Character, Long> filteredMap = input.chars().mapToObj(i -> (char) i).collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting())
        )
                .entrySet().stream()
                .flatMap(e -> e.getValue() % 2 == 0 ? Stream.of(e) : Stream.empty())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static void flatmap_on_lists() {
        List<List<Integer>> lists = List.of(List.of(1, 2), List.of(3, 4));
        List<Integer> finalList = lists.stream().flatMap(l -> l.stream().filter(i -> i%2 == 0)).toList();
    }

    public static void counting_duplicate_characters_except_space() {
        String str = "Asmit Basu";
        Map<Character, Long> map = str.chars().mapToObj(i -> (char) i)
                .map(c -> Character.toLowerCase(c)).filter(Character::isAlphabetic)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }

    public static void sort_object_by_string_property() {
        List<Boxed3> result = Stream.of(new Boxed3("John"), new Boxed3("mana"), new Boxed3("kana"))
                .sorted(Comparator.comparing(b -> b.value)).toList();

        result.forEach(System.out::println);
    }

    public static void sort_and_concat_arrays() {
        int [] arr1 = {5,67, 1, 9, 20};
        int [] arr2 = {54, 8, 91, 3};

        int [] result = Stream.concat(
                Arrays.stream(arr1).boxed(),
                Arrays.stream(arr2).boxed()
        ).sorted().mapToInt(Integer::intValue).toArray();

        System.out.println(result);
    }

    public void partitioning_characters() {
        String str = "Hello World";

        List<Character> charList = List.of('a', 'e', 'i', 'o', 'u');

        Map<Boolean, Long> result = str.chars()
                .mapToObj(it -> (char) it).filter(it -> !Character.isWhitespace(it))
                .collect(Collectors.partitioningBy(charList::contains, Collectors.counting()));

        System.out.println(result);
    }

    public static void main(String args[]) {
        collector_sorting();
    }
}

class Boxed {
    int value;
    public Boxed(int v) {
        value=v;
    }
}

class Boxed2 implements Comparable<Boxed2> {
    int value;
    public Boxed2(int v) {
        value=v;
    }

    @Override
    public int compareTo(Boxed2 o) {
        return this.value - o.value;
    }
}

class Boxed3 {
    String value;
    public Boxed3(String v) {value = v;};
}
