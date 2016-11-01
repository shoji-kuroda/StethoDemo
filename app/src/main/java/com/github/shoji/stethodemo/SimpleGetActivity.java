package com.github.shoji.stethodemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shoji.kuroda on 2016/11/01.
 */
public class SimpleGetActivity extends AppCompatActivity {

    private static final String TAG = SimpleGetActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Get get = new Get();
                    String response = get.run("https://github.com/facebook/stetho/blob/master/README.md");
                    Log.d(TAG, response);
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        });
    }

    private class Get {

        OkHttpClient client;

        public Get() {
            this.client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();
        }

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }
}

