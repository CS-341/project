# project
https://alvinalexander.com/java/edu/pj/jdbc/jdbc0002
Above link shows how to create queries in java code whenever you are trying to insert into the database or table.
I can explain more in depth as well.

You may or may not have to do the following (only if the class doesn't work when you try and connect to the database):
You may need to add the JDBC driver to the build path within the JdbcSQLiteConnection class. 
Just download the latest driver here: https://bitbucket.org/xerial/sqlite-jdbc/downloads/
and right click on the class -> properties -> build path and add the file to wherever you saved it when you downloaded it.
