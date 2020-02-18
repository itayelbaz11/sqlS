package com.example.sqls;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.sqls.GRADES.GRADE;
import static com.example.sqls.GRADES.QUARTER;
import static com.example.sqls.GRADES.SUBJECT;
import static com.example.sqls.GRADES.TABLE_GRADES;
import static com.example.sqls.STUDENTS.ADDRESS;
import static com.example.sqls.STUDENTS.DAD_NAME;
import static com.example.sqls.STUDENTS.DAD_NUM;
import static com.example.sqls.STUDENTS.HOME_P;
import static com.example.sqls.STUDENTS.KEY_ID;
import static com.example.sqls.STUDENTS.MOM_NAME;
import static com.example.sqls.STUDENTS.MOM_NUM;
import static com.example.sqls.STUDENTS.NAME;
import static com.example.sqls.STUDENTS.PHONE;
import static com.example.sqls.STUDENTS.TABLE_STUDENTS;


public class HelperDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 1;
    String strCreate, strDelete;

    public HelperDB(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate="CREATE TABLE "+TABLE_STUDENTS;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+NAME+" TEXT,";
        strCreate+=" "+ADDRESS+" TEXT,";
        strCreate+=" "+PHONE+" INTEGER";
        strCreate+=" "+HOME_P+" INTEGER";
        strCreate+=" "+MOM_NAME+" TEXT,";
        strCreate+=" "+MOM_NUM+" INTEGER";
        strCreate+=" "+DAD_NAME+" TEXT,";
        strCreate+=" "+DAD_NUM+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE " + TABLE_GRADES;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+NAME+" TEXT,";
        strCreate+=" "+QUARTER+" TEXT";
        strCreate+=" "+GRADE+" TEXT";
        strCreate+=" "+SUBJECT+" TEXT";
        strCreate+=");";
        db.execSQL(strCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        strDelete = "DROP TABLE IF EXISTS "+ TABLE_STUDENTS;
        db.execSQL(strDelete);

        strDelete = "DROP TABLE IF EXISTS "+ TABLE_GRADES;
        db.execSQL(strDelete);

        onCreate(db);

    }
}