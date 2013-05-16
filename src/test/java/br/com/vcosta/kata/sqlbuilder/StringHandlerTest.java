package br.com.vcosta.kata.sqlbuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import br.com.vcosta.kata.sqlbuilder.Handler;
import br.com.vcosta.kata.sqlbuilder.StringHandler;

public class StringHandlerTest {

    @Test
    public void it_formats_strings() {
        final Handler stringHandler = new StringHandler();
        final Object nameString = "peter";
        final Object nameStringFormmated = "'peter'";
        assertThat(stringHandler.process(nameString), is(nameStringFormmated));
    }

    @Test
    public void it_ignores_non_date() {
        final Handler stringHandler = new StringHandler();
        final Object regularNumer = 53;
        assertThat(stringHandler.process(regularNumer), is(regularNumer));

    }
}
