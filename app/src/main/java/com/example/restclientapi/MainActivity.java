package com.example.restclientapi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ArrayList<UserData> userDataArrayList;
    private DataAdapter dataAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        getData();
    }

    public void getData() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://jsonplaceholder.typicode.com/comments", null, new callAPI());
    }

    public class callAPI extends AsyncHttpResponseHandler {
        ProgressDialog progressDialog;

        public void onStart() {
            super.onStart();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        public void onFinish() {
            Log.e(TAG, "onFinish: ");
            super.onFinish();
        }

        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            progressDialog.dismiss();
            try {
                Gson gson = new Gson();

                Type userData = new TypeToken<List<UserData>>() {
                }.getType();
                String response = new String(responseBody);
                userDataArrayList = new ArrayList<>();
                userDataArrayList = gson.fromJson(response, userData);

                setAdapter();
                Log.e(TAG, "onSuccess: ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Log.e(TAG, "onFailure: " + error);
            progressDialog.dismiss();
        }
    }

    private void setAdapter() {
        dataAdapter = new DataAdapter(MainActivity.this, userDataArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, 1, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(dataAdapter);

        /*
          recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(dataAdapter);
      */

        dataAdapter.setItemClickCallback(new OnClickCallback<ArrayList<UserData>, Integer, String, Activity>() {
            @Override
            public void onClickCallBack(ArrayList<UserData> data, Integer position, String etc, Activity activity) {
                Toast.makeText(activity, "" + data.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
