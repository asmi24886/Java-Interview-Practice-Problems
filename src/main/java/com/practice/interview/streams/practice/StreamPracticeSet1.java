package com.practice.interview.streams.practice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    public static void words_with_specified_number_of_vowels() {
        String str = "I am lEarning stream apI in jAva";
        int n = 2;

        //The line where the Character is converted to char is important because towLowerCase with codepoint returns codepoint only, not char

        Set<Character> vowelSet = Set.of('a', 'e', 'i', 'o', 'u');
        List<String> ans = Arrays.stream(str.split(" ")).filter(s ->
                s.chars().filter(i -> vowelSet.contains(Character.toLowerCase((char) i))).toArray().length >= n
        ).toList();

        //Great trick to reduce
        List<String> ans2 = Arrays.stream(str.split(" ")).filter(s ->
                s.chars().reduce(0, (count, chr) -> vowelSet.contains(Character.toLowerCase((char) chr)) ? count + 1 : count) == n
        ).toList();

        System.out.println(ans2);
    }

    public static void partition_odd_even_integers() {
        int [] arr = {1,2,3,4,5,6,7,8};
        Map<Boolean, List<Integer>> partition = Arrays.stream(arr).boxed().collect(Collectors.partitioningBy(i -> i%2 == 1, Collectors.toList())); //toList stuff is optional
        System.out.println(partition);
    }

    public static void highest_possible_value() {
        int [] arr = {1,2,3,4,5};
        long val = Long.parseLong(Arrays.stream(arr).mapToObj(String::valueOf).sorted(Comparator.reverseOrder()).collect(Collectors.joining("")));
        System.out.println(val);
    }

    public static void sum_of_unique_elements() {
        int [] arr = {1,6,7,8,1,1,8,8,7};
        int sum = Arrays.stream(arr).distinct().sum();
        System.out.println(sum);
    }

    // very tricky and important
    public static void find_first_non_repeated_char() {
        String str = "Hello world";

        //general
        int idx = str.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() == 1)
                        .mapToInt(e -> str.indexOf(e.getKey())).min().getAsInt();

        //preserving order of keys
        char chr = str.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() == 1).findFirst().get().getKey();

        //Simpler impl
        char chr1 = (char) str.chars().filter(c -> str.indexOf(c) == str.lastIndexOf(c)).findFirst().getAsInt();
        System.out.println(chr1);
    }

    public static void find_first_repeated_character() {
        String str = "Helo world";

        //general
        int idx = str.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1).map(e -> str.indexOf(e.getKey()))
                .min(Comparator.comparingInt(i -> i)).get();
        System.out.println(str.charAt(idx));

        //preserving order
        char ch1 = str.chars().mapToObj(i -> (char) i).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1).findFirst().get().getKey();
        System.out.println(ch1);

        //simple
        char ch2 = (char) str.chars().filter(c -> str.indexOf(c) != str.lastIndexOf(c)).findFirst().getAsInt();
        System.out.println(ch2);
    }

    public static void group_numbers_by_range() {
        int [] arr = {2,3,11,17,23,26,57,153,987,82,1002, 1009};
        TreeMap<Integer, Set<Integer>> set = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(
                i -> Math.max(0, i - i%10), // complex
                TreeMap::new,
                Collectors.toSet()
        ));

        Map<Integer, List<Integer>> list = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(
                i -> i/10 * 10, //simple
                TreeMap::new,
                Collectors.toList()
        ));

        System.out.println(list);
    }

    public static void filter_integers_from_list_of_string() {
        List<String> list = List.of("sadf", "123", "ghfhg", "765");
        List<String> list2 = list.stream().map(s -> {
          try {
               Integer.parseInt(s);
               return s;
          } catch (Exception e) {
              return  null;
          }
        }).filter(s -> s != null).toList();
        System.out.println(list2);
    }

    public static void product_of_first_two_integers() {
        int [] arr = {12,5,6,9,2,4};
        int product = Arrays.stream(arr).limit(2).reduce((i, j) -> i*j).getAsInt();
        System.out.println(product);
    }

    //difficult
    public static void group_anagrams() {
        List<String> list = List.of("pat", "tap", "pan", "nap", "Team", "meat");
        List<List<String>> lists = list.stream().map(String::toLowerCase).collect(Collectors.groupingBy(
                str -> Arrays.stream(str.split("")).sorted().collect(Collectors.joining("")),
                Collectors.toList()
        )).values().stream().toList();

        System.out.println(lists);
    }

    //very difficult
    public static void group_reversed() {
        List<String> list = List.of("pat", "tap", "pan", "nap", "Team", "meat");
        Set<List<String>> setOfReversed = list.stream().map(String::toLowerCase).collect(
                HashSet<List<String>>::new,
                (set, str) -> {
                    String reversed = new StringBuffer(str).reverse().toString();
                    if(list.contains(reversed)) {
                        set.add(Stream.of(str, reversed).sorted().toList());
                    }
                },
                (set1, set2) -> set2.addAll(set1)
        );
        System.out.println(setOfReversed);
    }

    //tricky - always use such ranges when dealing with index stuff
    //Also binary search might be more useful in some cases of searches if arr is sorted
    public static void multiply_alternate_numbers() {
        int [] arr = {4, 5, 1, 7, 2, 9, 2};

        int product = Arrays.stream(arr).filter(i -> {
            for(int k=0; k<arr.length; k++) {
                if(arr[k] == i) {
                   return k%2==0;
                }
            }
            return false;
        }).reduce((i,j)->i*j).getAsInt();

        //Better approach
        int product2 = IntStream.range(0, arr.length)
                .filter(i -> i%2 == 0).map(i -> arr[i])
                .reduce((x,y)->x*y).getAsInt();
        System.out.println(product2);
    }

    public static void multiply_alternate_start_end_element() {
        int [] arr = {4, 5, 1, 7, 2, 9};
        List<Integer> products = IntStream.range(0, arr.length/2).map(i -> arr[i] * arr[arr.length - i -1]).boxed().toList();
        System.out.println(products);
    }

    public static void partition_zeroes() {
        int [] arr = {5,0,1,0,8,0};
        //could also do i -> i != 0, then sorting on list wouldnt have been required
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.partitioningBy(i -> i == 0))
                .entrySet().stream().map(Map.Entry::getValue)
                .sorted((l1, l2) -> l1.contains(0)?-1:1).flatMap(l -> l.stream()).toList();

        System.out.println(list);
    }

    public static void has_distinct_elements() {
        int [] arr = {5,0,1,8};
        boolean isDistinct = Arrays.stream(arr).distinct().count() == arr.length;
        System.out.println(isDistinct);

    }

    public static void main(String [] args) {
        has_distinct_elements();
    }
}
