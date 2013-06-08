package com.ymeng.util;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.ymeng.util.Collections.copy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class CollectionsTest {

    @Test
    public void should_make_a_copy_of_set() {
        Set<String> set = new HashSet<String>();
        set.add("Tom");

        Set<String> copy = copy(set);

        assertThat(copy, not(sameInstance(set)));
        assertThat(copy.size(), is(1));
        assertThat(copy, contains("Tom"));
    }
}
