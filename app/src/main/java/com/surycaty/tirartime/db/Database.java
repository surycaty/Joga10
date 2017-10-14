package com.surycaty.tirartime.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by negus on 14/10/17.
 */

public class Database extends SQLiteOpenHelper {


    public Database(Context context) {

        super(context, "JOGA10", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL.getCreateJogador());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void open() {
        SQLiteDatabase db = getReadableDatabase();
    }
}
