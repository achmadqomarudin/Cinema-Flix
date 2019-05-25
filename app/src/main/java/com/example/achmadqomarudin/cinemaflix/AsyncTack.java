package com.example.achmadqomarudin.cinemaflix;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Achmad Qomarudin on 04/12/2017.
 */

public class AsyncTack extends AsyncTask<String, String, String> {

    private String TAG = AsyncTack.class.getSimpleName();
    private RecyclerView recyclerView;
    private Context context;
    private ProgressDialog pDialog;

    public AsyncTack(RecyclerView recyclerView, Context context) {
        this.recyclerView = recyclerView;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        Log.e(TAG, "success");

        /**Menampilkan progress dialog**/
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Mohon tunggu sebentar...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(Api.api)
                .get()
                .build();

        try {
            //get response api
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        if (s == null) {
            Toast.makeText(context, "Data Kosong", Toast.LENGTH_SHORT).show();
            pDialog.dismiss();
        } else {

            try {
                pDialog.dismiss();
                /*get json data api*/
                JSONObject jsonObject = new JSONObject(s);
                JSONArray results = jsonObject.getJSONArray("results");

                List<ModelData> data = new ArrayList<>();

                for (int i = 0; i < results.length(); i++) {
                    JSONObject object = results.getJSONObject(i);

                    ModelData modelData = new ModelData();

                    modelData.setVote_count(object.getString("vote_count"));
                    modelData.setVote_average(object.getString("vote_average"));
                    modelData.setTitle(object.getString("title"));
                    modelData.setPopularity(object.getString("popularity"));
                    modelData.setPoster_path(object.getString("poster_path"));
                    modelData.setBackdrop_path(object.getString("backdrop_path"));
                    modelData.setOverview(object.getString("overview"));
                    modelData.setRelease_date(object.getString("release_date"));

                    data.add(modelData);
                }

                AdapterRecyclerView view = new AdapterRecyclerView(data, context);
                recyclerView.setAdapter(view);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
