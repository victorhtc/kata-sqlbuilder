package br.com.vcosta.kata.sqlbuilder;

public class SQLUpdateBuilder {

    private StringBuilder rawSQL = new StringBuilder();

    public SQLUpdateBuilder(final String tableName) {
        rawSQL.append("update")
                .append(" ")
                .append(tableName)
                .append(" ");
    }

    public SQLUpdateBuilder set(Object... values) {
        rawSQL.append("set")
                .append(" ");
        for (int i = 0; i < values.length; i += 2 ) {
            final Object fieldName = values[i];
            Object value = values[i+1];
            for (Handler handler : Handler.all) {
                value = handler.process(value);
            }
            rawSQL.append(fieldName)
                    .append("=")
                    .append(value)
                    .append(", ");
        }
        removeLastComma();
        return this;
    }
    
    public Where where(final String field){
        final Where where = new Where(rawSQL);
        return where.where(field);
    }

    private void removeLastComma() {
        final int lastCommaPosition = rawSQL.lastIndexOf(",");
        rawSQL = rawSQL.delete(lastCommaPosition, lastCommaPosition + 1);
    }

    public String compile() {
        return rawSQL.toString();
    }
}
