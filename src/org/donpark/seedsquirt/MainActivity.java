package org.donpark.seedsquirt;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements Constants {

    private Database db;
    private ListView mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(APP_TAG, "App starting. Starting service");
        Intent watch = new Intent(this, WatchService.class);
        startService(watch);
    }
    
    @Override
    protected void onResume(){
        super.onResume();
        db = new Database(this);
        db.open();
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,
                R.layout.row, db.unfinishedUploads(),
                new String[] {Database.FILENAME_COLUMN},
                new int[] {R.id.filename});
        mainList = (ListView) findViewById(R.id.uploads);
        mainList.setAdapter(adapter);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
