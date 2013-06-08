package com.ymeng.util;

import java.util.Collection;
import java.util.Iterator;

public final class CollectionWrapper {

    private final Collection<?> collection;

    private CollectionWrapper(Collection<?> collection) {
        this.collection = collection;
    }

    public static CollectionWrapper collect(Collection<?> set) {
        return new CollectionWrapper(set);
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
