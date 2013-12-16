package org.donpark.seedsquirt;

import android.os.FileObserver;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

public class DirObserver extends FileObserver implements Constants {

    public static String PATH="/DCIM/camera";
    private Database _db;
    private JsonHttpResponseHandler _handler;
    
    public DirObserver(String path, Database db, JsonHttpResponseHandler handler) {
        super(path, CREATE);
        _db = db;
        _handler = handler;
    }

    @Override
    public void onEvent(int event, String path) {
        Log.d(APP_TAG,"Change "+event+" "+path);
        _db.insertUpload(path);
        String fullpath = PATH+"/"+path;
        Post.filePost(fullpath, _handler);
    }

}
