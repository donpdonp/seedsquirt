package org.donpark.seedsquirt;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {
    public static final String DB_NAME="files";
    public static int DB_VERSION=1;
    
    public static final String UPLOADS_TABLE="uploads";

    private final Context _context;
    private final DbHelper _helper;

    public Database(Context context){
        _context = context;
        _helper = new DbHelper(_context, DB_NAME, null, DB_VERSION);
    }
    
    public void close(){
        _helper.close();
    }
    
    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context, String dbName, CursorFactory factory, int version) {
            super(context, dbName, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+UPLOADS_TABLE+" (_id integer primary key, filename text, url text, uploaded_at datetime)");            
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            
        }
        
    }
}
