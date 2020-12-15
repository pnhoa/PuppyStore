package com.example.app_ban_hang.model;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.widget.Toast;
import 	android.content.ContentValues;

import java.util.ArrayList;
import java.util.List;

public class DatabaseThanhToan {
    String table = "thanhtoan";
    private Context context;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseThanhToan instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseThanhToan(Context context) {
        this.openHelper = new DatabaseOpenHelperFavourite(context);
        this.context=context;
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseThanhToan getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseThanhToan(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all words from anh_viet dictionary
     *
     * @return a List of word from dictionary
     */
    public List<String> getWordsFavoutire() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM thanhtoan", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public void insertData(String name){
        //Boolean x= database.execSQL("Insert into fav_table(name,definition) values('"+name+"','"+definition+"')");

        ContentValues newValues = new ContentValues();
        newValues.put("name", name);
        //  newValues.put("definition", definition);




        long rowInserted  = database.insert(table, null, newValues);
        if(rowInserted != -1)
            Toast.makeText(context.getApplicationContext(), "New row added, row id: " + rowInserted, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context.getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();


    }
    public void deleteData(int  position){
        database.delete(table,"ID=? ",new String[]{"'position'"});
        Toast.makeText(context.getApplicationContext(), " deleted success", Toast.LENGTH_SHORT).show();
    }
}
