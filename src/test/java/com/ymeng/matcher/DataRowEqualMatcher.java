package com.ymeng.matcher;

import com.ymeng.pattern.database.QueryException;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static java.text.MessageFormat.format;

public class DataRowEqualMatcher extends TypeSafeMatcher<ResultSet> {

    private Object[] expected;

    public DataRowEqualMatcher(Object[] expected) {
        this.expected = expected;
    }

    @Override
    protected boolean matchesSafely(ResultSet resultSet) {
        try {
            if (!resultSet.next()) {
                return false;
            }

            for(int i = 0; i < expected.length; i++) {
                Object expectedItem = expected[i];
                if (!isSameField(expectedItem, resultSet, i + 1)) {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new QueryException();
        }

        return true;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("not equal row");
    }

    public static Matcher<ResultSet> nextRowIs(Object... expected) {
        return new DataRowEqualMatcher(expected);
    }

    private boolean isSameField(Object expected, ResultSet resultSet, int index) throws SQLException {
        if (expected instanceof Long) {
            return resultSet.getLong(index) == (Long)expected;
        } else if (expected instanceof Double) {
            return resultSet.getDouble(index) == (Double)expected;
        } else if (expected instanceof Date) {
            return new DateEqualMatcher((Date)expected).matchesSafely(convertToNormalDate(resultSet.getDate(index)));
        }
        throw new IllegalArgumentException(format("Can NOT identify data type '{0}'", expected.getClass()));
    }

    private Date convertToNormalDate(java.sql.Date date) {
        return new Date(date.getTime());
    }
}
