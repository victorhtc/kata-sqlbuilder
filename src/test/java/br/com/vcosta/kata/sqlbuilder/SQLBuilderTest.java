package br.com.vcosta.kata.sqlbuilder;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static br.com.vcosta.kata.sqlbuilder.SQLBuilder.*;

public class SQLBuilderTest {

    @Test
    public void it_creates_select_query_with_group_by() {
        final String sql = select().from("pessoas").groupBy("nome", "nascimento").compile();

        assertThat(sql, is("select * from pessoas group by nome, nascimento"));
    }

    @Test
    public void it_creates_aggregated_query_with_order() {
        final String avg = select(avg("salario")).from("pessoas").compile();
        final String count = select(count("*")).from("pessoas").compile();
        final String max = select(max("salario")).from("pessoas").compile();
        final String min = select(min("nascimento")).from("pessoas").compile();

        assertThat(avg, is("select avg(salario) from pessoas"));
        assertThat(count, is("select count(*) from pessoas"));
        assertThat(max, is("select max(salario) from pessoas"));
        assertThat(min, is("select min(nascimento) from pessoas"));
    }

    @Test
    public void it_creates_select_query_with_order() {
        final String sql = select().from("pessoas").orderBy("nome", "desc").compile();

        assertThat(sql, is("select * from pessoas order by nome desc"));
    }

    @Test
    public void it_creates_select_query_restricted_by_where() {
        final String sqlEquals = select("nome", "cpf")
                .from("pessoas")
                .where("nome").equalsTo("peter")
                .compile();
        final String sqlGraterThan = select("nome", "cpf")
                .from("pessoas")
                .where("idade").greaterThan(3)
                .compile();
        final String composedSQL = select("nome", "cpf")
                .from("pessoas")
                .where("salario").greaterThan(3.3)
                .and("nome").like("%pet%")
                .compile();


        assertThat(sqlEquals, is("select nome, cpf from pessoas where nome = 'peter'"));
        assertThat(sqlGraterThan, is("select nome, cpf from pessoas where idade > 3"));
        assertThat(composedSQL, is("select nome, cpf from pessoas where salario > 3.3 and nome like '%pet%'"));
    }

    @Test
    public void it_creates_select_query_restricted_by_where_using_date() {
        final Calendar date = new GregorianCalendar(2000, 7, 3);

        final String sqlEquals = select("nome", "cpf")
                .from("pessoas")
                .where("nascimento").greaterThan(date.getTime())
                .compile();

        assertThat(sqlEquals, is("select nome, cpf from pessoas where nascimento > 2000-08-03"));
    }

    @Test
    public void it_creates_select_query_restricted_by_field() {
        final String sql = select("nome", "cpf").from("pessoas").compile();

        assertThat(sql, is("select nome, cpf from pessoas"));
    }

    @Test
    public void it_creates_select_query_not_restricted_by_field() {
        final String sql = select().from("pessoas").compile();

        assertThat(sql, is("select * from pessoas"));
    }

    @Test
    public void it_creates_select_query_with_distintic_schema() {
        final String sql = selectUsingDifferentSchema("prod").from("pessoas").compile();

        assertThat(sql, is("select * from prod.pessoas"));
    }

    @Test
    public void it_creates_select_query_with_distintic_schema_and_alias_table() {
        final String sql = selectUsingDifferentSchema("prod", "nome", "cpf").from("pessoas", "p").compile();

        assertThat(sql, is("select p.nome, p.cpf from prod.pessoas p"));
    }

    @Test
    public void it_creates_select_with_alias_restricted_by_field() {
        final String sql = select("nome", "cpf").from("pessoas", "pes").compile();

        assertThat(sql, is("select pes.nome, pes.cpf from pessoas pes"));
    }

    @Test
    public void it_creates_select_with_alias_restricted_by_field_and_using_schema() {
        final String sql = select("nome", "cpf").from("pessoas", "pes").compile();

        assertThat(sql, is("select pes.nome, pes.cpf from pessoas pes"));
    }

    @Test
    public void it_creates_select_with_alias_not_restricted_by_field() {
        final String sql = select().from("pessoas", "pes").compile();

        assertThat(sql, is("select pes.* from pessoas pes"));
    }

    @Test
    public void it_creates_select_using_join() {
        final String sql = select("nome", "cpf")
                .from("pessoas")
                .join("carros")
                .on("cpf")
                .compile();

        assertThat(sql, is("select nome, cpf from pessoas inner join carros on pessoas.cpf = carros.cpf"));
    }

    @Test
    public void it_creates_select_using_join_using_alias() {
        final String sql = select("nome", "cpf")
                .from("pessoas", "p")
                .join("carros", "c")
                .on("cpf")
                .compile();

        assertThat(sql, is("select p.nome, p.cpf from pessoas p inner join carros c on p.cpf = c.cpf"));
    }

    @Test
    public void it_creates_select_using_all_kinds_of_join() {
        final String innerJoin = select("nome", "cpf")
                .from("pessoas")
                .join("carros")
                .on("cpf")
                .compile();

        final String leftJoin = select("nome", "cpf")
                .from("pessoas")
                .leftJoin("carros")
                .on("cpf")
                .compile();

        final String rightJoin = select("nome", "cpf")
                .from("pessoas")
                .rightJoin("carros")
                .on("cpf")
                .compile();

        final String crossJoin = select("nome", "cpf")
                .from("pessoas")
                .crossJoin("carros")
                .compile();

        assertThat(innerJoin, is("select nome, cpf from pessoas inner join carros on pessoas.cpf = carros.cpf"));
        assertThat(leftJoin, is("select nome, cpf from pessoas left outer join carros on pessoas.cpf = carros.cpf"));
        assertThat(rightJoin, is("select nome, cpf from pessoas right outer join carros on pessoas.cpf = carros.cpf"));
        assertThat(crossJoin, is("select nome, cpf from pessoas cross join carros"));
    }
}
