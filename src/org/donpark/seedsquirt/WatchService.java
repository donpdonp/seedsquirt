package org.donpark.seedsquirt;

import org.json.JSONArray;
import org.json.JSONObject;

import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class WatchService extends Service implements Constants {

    private final class PostResponse extends JsonHttpResponseHandler {
        @Override
        public void onSuccess(JSONObject response) {
            Log.d(APP_TAG, "Upload Success "+response);
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
        }
    }

    public static DirObserver photos;
    public static Database db;

    @Override
    public void onStart(Intent start, int key){
        db = new Database(getApplicationContext());
        db.open();
        String photoPath = Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DCIM+"/camera";
        photos = new DirObserver(photoPath, db, new PostResponse());
        Log.d(APP_TAG,"Begin watching "+photoPath);
        photos.startWatching();
    }
    
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onDestroy(){
        db.close();
    }
}
