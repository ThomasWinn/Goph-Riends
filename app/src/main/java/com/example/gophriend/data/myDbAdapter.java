package com.example.gophriend.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class myDbAdapter {
    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name, String pass, String displayname, String email, String dob, String major, String year, String interests)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.MyPASSWORD, pass);
        contentValues.put(myDbHelper.DisplayName, displayname);
        contentValues.put(myDbHelper.EMAIL, email);
        contentValues.put(myDbHelper.DOB, dob);
        contentValues.put(myDbHelper.MAJOR, major);
        contentValues.put(myDbHelper.YEAR, year);
        contentValues.put(myDbHelper.INTERESTS, interests);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MyPASSWORD, myDbHelper.DisplayName, myDbHelper.EMAIL, myDbHelper.DOB, myDbHelper.MAJOR, myDbHelper.YEAR, myDbHelper.INTERESTS};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            String  displayname =cursor.getString(cursor.getColumnIndex(myDbHelper.DisplayName));
            String  email =cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL));
            String  dob =cursor.getString(cursor.getColumnIndex(myDbHelper.DOB));
            String  major =cursor.getString(cursor.getColumnIndex(myDbHelper.MAJOR));
            String  year =cursor.getString(cursor.getColumnIndex(myDbHelper.YEAR));
            String  interests =cursor.getString(cursor.getColumnIndex(myDbHelper.INTERESTS));
            buffer.append(cid+ "   " + name + "   " + password +  "   " +   displayname +  "   "  + email + "   "  + dob  + "   " + major + "   "  + year + "   " + interests + " " + " \n");
        }
        return buffer.toString();
    }

    public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
        return  count;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";
        private static final String TABLE_NAME = "myTable";
        private static final int DATABASE_Version = 4;
        private static final String UID="id";
        private static final String NAME = "Name";
        private static final String MyPASSWORD= "Password";
        private static final String DisplayName= "DisplayName";
        private static final String EMAIL= "EMAIL";
        private static final String DOB= "DOB";
        private static final String MAJOR= "MAJOR";
        private static final String YEAR= "YEAR";
        private static final String INTERESTS= "INTERESTS";

        private static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " ("
                        + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + NAME+ " VARCHAR(255) , "
                        + MyPASSWORD+ " VARCHAR(225) , "
                        + DisplayName+ " VARCHAR(255), "
                        + EMAIL+ " VARCHAR(255), "
                        + DOB+ " VARCHAR(255), "
                        + MAJOR+ " VARCHAR(255), "
                        + YEAR+ " VARCHAR(255), "
                        + INTERESTS+ " VARCHAR(255)" + ")";

        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                Message.message(context,""+e);
            }
        }
    }
}