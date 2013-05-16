package br.com.vcosta.kata.sqlbuilder;

public class Where {

    private final StringBuilder rawSQL;

    public Where(final StringBuilder rawSQL) {
        this.rawSQL = rawSQL;
    }

    public Where where(final String field) {
        rawSQL.append("where ")
                .append(field)
                .append(" ");
        return this;
    }

    public Where and(final String field) {
        rawSQL.append("and ")
                .append(field).append(" ");
        return this;
    }

    public Where or(final String field) {
        rawSQL.append("or ")
                .append(field).append(" ");
        return this;
    }

    public Where equalsTo(Object value) {
        value = processValue(value);
        rawSQL.append("= ").append(value).append(" ");
        return this;
    }

    public Where greaterThan(Object value) {
        value = processValue(value);
        rawSQL.append("> ").append(value).append(" ");
        return this;
    }

    public Where greaterOrEqualsThan(Object value) {
        value = processValue(value);
        rawSQL.append(">= ").append(value).append(" ");
        return this;
    }

    public Where lessThan(Object value) {
        value = processValue(value);
        rawSQL.append("< ").append(value).append(" ");
        return this;
    }

    public Where lessOrEqualsThan(Object value) {
        value = processValue(value);
        rawSQL.append("<= ").append(value).append(" ");
        return this;
    }

    public Where like(final String value) {
        rawSQL.append("like '").append(value).append("' ");
        return this;
    }

    public String compile() {
        return rawSQL.toString().trim();
    }

    private Object processValue(Object value) {
        for (final Handler handler : Handler.all) {
            value = handler.process(value);
        }
        return value;
    }
}
