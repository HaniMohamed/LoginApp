package com.proga.hani.login;

/**
 * Created by hani on 11/05/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "accountsManager";
    private static final String TABLE_ACCOUNTS = "accounts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PH_NO = "phone_number";
    private static final String KEY_EMAIL = "email";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNTS_TABLE = "CREATE TABLE " + TABLE_ACCOUNTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PASSWORD + " TEXT,"
                + KEY_PH_NO + " TEXT," + KEY_EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new account
    void addAccount(Accounts account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, account.getName());
        values.put(KEY_PASSWORD, account.getPassword());
        values.put(KEY_PH_NO, account.getPhoneNumber());
        values.put(KEY_EMAIL, account.getEmail());

        // Inserting Row
        db.insert(TABLE_ACCOUNTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single Account
    Accounts getAccount(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ACCOUNTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PASSWORD, KEY_PH_NO, KEY_EMAIL}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Accounts account = new Accounts(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return account;
    }

    // code to get all Accounts in a list view
    public List<Accounts> getAllAccounts() {
        List<Accounts> accountList = new ArrayList<Accounts>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ACCOUNTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Accounts account = new Accounts();
                account.setID(Integer.parseInt(cursor.getString(0)));
                account.setName(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setPhoneNumber(cursor.getString(3));
                account.setEmail(cursor.getString(4));
                // Adding account to list
                accountList.add(account);
            } while (cursor.moveToNext());
        }

        // return account list
        return accountList;
    }

    // code to update the single account
    public int updateAccount(Accounts account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, account.getName());
        values.put(KEY_PASSWORD, account.getPassword());
        values.put(KEY_PH_NO, account.getPhoneNumber());
        values.put(KEY_EMAIL, account.getEmail());

        // updating row
        return db.update(TABLE_ACCOUNTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(account.getID())});
    }

    // Deleting single account
    public void deleteAccount(Accounts account) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACCOUNTS, KEY_ID + " = ?",
                new String[]{String.valueOf(account.getID())});
        db.close();
    }

    // Getting accounts Count
    public int getAccountsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ACCOUNTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}