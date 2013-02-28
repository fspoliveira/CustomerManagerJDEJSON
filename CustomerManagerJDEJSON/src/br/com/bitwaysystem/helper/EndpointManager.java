package br.com.bitwaysystem.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EndpointManager extends SQLiteOpenHelper {	
	
	public static final String DATABASE_NAME = "CustomerManager.db";
	public static final String DATABASE_TABLE = "endpoint";	
	public static final String KEY_ROWID = "id";
	public static final String KEY_URL = "url";

	public EndpointManager(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table " + DATABASE_TABLE
				+ " (id integer primary key ,"
				+ " url text )";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		String sql = "drop table if exists " + DATABASE_TABLE;
		db.execSQL(sql);
		onCreate(db);
	}
}