package br.com.vcosta.kata.sqlbuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static br.com.vcosta.kata.sqlbuilder.SQLBuilder.*;

public class InsertBuilderTest {

    @Test
    public void it_creates_insert_query() {
        final Calendar date = new GregorianCalendar(2000, 7, 3);
        final String simpleInsert = insertInto("pessoas").values(12l, "peter", "123456789", 8501.89, date.getTime()).compile();
        final String complexInsert = insertInto("pessoas", "cpf", "nome").values("123456789", "peter").compile();

        assertThat(simpleInsert, is("insert into pessoas values(12,'peter','123456789',8501.89,2000-08-03)"));
        assertThat(complexInsert, is("insert into pessoas (cpf, nome ) values('123456789','peter')"));
    }
}
