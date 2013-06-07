package com.ymeng.orm;

import org.junit.Test;

import java.util.List;

import static com.ymeng.matcher.ListEqualMatcher.contains;
import static com.ymeng.matcher.NewObjectMatcher.newObject;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FlattenerTest {

    @Test
    public void should_flatten_target() {
        DummyProduct product = new DummyProduct("MS Word", "W");
        Flattener flattener = new Flattener(product);

        List<FlatObject> flatObjects = flattener.flatten();

        assertThat(flatObjects.size(), is(1));
        FlatObject firstFlatObject = flatObjects.get(0);
        assertThat(firstFlatObject.fields(), contains("id", "name", "type"));
        assertThat(firstFlatObject, is(newObject()));
        assertThat((String)firstFlatObject.getValue("name"), is("MS Word"));
        assertThat((String)firstFlatObject.getValue("type"), is("W"));
    }
}
