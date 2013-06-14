package com.ymeng.orm;

import org.junit.Before;
import org.junit.Test;

import static com.ymeng.matcher.Matchers.contains;
import static com.ymeng.orm.Database.INVALID_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public class FlatObjectTest {

    private FlatObject flatObject;

    @Before
    public void setUp() throws Exception {
        flatObject = new FlatObject("Student");
        flatObject.registerField("Name", "Tom");
        flatObject.registerPrimaryKeys("Id");
    }

    @Test
    public void should_get_table_name() {
        assertThat(flatObject.tableName(), is("student"));
    }

    @Test
    public void should_be_new_object_when_primary_key_field_is_long_and_invalid() {
        flatObject.registerField("id", INVALID_ID);

        assertThat(flatObject.isNew(), is(true));
    }

    @Test
    public void should_get_all_fields_if_object_is_NOT_new() {
        flatObject.registerField("id", 1L);

        assertThat(flatObject.fields(), contains("id", "name"));
    }

    @Test
    public void should_exclude_primary_keys_when_get_all_fields_if_object_is_new() {
        flatObject.registerField("id", INVALID_ID);

        assertThat(flatObject.fields(), contains("name"));
    }

    @Test
    public void should_clone_itself() {
        flatObject.registerField("id", 1L);

        FlatObject clone = flatObject.clone();

        assertThat(clone, not(sameInstance(flatObject)));
        assertThat(clone, is(flatObject));
    }
}
