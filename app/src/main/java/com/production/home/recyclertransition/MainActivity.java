package com.production.home.recyclertransition;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setHasFixedSize(true);

        new GetData().execute();

    }


    public class GetData extends AsyncTask<String, Void, Void> {

        String responseString = null;

        @Override
        protected Void doInBackground(String... str) {

            OkHttpClient client = new OkHttpClient();
            RequestBody requestBody = new FormBody.Builder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://api.androidhive.info/json/movies.json")
                    .post(requestBody)
                    .build();

            try {

                Response response = client.newCall(request).execute();
                if (!response.isSuccessful()) throw new IOException("Unexpected code" + response);
                {

                    responseString = response.body().string();
                    System.out.println(responseString);
                    response.body().close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            if (responseString != null) {

                movieList = new ArrayList<>();

                try {
                    JSONArray jsonArray = new JSONArray(responseString);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String title = jsonObject.optString("title");
                        String rating = jsonObject.optString("rating");
                        String image = jsonObject.optString("image");
                        String releaseYear = jsonObject.optString("releaseYear");

                        Movie movie = new Movie(title,rating,image,releaseYear);
                        movieList.add(movie);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RVAdapter rvAdapter = new RVAdapter(movieList,MainActivity.this);
                recyclerview.setAdapter(rvAdapter);

            }

        }


    }


}
