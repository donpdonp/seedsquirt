package org.donpark.seedsquirt;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

public class WatchService extends Service implements Constants {

    public static DirObserver photos;

    @Override
    public void onStart(Intent start, int key){
        String photoPath = Environment.getExternalStorageDirectory()+"/DCIM/camera";
        photos = new DirObserver(photoPath, getBaseContext());
        Log.d(APP_TAG,"Begin watching "+photoPath);
        photos.startWatching();
    }
    
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onDestroy(){
        
    }
}
