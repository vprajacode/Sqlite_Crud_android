package com.example.sqlitecr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context,"User.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usertable(name TEXT primary key,contact TEXT,email TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usertable");

    }
    public Boolean insertdata(String name_val,String contact_val,String email_val)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values  .put("name",name_val);
        values  .put("contact",contact_val);
        values  .put("email",email_val);
        long result= DB.insert("usertable",null,values);
        if(result==-1)
        {
            return false;
        }
        else {
            return true;
        }
    }
    //update the data
    public Boolean updatedata(String name_val,String contact_val,String email_val)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cvalues= new ContentValues();
        cvalues  .put("name",name_val);
       cvalues  .put("contact",contact_val);
        cvalues  .put("email",email_val);
        Cursor cur =DB.rawQuery("select * from usertable where name=?",new String[]{name_val});
        if (cur.getCount() >0)
        {
            long result= DB.update("usertable",cvalues,"name=?",new String[] {name_val});
            if(result==-1)
            {
                return false;
            }
            else {
                return true;
            }

        }
        else{
            return false;
        }

    }
    //delete data
    public Boolean deletedata(String name_val)
    {
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cur =DB.rawQuery("select * from usertable where name=?",new String[]{name_val});
        if (cur.getCount() >0)
        {
            long result= DB.delete("usertable","name=?",new String[]{name_val});
            if(result==-1)
            {
                return false;
            }
            else {
                return true;
            }

        }
        else{
            return false;
        }

    }

    public Cursor getdata()
    {
        SQLiteDatabase db=this .getWritableDatabase();
        Cursor curr=db.rawQuery("Select * from usertable",null);
        return curr;
    }

}
