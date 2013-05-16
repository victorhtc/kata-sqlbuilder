package br.com.vcosta.kata.sqlbuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandler implements Handler {

    private final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public Object process(final Object value) {
        if (isADate(value)) {
            return format.format(value);
        } else {
            return value;
        }
    }

    private boolean isADate(final Object value) {
        return value instanceof Date || value instanceof java.sql.Date;
    }
}
