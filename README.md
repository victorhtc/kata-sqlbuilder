kata-sqlbuilder
===============

Simple kata exercice:

Given table metadata (name, columns/types, etc.), generate a well-formed SQL string. For example:

String[] columns = { "a", "b" };
assertEquals("select a,b,c from x", sql.select(tableName, columns));

Do this for the usual SQL queries: SELECT, INSERT, UPDATE, DELETE.


***For documentation, read the tests...
