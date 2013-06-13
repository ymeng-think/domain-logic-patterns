package com.ymeng.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class ArrayEqualMatcher<T> extends TypeSafeMatcher<T[]> {

    private final T[] expected;

    public ArrayEqualMatcher(T[] expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(T[] list) {
        if (expected.length != list.length) {
            return false;
        }

        for (int i = 0; i < expected.length; i++) {
            if (!expected[i].equals(list[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expected);
    }
}
