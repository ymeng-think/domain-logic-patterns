package com.ymeng.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Set;

public class ListEqualMatcher<T> extends TypeSafeMatcher<Set<T>> {

    private final T[] expected;

    public ListEqualMatcher(T[] expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(Set<T> set) {
        if (expected.length != set.size()) {
            return false;
        }

        for (T item : expected) {
            if (!set.contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("not equal set");
    }

    public static ListEqualMatcher<String> contains(String... items) {
        return new ListEqualMatcher<String>(items);
    }
}
