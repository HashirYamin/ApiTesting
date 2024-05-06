package com.example.callapitesting;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv ;
        tv= findViewById(R.id.txt);

        String url = "https://jsonplaceholder.typicode.com/todos/1";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int userId = response.getInt("userId"); // Use "userId" instead of "userid"
                    int id = response.getInt("id");
                    String title = response.getString("title"); // Correct the key to "title"
                    boolean completed = response.getBoolean("completed");

                    tv.setText("UserId: " + userId + "\nId: " + id + "\nTitle: " + title + "\nCompleted: " + completed);
                } catch (JSONException e) {
                    e.printStackTrace();
                    tv.setText("Error parsing JSON");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setText("Error");
            }
        });

        RequestQueue  requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}