package com.example.fang.walmartproject.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.source.CartDataSource;

import java.util.List;

public class CartLocalDataSource implements CartDataSource {
    CartDbHelper dbHelper;
    SQLiteDatabase database;


    public CartLocalDataSource(Context context) {
       dbHelper = new CartDbHelper(context);
       database = dbHelper.getWritableDatabase();
    }

    @Override
    public void saveCart(Product product) {
        ContentValues values = new ContentValues();
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_ID,product.getId());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_TITLE,product.getPname());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_QUANTITY,product.getQuantity());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_PRISE,product.getPrize());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_DESCRIPTION,product.getDiscription());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_IMAGEURL,product.getImage());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_AMOUNT,product.getUserAmount());

        database.insert(CartsPersistenceContract.CartEntry.TABLE_NAME,null,values);
    }

    @Override
    public List<Product> getCarts() {
        return null;
    }

    @Override
    public void clearCart() {

    }

    @Override
    public void deleteProduct(String id) {

    }
}
