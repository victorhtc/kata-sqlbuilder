package br.com.vcosta.kata.sqlbuilder;

public class SQLBuilder {

    public static SQLSelectBuilder selectUsingDifferentSchema(final String schema, final String... fields) {
        if (fields.length == 0) {
            final SQLSelectBuilder all = new SQLSelectBuilder("*");
            all.setSchema(schema);
            return all;
        } else {
            final SQLSelectBuilder selectedFields = new SQLSelectBuilder(fields);
            selectedFields.setSchema(schema);
            return selectedFields;
        }
    }

    public static SQLSelectBuilder select(final String... fields) {
        if (fields.length == 0) {
            return new SQLSelectBuilder("*");
        } else {
            return new SQLSelectBuilder(fields);
        }
    }

    public static String avg(final String field) {
        return String.format("avg(%s)", field);
    }

    public static String count(final String field) {
        return String.format("count(%s)", field);
    }

    public static String max(final String field) {
        return String.format("max(%s)", field);
    }

    public static String min(final String field) {
        return String.format("min(%s)", field);
    }

    public static SQLInsertBuilder insertInto(final String table, final String... fields) {
        return (fields.length == 0) ? new SQLInsertBuilder(table) : new SQLInsertBuilder(table, fields);
    }

    public static SQLUpdateBuilder update(final String table) {
        return new SQLUpdateBuilder(table);
    }

    public static SQLDeleteBuilder deleteFrom(final String table) {
        return new SQLDeleteBuilder(table);
    }
}
