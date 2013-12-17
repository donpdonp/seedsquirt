package org.donpark.seedsquirt;

import org.json.JSONObject;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;

public class PostHandler extends JsonHttpResponseHandler implements Constants {
    private String path;
    private Database db;
    
    public PostHandler(String path, Database _db){
        this.path = path;
        this.db = db;
    }
    @Override
    public void onSuccess(JSONObject response) {
        Log.d(APP_TAG, "Upload Success "+response);
        db.setStatus(Database.SUCCESS_STATUS, path);
    }
    @Override
    public void onStart() {
        Log.d(APP_TAG, "Upload Start! ");
    }
    @Override
    public void onProgress(int bytesWritten, int bytesTotal) {
        Log.d(APP_TAG, "Upload Progress! written="+bytesWritten+" total="+bytesTotal);
    }
    @Override
    public void onFailure(Throwable e, JSONObject response) {
        Log.d(APP_TAG, "Upload Error "+e+" "+response);
        db.setStatus(Database.ERROR_STATUS, path);
    }
}

