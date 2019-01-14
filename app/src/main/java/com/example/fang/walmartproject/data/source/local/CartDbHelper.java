package com.example.fang.walmartproject.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CartDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Carts.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CartsPersistenceContract.CartEntry.TABLE_NAME + " (" +
                    CartsPersistenceContract.CartEntry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + " PRIMARY KEY," +
                    CartsPersistenceContract.CartEntry.COLUMN_NAME_ID + TEXT_TYPE + COMMA_SEP +
                    CartsPersistenceContract.CartEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    CartsPersistenceContract.CartEntry.COLUMN_NAME_QUANTITY + TEXT_TYPE + COMMA_SEP +
                    CartsPersistenceContract.CartEntry.COLUMN_NAME_PRISE + TEXT_TYPE + COMMA_SEP +
                    CartsPersistenceContract.CartEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    CartsPersistenceContract.CartEntry.COLUMN_NAME_IMAGEURL + TEXT_TYPE + COMMA_SEP +
                    CartsPersistenceContract.CartEntry.COLUMN_NAME_AMOUNT + BOOLEAN_TYPE +
                    " )";


    public CartDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
