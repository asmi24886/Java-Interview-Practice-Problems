package com.practice.interview;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        String input = "aabbcdeeeff";

        /********************************************************/

        char[] charArr = input.toCharArray();
        Character ch = (char) 15;

        /********************************************************/

        IntStream.rangeClosed(1, 500).boxed().map(
                i -> (char) i.intValue()
        );

        /********************************************************/

        Deque<Character> dq = new LinkedList<>();

        for(int i = 0; i < charArr.length; i++) {

            if(dq.contains(charArr[i])) continue;

            if(charArr[i] == 'a' || charArr[i] == 'b') {
                dq.addLast(charArr[i]);
            }
            else {
                dq.addFirst(charArr[i]);
            }
        }

        System.out.println(dq);

        /********************************************************/

        new String(charArr).chars().mapToObj(i -> (char) i).forEach(System.out::println);
    }

}