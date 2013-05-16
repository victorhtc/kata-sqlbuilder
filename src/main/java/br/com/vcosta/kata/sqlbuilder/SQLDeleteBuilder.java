package br.com.vcosta.kata.sqlbuilder;

public class SQLDeleteBuilder {

    private StringBuilder rawSQL = new StringBuilder();

    public SQLDeleteBuilder(final String tableName) {
        rawSQL.append("delete from")
                .append(" ")
                .append(tableName)
                .append(" ");
    }

    public Where where(final String field) {
        final Where where = new Where(rawSQL);
        return where.where(field);
    }
}
