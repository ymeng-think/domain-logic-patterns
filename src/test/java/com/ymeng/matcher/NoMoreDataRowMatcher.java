package com.ymeng.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoMoreDataRowMatcher extends TypeSafeMatcher<ResultSet> {

    @Override
    protected boolean matchesSafely(ResultSet resultSet) {
        try {
            return !resultSet.next();
        } catch (SQLException e) {
            throw new ResultSetAccessFailedException();
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("no more row");
    }
}
