package com.practice.interview.string;

import java.util.Map;

public class SwapVowels {
    static Map<Character, Boolean> vowelsMap = Map.of(
            'a', true, 'e', true, 'i', true, 'o', true, 'u', true);

    public static void main(String [] args) {

        String s = "Asmite"; //Expected output - esmitA
        char [] charArr = s.toCharArray();

        int i = 0; int j = charArr.length - 1;

        while(i <= j) {
            if(isVowel(charArr[i]) && isVowel(charArr[j])) {
                char temp = charArr[i];
                charArr[i] = charArr[j];
                charArr[j] = temp;
                i++;
                j--;
            }
            else if(isVowel(charArr[i])) {
                j--;
            }
            else if(isVowel(charArr[j])) {
                i++;
            }
            else {
                i++; j--;
            }
        }

        System.out.println(new String(charArr));
    }

    public static boolean isVowel(char ch) {
        return vowelsMap.get(Character.toLowerCase(ch)) != null;
    }
}
