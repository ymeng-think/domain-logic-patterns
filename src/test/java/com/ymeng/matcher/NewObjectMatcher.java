package com.ymeng.matcher;

import com.ymeng.orm.FlatObject;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import static com.ymeng.orm.Database.INVALID_ID;

public class NewObjectMatcher extends TypeSafeMatcher<FlatObject> {

    @Override
    protected boolean matchesSafely(FlatObject flatObject) {
        return ((Integer)flatObject.value("id")) == INVALID_ID;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a new object");
    }

}
