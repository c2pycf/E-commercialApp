package com.example.fang.walmartproject.data.source.local.WishListDb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.fang.walmartproject.data.Cart;
import com.example.fang.walmartproject.data.Product;
import com.example.fang.walmartproject.data.WishList;
import com.example.fang.walmartproject.data.source.WishListDataSource;

public class WishListLocalDataSource implements WishListDataSource {
    WishListDbHelper dbHelper;
    SQLiteDatabase database;
    static final String TAG = WishListLocalDataSource.class.getSimpleName();

    public WishListLocalDataSource(Context context) {
        this.dbHelper = new WishListDbHelper(context);
        this.database = dbHelper.getWritableDatabase();
    }

    @Override
    public void saveLater(Product product) {

        if (!checkIsDataExist(product.getId())) {
            Log.d(TAG, "Start saving products");
            database = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_ID, product.getId());
            values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_TITLE, product.getPname());
            values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_QUANTITY, product.getQuantity());
            values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_PRISE, product.getPrize());
            values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_DESCRIPTION, product.getDiscription());
            values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_IMAGEURL, product.getImage());
            values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_AMOUNT, product.getUserAmount());

            database.insert(WishListpersisteceContract.CartEntry.TABLE_NAME, null, values);
            database.close();
        }
        else{
            this.update(product);
        }

    }

    private boolean checkIsDataExist(String id) {
        Log.d(TAG,"Checking id is exsit");
        database = dbHelper.getWritableDatabase();
        String query = "Select * from " + WishListpersisteceContract.CartEntry.TABLE_NAME + " where "
                + WishListpersisteceContract.CartEntry.COLUMN_NAME_ID + " = " + id;
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

    @Override
    public WishList getList() {
        Log.d(TAG,"getting carts" );
        database = dbHelper.getWritableDatabase();
        String query = "Select * from " + WishListpersisteceContract.CartEntry.TABLE_NAME;
        Cursor cursor = database.rawQuery(query,null);
        cursor.moveToFirst();
        WishList wishList= new WishList();
        if(cursor.getCount()>0) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(WishListpersisteceContract.CartEntry.COLUMN_NAME_ID));
                String pname = cursor.getString(cursor.getColumnIndex(WishListpersisteceContract.CartEntry.COLUMN_NAME_TITLE));
                String quantity = cursor.getString(cursor.getColumnIndex(WishListpersisteceContract.CartEntry.COLUMN_NAME_QUANTITY));
                String prise = cursor.getString(cursor.getColumnIndex(WishListpersisteceContract.CartEntry.COLUMN_NAME_PRISE));
                String desc = cursor.getString(cursor.getColumnIndex(WishListpersisteceContract.CartEntry.COLUMN_NAME_DESCRIPTION));
                String imageUrl = cursor.getString(cursor.getColumnIndex(WishListpersisteceContract.CartEntry.COLUMN_NAME_IMAGEURL));
                String amount = cursor.getString(cursor.getColumnIndex(WishListpersisteceContract.CartEntry.COLUMN_NAME_AMOUNT));
                Product newProduct = new Product(id, pname, quantity, prise, desc, imageUrl);
                newProduct.setUserAmount(Integer.parseInt(amount));
                wishList.addProduct(newProduct);

            } while (cursor.moveToNext());
        }

        cursor.close();


        database.close();

        return wishList;
    }

    @Override
    public void clearList() {

    }

    @Override
    public void deleteProduct(String id) {
        database = dbHelper.getWritableDatabase();

        database.delete(WishListpersisteceContract.CartEntry.TABLE_NAME,WishListpersisteceContract.CartEntry.COLUMN_NAME_ID+"="+id,null );

        database.close();
    }

    @Override
    public void update(Product product) {
        String id = product.getId();
        database = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_ID, product.getId());
        values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_TITLE, product.getPname());
        values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_QUANTITY, product.getQuantity());
        values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_PRISE, product.getPrize());
        values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_DESCRIPTION, product.getDiscription());
        values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_IMAGEURL, product.getImage());
        values.put(WishListpersisteceContract.CartEntry.COLUMN_NAME_AMOUNT, product.getUserAmount());

        database.update(WishListpersisteceContract.CartEntry.TABLE_NAME,values, WishListpersisteceContract.CartEntry.COLUMN_NAME_ID+"="+id,null );

        Log.d(TAG,"Db updated ");

        database.close();

    }
}
