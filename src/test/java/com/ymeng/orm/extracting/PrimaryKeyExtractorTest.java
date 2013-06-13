package com.ymeng.orm.extracting;

import com.ymeng.orm.dummy.Donkey;
import com.ymeng.orm.dummy.Product;
import org.junit.Test;

import java.lang.reflect.Field;

import static com.ymeng.matcher.Matchers.eq;
import static org.junit.Assert.assertThat;

public class PrimaryKeyExtractorTest {

    @Test
    public void should_use_id_field_as_primary_key_if_no_fields_is_marked_by_primary_key() throws Exception {
        Field[] fields = Product.class.getDeclaredFields();
        PrimaryKeyExtractor extractor = new PrimaryKeyExtractor(fields);

        String[] primaryKeys = extractor.extract();

        assertThat(primaryKeys, eq("id"));
    }

    @Test
    public void should_get_marked_primary_key() {
        Field[] fields = Donkey.class.getDeclaredFields();
        PrimaryKeyExtractor extractor = new PrimaryKeyExtractor(fields);

        String[] primaryKeys = extractor.extract();

        assertThat(primaryKeys, eq("ssid"));
    }
}
