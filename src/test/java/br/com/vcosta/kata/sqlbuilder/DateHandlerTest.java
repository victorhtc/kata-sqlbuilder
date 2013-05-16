package br.com.vcosta.kata.sqlbuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import br.com.vcosta.kata.sqlbuilder.DateHandler;
import br.com.vcosta.kata.sqlbuilder.Handler;

public class DateHandlerTest {

    @Test
    public void it_formats_date() {
        final Handler dataHandler = new DateHandler();
        final Calendar date = new GregorianCalendar(2000, 7, 3);
        final Object dateString = "2000-08-03";
        assertThat(dataHandler.process(date.getTime()), is(dateString));
    }

    @Test
    public void it_ignores_non_date() {
        final Handler dataHandler = new DateHandler();
        final Object name = "peter";
        assertThat(dataHandler.process(name), is(name));
    }
}
