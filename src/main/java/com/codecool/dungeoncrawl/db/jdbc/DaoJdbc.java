package com.codecool.dungeoncrawl.db.jdbc;

import javax.sql.DataSource;

abstract class DaoJdbc {

    protected DataSource dataSource;

    public DaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
