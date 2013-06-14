package com.ymeng.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class Collections {

    public static <T> Set<T> copy(Set<T> set) {
        Set<T> copy = new HashSet<T>();
        for (T item : set) {
            copy.add(item);
        }

        return copy;
    }

    public static <K, V> void copy(Map<K, V> from, Map<K, V> to) {
        to.clear();

        for (Map.Entry<K, V> entry : from.entrySet()) {
            to.put(entry.getKey(), entry.getValue());
        }
    }
}
