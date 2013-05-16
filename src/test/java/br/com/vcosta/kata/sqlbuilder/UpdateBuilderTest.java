package br.com.vcosta.kata.sqlbuilder;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static br.com.vcosta.kata.sqlbuilder.SQLBuilder.*;

public class UpdateBuilderTest {

    @Test
    public void it_creates_update_query() {
        final String update = update("pessoas")
                .set("nome","peter griffin","salario",10850.78)
                .where("id").equalsTo(3).compile();

        assertThat(update, 
                is("update pessoas set nome='peter griffin', salario=10850.78 where id = 3"));
    }
}
