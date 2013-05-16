package br.com.vcosta.kata.sqlbuilder;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static br.com.vcosta.kata.sqlbuilder.SQLBuilder.*;

public class UpdateDeleteTest {

    @Test
    public void it_creates_update_query() {
        final String delete = deleteFrom("pessoas")
                .where("nome").like("%ra").compile();

        assertThat(delete, is("delete from pessoas where nome like '%ra'"));
    }
}
