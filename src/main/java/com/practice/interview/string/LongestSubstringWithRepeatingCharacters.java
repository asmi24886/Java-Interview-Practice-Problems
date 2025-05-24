package com.practice.interview.string;

public class LongestSubstringWithRepeatingCharacters {

    public static void main(String [] args) {
        System.out.println(solution());
    }

    public static String solution() {
        String str = "aabccccddaaaaaaa";
        StringBuffer buffer = new StringBuffer();

        str.chars().forEach(c -> {
            if(!buffer.isEmpty() && buffer.charAt(buffer.length() - 1) != c) {
                buffer.delete(0, buffer.length());
            }
            buffer.append( (char) c );
        });

        return buffer.toString();
    }
}
