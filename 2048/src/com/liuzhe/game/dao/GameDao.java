package com.liuzhe.game.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GameDao {
	private BestRecordSqlite helper;
	private int best = 0;

	public GameDao(Context context) {
		helper = new BestRecordSqlite(context);
	}
	public void add(int score){
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("best", score);
		db.insert("record", null, values);
	}
	public int find() {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from record", null);
		if(cursor.moveToNext()){
			best = cursor.getInt(cursor.getColumnIndex("best"));
		}else{
			add(best);
		}
		cursor.close();
		db.close();
		return best;
	}

	public void update(int best) {
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("update record set best=?", new Object[] { best });
		db.close();
	}
}
