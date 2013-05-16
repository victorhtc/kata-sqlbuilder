package br.com.vcosta.kata.sqlbuilder;

public class SQLInsertBuilder {

    private StringBuilder rawSQL = new StringBuilder();
    private Handler[] handlers = new Handler[]{new StringHandler(), new DateHandler()};

    public SQLInsertBuilder(final String tableName) {
        rawSQL.append("insert into")
                .append(" ")
                .append(tableName)
                .append(" values(");
    }

    public SQLInsertBuilder(final String tableName, final String... fields) {
        rawSQL.append("insert into")
                .append(" ")
                .append(tableName)
                .append(" (");
        for (String field : fields) {
            rawSQL.append(field).append(", ");
        }
        removeLastComma();
        rawSQL.append(") values(");
    }

    public SQLInsertBuilder values(Object... values) {
        for (Object value : values) {
            for (Handler handler : handlers) {
                value = handler.process(value);
            }
            rawSQL.append(value).append(",");
        }
        removeLastComma();
        rawSQL.append(")");
        return this;
    }

    private void removeLastComma() {
        final int lastCommaPosition = rawSQL.lastIndexOf(",");
        rawSQL = rawSQL.delete(lastCommaPosition, lastCommaPosition + 1);
    }

    public String compile() {
        return rawSQL.toString();
    }
}
