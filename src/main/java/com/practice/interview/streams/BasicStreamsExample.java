package com.practice.interview.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BasicStreamsExample {

    /*  EXTRAS:-

    * Exception handling in streams
    * sorting with comparator vs comparable
    * searching with binary search
    *
    * */
    public static List<Employee> employees = new ArrayList<>();
    public static void main(String[] args) {
        initData();
        count_employees_in_id_mod_by_3_buckets().entrySet().forEach(System.out::println);
    }

    public static void initData() {

        IntStream.range(1, 21).forEach(
                i -> employees.add(new Employee(i, i*2, i+"name", i*50.0))
        );
    }

    public static List<Object> map_employee_name_first_number() {
        return employees
                .stream()
                .map(
                        e -> Integer.parseInt(
                                e.name.chars()
                                        .filter(Character::isDigit)
                                        .mapToObj(Character::toString)
                                        .collect(Collectors.joining(""))
                        )
                )
                .collect(Collectors.toList());
    }

    public static OptionalDouble get_average_salary() {
        return employees
                .stream()
                .mapToDouble(e -> e.salary)
                .average(); //can also be done using Collectors.averagingDouble(e -> e.salary)
    }

    public static Map<Integer, Set<Double>> mapping_by_id_mod_5_in_set() {
        return employees.stream().collect(
                Collectors.groupingBy(
                        e -> e.id % 5,
                        Collectors.mapping(e -> e.salary, Collectors.toSet())
                )
        );
    }

    public static Map<Boolean, List<Employee>> partition_odd_even_id() {
        return employees
                .stream()
                .collect(
                        Collectors.partitioningBy(
                                e -> e.id % 2 == 0
                        )
                );
    }

    public static String get_all_names_joining_by_comma() {
        return employees.stream().map(e -> e.id+"").collect(Collectors.joining());
    }

    public static Map<String, Double> map_by_employee_names() {
        return employees.stream().collect(
                Collectors.toMap(
                        e->e.name,
                        e->e.salary
                )
        );
    }

    public static Map<Integer, Long> count_employees_in_id_mod_by_3_buckets() {
        return employees.stream().collect(
                Collectors.groupingBy(
                        e->e.id%3,
                        Collectors.counting()
                )
        );
    }

    public static double get_simple_sum_of_salary_of_even_employees() {
        return employees.parallelStream().filter(e -> e.id%2 == 0).mapToDouble(e -> e.salary).sum();
    }

    public static void println(Object obj) {
        System.out.println(obj.toString());
    }
}

class Employee {
    public int id;
    public int age;
    public String name;
    public double salary;

    public Employee(int id, int age, String name, double salary) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.salary = salary;
    }
}