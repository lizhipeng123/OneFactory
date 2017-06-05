package com.debug.newfactory.debug.mylibraryone.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *  创建存储数据库
 * Created by lizhipeng on 2017/5/18.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "ldm_family"; // DB name
    private Context mcontext;
    private DBHelper mDbHelper;
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 11);
        this.mcontext = context;
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    /**
     * 用户第一次使用软件时调用的操作，用于获取数据库创建语句（SW）,然后创建数据库
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists family_bill(id integer primary key,time text,food text,use text,traffic text,travel text,clothes text,doctor text,laiwang text,baby text,live text,other text,remark text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /* 打开数据库,如果已经打开就使用，否则创建 */
    public DBHelper open() {
        if (null == mDbHelper) {
            mDbHelper = new DBHelper(mcontext);
        }
        db = mDbHelper.getWritableDatabase();
        return this;
    }

    /* 关闭数据库 */
    public void close() {
        db.close();
        mDbHelper.close();
    }

    /**添加数据 */
    public long insert(String tableName, ContentValues values) {
        return db.insert(tableName, null, values);
    }

    /**查询数据*/
    public Cursor findList(String tableName, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit) {
        return db.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    public Cursor exeSql(String sql) {
        return db.rawQuery(sql, null);
    }
}
