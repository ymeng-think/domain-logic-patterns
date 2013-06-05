package com.ymeng.orm;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlattenerTest {

    @Test
    public void should_flatten_target() {
        DummyProduct product = new DummyProduct("MS Word", "W");
        Flattener flattener = new Flattener(product);

        List<FlatObject> flatObjects = flattener.flatten();

        assertThat(flatObjects.size(), is(1));
    }
}
