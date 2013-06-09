package com.ymeng.orm;

import com.ymeng.orm.dummy.Donkey;
import com.ymeng.orm.dummy.Product;
import org.junit.Test;

import static com.ymeng.matcher.Matchers.contains;
import static com.ymeng.matcher.Matchers.newObject;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FlattenerTest {

    @Test
    public void should_flatten_target_only_with_class_info() {
        Product product = new Product("MS Word", "W");
        Flattener flattener = new Flattener(product);

        FlatObjectCollection flatObjects = flattener.flatten();

        assertThat(flatObjects.size(), is(1));
        FlatObject firstFlatObject = flatObjects.get(0);
        assertThat(firstFlatObject.tableName(), is("product"));
        assertThat(firstFlatObject.fields(), contains("id", "name", "type"));
        assertThat(firstFlatObject, is(newObject()));
        assertThat((String)firstFlatObject.value("name"), is("MS Word"));
        assertThat((String)firstFlatObject.value("type"), is("W"));
    }

    @Test
    public void should_flatten_target_with_annotations() {
        Donkey donkey = new Donkey("Rap");
        Flattener flattener = new Flattener(donkey);

        FlatObjectCollection flatObjects = flattener.flatten();

        assertThat(flatObjects.size(), is(1));
        FlatObject firstFlatObject = flatObjects.get(0);
        assertThat(firstFlatObject.tableName(), is("animal"));
    }
}
