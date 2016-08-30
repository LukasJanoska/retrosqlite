package com.example.eagolukas.retrosqlite.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eagolukas.retrosqlite.retrof.Post;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 3;
	private static final String DATABASE_NAME = "dbName";

	private static final String TABLE_POSTS = "posts";
	private static final String KEY_ID = "id";
	private static final String KEY_TITLE = "title";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_POSTS_TABLE = "CREATE TABLE " + TABLE_POSTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT"
				+ ")";
		db.execSQL(CREATE_POSTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTS);

		// Create tables again
		onCreate(db);
	}

	// Adding new contact
	public void addPost(Post post) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, post.getTitle()); // Contact Name
		//values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone

		// Inserting Row
		db.insert(TABLE_POSTS, null, values);
		db.close(); // Closing database connection
	}

    // Getting All Contacts
    public List<Post> getPosts() {
        List<Post> contactList = new ArrayList<Post>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_POSTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Post contact = new Post();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTitle(cursor.getString(1));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

}
