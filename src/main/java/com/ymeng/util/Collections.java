package com.ymeng.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Collections {

    public static <T> Set<T> copy(Set<T> set) {
        Set<T> copy = new HashSet<T>();
        copy(set, copy);
        return copy;
    }

    public static <K, V> void copy(Map<K, V> from, Map<K, V> to) {
        to.clear();

        for (Map.Entry<K, V> entry : from.entrySet()) {
            to.put(entry.getKey(), entry.getValue());
        }
    }

    public static <T> void copy(Set<T> from, Set<T> to) {
        to.clear();

        for (T item : from) {
            to.add(item);
        }
    }
}
