package com.ymeng.util;

import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.ymeng.util.CollectionUtil.collect;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CollectionUtilTest {

    @Test
    public void should_join_items_in_set_to_string() {
        Set<String> set = buildSet("a", "b", "c");

        String joined = collect(set).join(",");

        assertThat(joined, is("b,c,a"));
    }

    private Set<String> buildSet(String... items) {
        Set<String> set = new HashSet<String>();
        Collections.addAll(set, items);
        return set;
    }
}
