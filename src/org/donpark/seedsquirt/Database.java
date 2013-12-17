package org.donpark.seedsquirt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database implements Constants {
    public static final String DB_NAME="files";
    public static int DB_VERSION=1;
    
    public static final String UPLOADS_TABLE="uploads";
    public static final String FILENAME_COLUMN="filename";
    public static final String URL_COLUMN="url";
    public static final String STATUS_COLUMN="status";
    public static final String UPLOADED_AT_COLUMN="uploaded_at";

    public static String INPROGRESS_STATUS = "inprogress";
    public static String SUCCESS_STATUS = "success";
    public static String ERROR_STATUS = "failed";

    private final Context _context;
    private final DbHelper _helper;
    private SQLiteDatabase _db;

    public Database(Context context){
        _context = context;
        _helper = new DbHelper(_context, DB_NAME, null, DB_VERSION);        
    }
    
    public void open(){
        _db = _helper.getWritableDatabase();
    }

    public void close(){
        _db.close();
    }
    
    public void insertUpload(String fileName){
        Log.d(APP_TAG, "inserting "+fileName);
        ContentValues cv = new ContentValues();
        cv.put(FILENAME_COLUMN, fileName);
        cv.put(STATUS_COLUMN, INPROGRESS_STATUS);

        _db.insert(UPLOADS_TABLE, null, cv);
    }
    
    public Cursor uploads(){
        return _db.rawQuery("SELECT * FROM "+UPLOADS_TABLE, null);        
    }

    public Cursor uploads(String status){
        return _db.rawQuery("SELECT *"+
                 " FROM "+UPLOADS_TABLE+
                 " WHERE "+STATUS_COLUMN+" = ?",
                 new String[] {status});        
    }

    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context, String dbName, CursorFactory factory, int version) {
            super(context, dbName, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+UPLOADS_TABLE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    FILENAME_COLUMN+" TEXT, "+
                    URL_COLUMN+" TEXT, "+
                    STATUS_COLUMN+" TEXT, "+
                    UPLOADED_AT_COLUMN+" DATETIME);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            
        }
        
    }

    public void setStatus(String status, String filename) {
        ContentValues cv = new ContentValues();
        cv.put(STATUS_COLUMN, status);
        _db.update(UPLOADS_TABLE, cv, FILENAME_COLUMN+" = ?", new String[] {filename});
    }
}
