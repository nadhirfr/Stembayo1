package id.ayobantu.simple.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import id.ayobantu.simple.Model.User;

/**
 * Created by fachrul on 4/2/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "db_user";

    // Contacts table name
    private static final String TABLE_USER = "user";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USER = "user";
    private static final String KEY_PASSWORD = "password";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USER + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();

    }

    public int findUser(String user, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        int id;
        Cursor cursor = db.query(TABLE_USER, new String[]{KEY_ID, KEY_USER, KEY_PASSWORD},
                KEY_USER + "= ? AND " + KEY_PASSWORD + "= ?", new String[]{user, password}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            id = Integer.valueOf(cursor.getString(0));
        } else {
            id = 0;
        }

        return id;
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[]{KEY_ID, KEY_USER, KEY_PASSWORD},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        return user;
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));

                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }

    public int getUserCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());

        return db.update(TABLE_USER, values, KEY_ID + "= ?",
                new String[]{String.valueOf(user.getId())});
    }

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, KEY_ID + "= ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
}
