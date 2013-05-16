package br.com.vcosta.kata.sqlbuilder;

public class SQLSelectBuilder {

    private StringBuilder rawSQL = new StringBuilder();
    private boolean hasAlias = false;
    private final String[] fields;
    private String schema = "";
    private String alias;
    private String tableName, joinTableRefereneceName;

    public SQLSelectBuilder(final String... fields) {
        this.fields = fields;
    }

    public void setSchema(String schema) {
        this.schema = schema + ".";
    }

    private void processSelectFields() {
        final String prefix = (hasAlias) ? (alias + ".") : ("");

        rawSQL.append("select ");
        for (String field : fields) {
            rawSQL.append(prefix).append(field).append(", ");
        }
        removeLastComma();
    }

    public SQLSelectBuilder from(final String tableName) {
        this.tableName = schema + tableName;
        processSelectFields();
        rawSQL.append("from ").append(this.tableName).append(" ");
        return this;
    }

    public SQLSelectBuilder leftJoin(final String joinTable) {
        this.joinTableRefereneceName = schema + joinTable;
        rawSQL.append("left outer join")
                .append(" ")
                .append(joinTable)
                .append(" ");
        return this;
    }

    public SQLSelectBuilder rightJoin(final String joinTable) {
        this.joinTableRefereneceName = schema + joinTable;
        rawSQL.append("right outer join")
                .append(" ")
                .append(joinTable)
                .append(" ");
        return this;
    }

    public SQLSelectBuilder crossJoin(final String joinTable) {
        this.joinTableRefereneceName = schema + joinTable;
        rawSQL.append("cross join")
                .append(" ")
                .append(joinTable)
                .append(" ");
        return this;

    }

    public SQLSelectBuilder leftJoin(final String joinTable, final String alias) {
        this.joinTableRefereneceName = alias;
        rawSQL.append("left outer join")
                .append(" ")
                .append(schema).append(joinTable)
                .append(" ")
                .append(alias)
                .append(" ");
        return this;
    }

    public SQLSelectBuilder rightJoin(final String joinTable, final String alias) {
        this.joinTableRefereneceName = alias;
        rawSQL.append("right outer join")
                .append(" ")
                .append(schema).append(joinTable)
                .append(" ")
                .append(alias)
                .append(" ");
        return this;
    }

    public SQLSelectBuilder crossJoin(final String joinTable, final String alias) {
        this.joinTableRefereneceName = alias;
        rawSQL.append("cross join")
                .append(" ")
                .append(schema).append(joinTable)
                .append(" ")
                .append(alias)
                .append(" ");
        return this;

    }

    public SQLSelectBuilder join(final String joinTable, final String alias) {
        this.joinTableRefereneceName = alias;
        rawSQL.append("inner join")
                .append(" ")
                .append(schema).append(joinTable)
                .append(" ")
                .append(alias)
                .append(" ");
        return this;
    }

    public SQLSelectBuilder join(final String joinTable) {
        this.joinTableRefereneceName = joinTable;
        rawSQL.append("inner join")
                .append(" ")
                .append(schema).append(joinTable)
                .append(" ");
        return this;
    }

    public SQLSelectBuilder on(final String joinField) {
        String tableNameReference = this.tableName;
        if (hasAlias) {
            tableNameReference = alias;
        }
        rawSQL.append("on")
                .append(" ")
                .append(tableNameReference)
                .append(".")
                .append(joinField)
                .append(" = ")
                .append(joinTableRefereneceName)
                .append(".")
                .append(joinField)
                .append(" ");
        return this;
    }

    public SQLSelectBuilder from(final String tableName, final String alias) {
        this.tableName = schema + tableName;
        this.alias = alias;
        hasAlias = true;
        processSelectFields();
        rawSQL.append("from ").append(this.tableName).append(" ").append(alias).append(" ");
        return this;
    }

    public Where where(final String field) {
        final Where where = new Where(rawSQL);
        return where.where(field);
    }

    public SQLSelectBuilder orderBy(final String... fields) {
        rawSQL.append("order by")
                .append(" ");
        for (int i = 0; i < fields.length; i += 2) {
            final String fieldName = fields[i];
            final String value = fields[i + 1];
            rawSQL.append(fieldName)
                    .append(" ")
                    .append(value)
                    .append(" ");
        }
        return this;
    }

    public SQLSelectBuilder groupBy(final String... fields) {
        rawSQL.append("group by")
                .append(" ");

        for (String field : fields) {
            rawSQL.append(field)
                    .append(", ");
        }
        removeLastComma();
        return this;
    }

    public String compile() {
        return rawSQL.toString().trim();
    }

    private void removeLastComma() {
        final int lastCommaPosition = rawSQL.lastIndexOf(",");
        if (lastCommaPosition != -1) {
            rawSQL = rawSQL.delete(lastCommaPosition, lastCommaPosition + 1);
        }
    }
}
