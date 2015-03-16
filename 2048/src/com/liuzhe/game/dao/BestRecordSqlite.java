package com.liuzhe.game.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BestRecordSqlite extends SQLiteOpenHelper {

	public BestRecordSqlite(Context context) {
		super(context, "bestRecord.db", null , 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table record(best integer)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
