package com.practice.interview.immutables;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public final class ImmutableEmployee {
    private final int i;
    private final String s;
    private final Map<Integer, String> m;

    public ImmutableEmployee(int i, String s, Map<Integer, String> m) {
        this.i = i;
        this.s = s;
        this.m = cloneMap(m);
    }

    public int getI() { return i; }
    public String getS() { return s; }
    public Map<Integer, String> getM() { return cloneMap(m); }

    private static <K, V> Map<K, V> cloneMap(Map<K, V> map) {
        return Collections.unmodifiableMap(
                map.entrySet().stream().collect(
                        Collectors.toMap(Map.Entry<K, V>::getKey, Map.Entry<K, V>::getValue)
                )
        );
    }
}
