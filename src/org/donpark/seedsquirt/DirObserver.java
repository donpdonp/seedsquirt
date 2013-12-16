package org.donpark.seedsquirt;

import android.content.Context;
import android.os.FileObserver;
import android.util.Log;
import android.widget.Toast;

public class DirObserver extends FileObserver implements Constants {

    private Context _ctx;
    
    public DirObserver(String path, Context ctx) {
        super(path, CREATE);
        _ctx = ctx;
    }

    @Override
    public void onEvent(int event, String path) {
        Log.d(APP_TAG,"Change "+event+" "+path);
    }

}
