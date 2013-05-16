package br.com.vcosta.kata.sqlbuilder;

public interface Handler {

    static Handler[] all = new Handler[]{new StringHandler(), new DateHandler()};

    Object process(final Object value);
}
