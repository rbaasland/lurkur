package rbaasland.com.lurkur.activities;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Imgur {
    OkHttpClient client = new OkHttpClient();
    public int page = 0;
    public final String mClientID = "a91bf86cbee1fdf";
    public String mApiURL = "https://api.imgur.com/3/gallery/hot/viral/";
    public String jsondata;

    public Imgur() {}

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void getFeed() throws JSONException {
        Request request = new Request.Builder()
                .header("Authorization", "Client-ID " + mClientID)
                .url(mApiURL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            jsondata = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject resobj = new JSONObject(jsondata);

        JSONArray contacts = resobj.getJSONArray("data");

        // looping through All Contacts
        for (int i = 0; i < contacts.length(); i++) {
            JSONObject c = contacts.getJSONObject(i);

            Log.d("IMGUR_NEW", "ID: " + c.getString("id"));
            Log.d("IMGUR_NEW", "Title: " + c.getString("title"));
            if (c.has("images_count")) {
                Log.d("IMGUR_NEW", "Image Count: " + c.getString("images_count") + "\n");
            }
        }

    }
}
