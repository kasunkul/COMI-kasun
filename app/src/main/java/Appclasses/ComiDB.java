package Appclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dealwis on 5/3/16.
 */
public class ComiDB  extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "comi.db";
    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_CONTACT = "contact";
    public static final String USER_COLUMN_LOCATION= "location";
    public static final String USER_COLUMN_FLAG = "flag";
    public static final String USER_COLUMN_ID = "id";
    private HashMap hp;

    public ComiDB(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table user " +
                        "(id integer primary key, name text,email text,username text,contact text, location text,flag int)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public boolean insertuser (String name, String contact, String email, String location,String username,int flag)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("contact", contact);
        contentValues.put("email", email);
        contentValues.put("location", location);
        contentValues.put("flag", flag);
        contentValues.put("username", username);
        db.insert("user", null, contentValues);
        return true;
    }

    public Cursor getAllData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from user where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USER_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }
    public boolean SetUserFlag (String email,int flag)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("flag", flag);
        db.update("user", contentValues, "email = ? ", new String[] { email } );
        return true;
    }


    public Integer deleteuser (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("user",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public void resetUserTbl(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL ("drop table IF EXISTS user");
//        db.close();
        this.onCreate (db);
    }

    public ArrayList<String> getAlluser()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from user", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(USER_COLUMN_USERNAME)));
            res.moveToNext();
        }
        return array_list;
    }


}
