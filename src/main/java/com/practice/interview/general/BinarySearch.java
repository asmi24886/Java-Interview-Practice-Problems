package com.practice.interview.general;

import java.util.Arrays;

public class BinarySearch {

    public static void main(String [] args) {
        int [] arr = {5, 8, 7, 4, 1, 20, 45, 32, 8};
        int search = 1;

        System.out.println(solution(arr, search));
    }

    public static int solution(int [] arr, int search) {
        Arrays.sort(arr);
        System.out.println(Arrays.stream(arr).boxed().toList());

        int left = 0;
        int right = arr.length - 1;
        int mid = -1;

        while(right >= left) {

            mid = left + ((right-left)/2);
            if(arr[mid] == search) return mid;
            if(search > arr[mid]) { left = mid+1; }
            else { right = mid - 1; }
        }

        return -1;
    }
}
