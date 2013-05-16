package br.com.vcosta.kata.sqlbuilder;

public class StringHandler implements Handler {

    public Object process(final Object value) {
        if (value instanceof String) {
            return "'"+avoidSillySQLInjection(value.toString())+"'";
        } else {
            return value;
        }
    }
    
    private String avoidSillySQLInjection(final String value){
        return value.replace("'", "''")
                .replace("--", "")
                .replace("\'", "")
                .replace("\\", "")
                .replace("/", "");
    }

}
