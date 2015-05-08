package com.saroty.ter.database;

import java.util.List;

/**
 * Created by Arthur on 07/05/2015.
 */
public abstract class DatabaseTable<T, I>
{
    protected final String TABLE_NAME;
    protected final String TABLE_CREATE_SQL;

    public DatabaseTable(String tableName, String tableCreateSQL)
    {
        TABLE_NAME = tableName;
        TABLE_CREATE_SQL = tableCreateSQL;
    }

    public String getTableCreateSQL()
    {
        return TABLE_CREATE_SQL;
    }

    public String getTableName()
    {
        return TABLE_NAME;
    }

    public void insertOne(T t)
    {
        throw new UnsupportedOperationException();
    }

    public void deleteOne(T t)
    {
        throw new UnsupportedOperationException();
    }

    public void updateOne(T t)
    {
        throw new UnsupportedOperationException();
    }

    public T readOne(I i)
    {
        throw new UnsupportedOperationException();
    }

    public List<T> readAll()
    {
        throw new UnsupportedOperationException();
    }
}
