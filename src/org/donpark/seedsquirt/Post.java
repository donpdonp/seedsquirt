package org.donpark.seedsquirt;

import java.io.File;
import java.io.FileNotFoundException;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;


public class Post implements Constants {
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static String URL = "http://cdn.donpark.org/i/upload";
    
    public static void filePost(String filename, AsyncHttpResponseHandler handler){
        File myFile = new File(filename);
        RequestParams params = new RequestParams();
        try {
            params.put("myfile", myFile);
            Log.d(APP_TAG,"Posting "+filename+" to "+URL);
            client.post(URL, handler);
        } catch(FileNotFoundException e) {}
    }
}

