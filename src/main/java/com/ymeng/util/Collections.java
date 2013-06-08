package com.ymeng.util;

import java.util.HashSet;
import java.util.Set;

public final class Collections {

    public static <T> Set<T> copy(Set<T> set) {
        Set<T> copy = new HashSet<T>();
        for (T item : set) {
            copy.add(item);
        }

        return copy;
    }
}
