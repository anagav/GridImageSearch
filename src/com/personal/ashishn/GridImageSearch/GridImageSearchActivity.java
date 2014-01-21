package com.personal.ashishn.GridImageSearch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestHandle;
import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridImageSearchActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    EditText searchBox;
    GridView gridView;
    ArrayList<String> results= new ArrayList<String>();
    ImageAdapter imgAdapter = new ImageAdapter(this, results);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        searchBox = (EditText) findViewById(R.id.searchBox);
        gridView = (GridView) findViewById(R.id.searchResults);
        gridView.setAdapter(imgAdapter);
        gridView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi(page);
            }
        });

    }

    private void customLoadMoreDataFromApi(int offset) {

        System.out.println("offset="+offset);
        Object waitforResults = new Object();
        AsyncHttpClient client = new AsyncHttpClient();

        RequestHandle requestHandle = client.get("https://ajax.googleapis.com/ajax/services/search/images?rsz=8&v=1.0&q=" + searchBox.getText().toString()
                + "&start=" + offset, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray json = new JSONObject(response).getJSONObject("responseData").getJSONArray("results");
                    for (int i = 0; i < json.length(); i++) {
                        String url = json.getJSONObject(i).getString("url");
                        System.out.println("adding url " + url);
                        results.add(url);

                    }


                } catch (JSONException e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("response=" + Arrays.toString(responseBody));
            }
        }
        );


        while(!requestHandle.isFinished()){

        }
        imgAdapter.setData(results);



    }

    public void onProfileView(MenuItem item) {

        Intent i = new Intent(GridImageSearchActivity.this, ConfigurationActivity.class);
        startActivity(i);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public void doSearch(View view) throws IOException, JSONException {
        //AsyncHttpClient client = new AsyncHttpClient();
        if (searchBox.getText() == null || searchBox.getText().toString().isEmpty()) {
            Toast.makeText(getBaseContext(), " Enter a Stirng", Toast.LENGTH_SHORT).show();
            return;
        }
           imgAdapter.clear();
        customLoadMoreDataFromApi(0);
       /* AsyncHttpClient client = new AsyncHttpClient();
        final List<String> urls = new ArrayList<String>();
        client.get("https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=" + searchBox.getText().toString(), new
                AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(String response) {
                        try {
                            JSONArray json = new JSONObject(response).getJSONObject("responseData").getJSONArray("results");
                            for(int i=0;i < json.length(); i++){
                                String url=json.getJSONObject(i).getString("url");
                                System.out.println("adding url "+url);
                                urls.add(url);

                            }

                        } catch (JSONException e) {
                            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                       System.out.println("response="+ Arrays.toString(responseBody));
                    }
                }

        );


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, urls);
        gridView.setAdapter(adapter);*/


    }
}
