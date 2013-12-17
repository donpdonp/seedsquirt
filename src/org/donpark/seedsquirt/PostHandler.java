package org.donpark.seedsquirt;

import org.apache.http.Header;
import org.json.JSONObject;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

public class PostHandler extends TextHttpResponseHandler implements Constants {
    private String path;
    private Database db;
    
    public PostHandler(String path, Database db){
        this.path = path;
        this.db = db;
    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, String response) {
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
    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable){
        Log.d(APP_TAG, "Upload Error "+statusCode+" "+response+" "+throwable);
        db.setStatus(Database.ERROR_STATUS, path);
    }
}

