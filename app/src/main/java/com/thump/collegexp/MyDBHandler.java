package com.thump.collegexp;/*
package com.example.jsonapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_COURSE = "course";
    public static final String COLUMN_IMAGE_URL = "imageUrl";



    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE " + TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_COURSE + "ARRAY" + ");";
        db.execSQL(query1);
    }
    //Lesson 51
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addProduct(College college){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, college.getName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //Delete a product from the database
    public void deleteProduct(String productName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_NAME + "=\"" + productName + "\";");
    }

    // this is goint in record_TextView in the Main activity.
    public ArrayList<College> getProducts(){
        ArrayList<College> productsLists = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PRODUCTS;// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor cursor = db.rawQuery(query, null);
        //Move to the first row in your results
        if(cursor.moveToFirst()){
            do {
                //Position after the last row means the end of the results
                College product = new College();
                product.setId(Integer.parseInt(cursor.getString(0)));
                product.setName(cursor.getString(1));

                productsLists.add(product);

            }while(cursor.moveToNext());
        }
        db.close();
        return productsLists;
    }

}
*/
