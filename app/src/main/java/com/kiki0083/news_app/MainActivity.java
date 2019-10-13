package com.kiki0083.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] myDataset = {"test1","test2","test3"};
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        queue = Volley.newRequestQueue(this);
        getNews();

    }

    public void getNews(){

        String url ="https://newsapi.org/v2/top-headlines?country=kr&apiKey=e0a58cb4198e48b58267718a8b46f935";

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject newsdata  = new JSONObject(response);
                                    JSONArray  jsonArray = newsdata.getJSONArray("articles");

                                    List<NewsData> list= new ArrayList<NewsData>();

                                    for(int i=0; i < jsonArray.length(); i++){
                                        JSONObject obj = jsonArray.getJSONObject(i);

                                        NewsData data = new NewsData();
                                        data.setTitle(obj.getString("title"));
                                        data.setDescription(obj.getString("description"));
                                        data.setUrlToImage(obj.getString("urlToImage"));

                                        list.add(data);

                                    }

                                    // specify an adapter (see also next example)
                                    mAdapter = new MyAdapter(list, MainActivity.this);
                                    recyclerView.setAdapter(mAdapter);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("에러 ===>",error.getMessage());
                    }
                });

                queue.add(stringRequest);

    }

}

