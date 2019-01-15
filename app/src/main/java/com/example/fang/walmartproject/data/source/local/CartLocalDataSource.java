package com.example.fang.walmartproject.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.source.CartDataSource;

import java.util.List;

public class CartLocalDataSource implements CartDataSource {
    CartDbHelper dbHelper;
    SQLiteDatabase database;
    final static private String TAG = CartLocalDataSource.class.getSimpleName();


    public CartLocalDataSource(Context context) {
       dbHelper = new CartDbHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void saveCart(Product product) {
        if (!checkIsDataExist(product.getId())) {
            Log.d(TAG,"Start saving products");
            database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_ID, product.getId());
            values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_TITLE, product.getPname());
            values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_QUANTITY, product.getQuantity());
            values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_PRISE, product.getPrize());
            values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_DESCRIPTION, product.getDiscription());
            values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_IMAGEURL, product.getImage());
            values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_AMOUNT, product.getUserAmount());

            database.insert(CartsPersistenceContract.CartEntry.TABLE_NAME, null, values);
            database.close();

        }
        else{
            this.update(product);
        }
    }

    @Override
    public Cart getCarts() {
        Log.d(TAG,"getting carts" );
        database = dbHelper.getWritableDatabase();
        String query = "Select * from " + CartsPersistenceContract.CartEntry.TABLE_NAME;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        Cart cart = new Cart();
        do {
            String id = cursor.getString(cursor.getColumnIndex(CartsPersistenceContract.CartEntry.COLUMN_NAME_ID));
            String pname = cursor.getString(cursor.getColumnIndex(CartsPersistenceContract.CartEntry.COLUMN_NAME_TITLE));
            String quantity = cursor.getString(cursor.getColumnIndex(CartsPersistenceContract.CartEntry.COLUMN_NAME_QUANTITY));
            String prise = cursor.getString(cursor.getColumnIndex(CartsPersistenceContract.CartEntry.COLUMN_NAME_PRISE));
            String desc = cursor.getString(cursor.getColumnIndex(CartsPersistenceContract.CartEntry.COLUMN_NAME_DESCRIPTION));
            String imageUrl = cursor.getString(cursor.getColumnIndex(CartsPersistenceContract.CartEntry.COLUMN_NAME_IMAGEURL));
            String amount = cursor.getString(cursor.getColumnIndex(CartsPersistenceContract.CartEntry.COLUMN_NAME_AMOUNT));
            Product newProduct = new Product(id,pname,quantity,prise,desc,imageUrl);
            newProduct.setUserAmount(Integer.parseInt(amount));
            cart.addProduct(newProduct);
            Log.d(TAG,"prise is " + cart.getTotalPrize());

        }while (cursor.moveToNext());


        cursor.close();


        database.close();

        return cart;
    }

    @Override
    public void clearCart() {

    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public void update(Product product) {
        String id = product.getId();
        database = dbHelper.getWritableDatabase();

        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_ID, product.getId());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_TITLE, product.getPname());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_QUANTITY, product.getQuantity());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_PRISE, product.getPrize());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_DESCRIPTION, product.getDiscription());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_IMAGEURL, product.getImage());
        values.put(CartsPersistenceContract.CartEntry.COLUMN_NAME_AMOUNT, product.getUserAmount());

        database.update(CartsPersistenceContract.CartEntry.TABLE_NAME,values, CartsPersistenceContract.CartEntry.COLUMN_NAME_ID+"="+id,null );

        Log.d(TAG,"Db updated ");

        database.close();


    }

    public boolean checkIsDataExist(String id){
        Log.d(TAG,"Checking id is exsit");
        database = dbHelper.getWritableDatabase();
        String query = "Select * from " + CartsPersistenceContract.CartEntry.TABLE_NAME + " where "
                + CartsPersistenceContract.CartEntry.COLUMN_NAME_ID + " = " + id;
        Cursor cursor = database.rawQuery(query,null);
        if(cursor.getCount() <= 0){
            Log.d(TAG,"Data is on db return false");
            cursor.close();
            return false;
        }
        cursor.close();
        database.close();
        return true;

    }
}
