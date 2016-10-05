package com.mlabs.bbm.iquiz;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
//hi hello
public class DataBaseAdapter {
    static final String DATABASE_NAME = "users.db";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE = "Create Table " + "USERS" +
            "( " + "ID" + " Integer Primary Key Autoincrement," +
            "FNAME text,LNAME text,UNAME text,EMAIL  text,PASSWORD text); ";
    public static SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public DataBaseAdapter(Context _context) {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataBaseAdapter open() throws SQLException {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
    }

    public SQLiteDatabase getDatabaseInstance() {
        return db;
    }

    public static void insertEntry(String firstname, String lastname, String uname,String email, String password) {
        ContentValues newValues = new ContentValues();
        newValues.put("FNAME", firstname);
        newValues.put("LNAME", lastname);
        newValues.put("UNAME", uname);
        newValues.put("EMAIL", email);
        newValues.put("PASSWORD", password);
        db.insert("USERS", null, newValues);
        /**Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();**/
    }

    /**public int deleteEntry(String email) {
     String where = "EMAIL=?";
     int numberOFEntriesDeleted = db.delete("USERS", where, new String[]{email});
     Toast.makeText(context, "Number for Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
     return numberOFEntriesDeleted;
     }**/

    public static String getSinlgeEntry(String email) {
        Cursor cursor = db.query("USERS", null, " EMAIL=?", new String[]{email}, null, null, null);
        if (cursor.getCount() < 1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
    public static String getUsername(String uname) {

        Cursor cursor = db.query("USERS", null, " UNAME=?", new String[]{uname}, null, null, null);
        if (cursor.getCount() < 1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public String getUsernameforsignup(String uname) {

        Cursor cursor = db.query("USERS", null, " UNAME=?", new String[]{uname}, null, null, null);
        if (cursor.getCount() < 1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("UNAME"));
        cursor.close();
        return password;
    }

    public String getEmailforsignup(String email) {

        Cursor cursor = db.query("USERS", null, " EMAIL=?", new String[]{email}, null, null, null);
        if (cursor.getCount() < 1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("EMAIL"));
        cursor.close();
        return password;
    }

    public void updateEntry(String email, String pword) {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("EMAIL", email);
        updatedValues.put("PASSWORD", pword);

        String where = "EMAIL = ?";
        db.update("USERS", updatedValues, where, new String[]{email});
    }
}