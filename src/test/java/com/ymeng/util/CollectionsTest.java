package com.ymeng.util;

import org.junit.Test;

import java.util.HashMap;
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

    @Test
    public void should_copy_map() {
        HashMap<Integer, String> from = new HashMap<Integer, String>();
        from.put(1, "Bob");
        from.put(2, "Larry");
        HashMap<Integer, String> to = new HashMap<Integer, String>();
        to.put(0, "Whom");

        copy(from, to);

        assertThat(to.size(), is(from.size()));
        assertThat(to.get(1), is("Bob"));
        assertThat(to.get(2), is("Larry"));
    }
}
