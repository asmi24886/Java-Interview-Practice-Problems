package com.practice.interview.designpatterns;

public class DoubleCheckedLockingSingleton {

    private static volatile DoubleCheckedLockingSingleton instance; // volatile is important

    private DoubleCheckedLockingSingleton() {
        // Private constructor
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        DoubleCheckedLockingSingleton result = instance; // Read local copy
        if (result == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                result = instance;
                if (result == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }

    // ... other methods ...
}
