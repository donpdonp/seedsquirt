package org.donpark.seedsquirt;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class WatchService extends Service implements Constants {

    public static DirObserver photos;
    public static Database db;

    @Override
    public void onStart(Intent start, int key){
        db = new Database(getApplicationContext());
        db.open();
        String photoPath = Environment.getExternalStorageDirectory()+"/DCIM/camera";
        photos = new DirObserver(photoPath, db);
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
