package com.ymeng.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public final class CollectionUtil {

    private final Collection<?> collection;

    private CollectionUtil(Collection<?> collection) {
        this.collection = collection;
    }

    public static CollectionUtil collect(Collection<?> set) {
        return new CollectionUtil(set);
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
