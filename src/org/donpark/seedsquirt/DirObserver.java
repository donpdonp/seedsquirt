package org.donpark.seedsquirt;

import android.os.FileObserver;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

public class DirObserver extends FileObserver implements Constants {

    private Database _db;
    private String _path;
    
    public DirObserver(String path, Database db) {
        super(path, CREATE);
        _path = path;
        _db = db;
    }

    @Override
    public void onEvent(int event, String path) {
        if(path != null) {
            Log.d(APP_TAG,"Change "+event+" "+path);
            _db.insertUpload(path);
            PostHandler handler = new PostHandler(path, _db);
            String fullpath = _path+"/"+path;
            Post.filePost(fullpath, handler);
        }
    }

}
