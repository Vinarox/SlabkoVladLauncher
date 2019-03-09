package slabko.vladislav.slabkovladlauncher.AsyncTasks;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncFavicon extends AsyncTask<String, Void, Bitmap> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        URL url = null;
        final Bitmap[] dr = new Bitmap[1];
        try {
            params[0] = params[0].trim();
            url = new URL("https://favicon.yandex.net/favicon/" + params[0] + "?size=16");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();


            if(HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                dr[0] = BitmapFactory.decodeStream(in);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dr[0];
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
    }
}

