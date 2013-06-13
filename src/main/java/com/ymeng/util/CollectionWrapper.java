package com.ymeng.util;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class CollectionWrapper {

    private final Collection<?> collection;

    private CollectionWrapper(Collection<?> collection) {
        this.collection = collection;
    }

    public static <T> CollectionWrapper collect(T[] items) {
        List<T> list = Lists.newArrayList(items);
        return collect(list);
    }

    public static CollectionWrapper collect(Collection<?> collection) {
        return new CollectionWrapper(collection);
    }

    public String join(String separator) {
        StringBuilder joined = new StringBuilder();

        Iterator<?> iterator = collection.iterator();
        int counter = 0;
        while(iterator.hasNext()) {
            joined.append(iterator.next());

            if (counter++ < (collection.size() - 1)) {
                joined.append(separator);
            }
        }

        return joined.toString();
    }
}
