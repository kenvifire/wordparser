package com.itluobo.wordtool.dao;

import java.sql.Connection;

/**
 * Created by kenvi on 16/2/16.
 */
public class BasicDao {
    Connection connection;

    public BasicDao(Connection connection) {
        this.connection = connection;
    }

    public void close() {
        DB.closeConnection(this.connection);
    }
}
