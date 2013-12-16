package org.donpark.seedsquirt;

import android.content.Context;
import android.os.FileObserver;
import android.util.Log;
import android.widget.Toast;

public class DirObserver extends FileObserver implements Constants {

    private Database _db;
    
    public DirObserver(String path, Database db) {
        super(path, CREATE);
        _db = db;
    }

    @Override
    public void onEvent(int event, String path) {
        Log.d(APP_TAG,"Change "+event+" "+path);
        _db.insertUpload(path);
    }

}
