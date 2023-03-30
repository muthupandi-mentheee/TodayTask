package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.task.Adapter.Adapter;
import com.example.task.Model.ApiPostModel;
import com.example.task.Model.AuthendicationResponse;
import com.example.task.Model.Result;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rey)
    RecyclerView rey;
    @BindView(R.id.BT)
    FloatingActionButton click;
    Adapter adapter;

    private RequestQueue queue;

    private AuthendicationResponse responceModel;
    ArrayList<Result> value = new ArrayList<>();
    ArrayList<Result> value2 = new ArrayList<>();
    Result results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        queue = Volley.newRequestQueue(this);
        API_VALUE();
        if (value.size() > 0) {
            Adaptervalue(value);
        }
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                API_VALUE();
                if (value.size() > 0) {
                    Adaptervalue(value);
                }
            }
        });
//        apiRequest();
//        value.add(new Result("mani","mani@gmail.com"));

    }

    private void API_VALUE() {
        String url = "https://jsonplaceholder.typicode.com/users";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //printline
                Log.d("VolleyRequestHandler",response);
                //class to Arraylist convert
                Type listType = new TypeToken<ArrayList<AuthendicationResponse>>() {
                }.getType();
                //json to convert model class
                List<AuthendicationResponse> yourClassList = new Gson().fromJson(response, listType);
                String city, street, suite, zipcode;
                if (yourClassList != null) {

                    for (int i = 0; i < yourClassList.size(); i++) {
                        city = yourClassList.get(i).getAddress().getCity();
                        street = yourClassList.get(i).getAddress().getStreet();
                        suite = yourClassList.get(i).getAddress().getSuite();
                        zipcode = yourClassList.get(i).getAddress().getZipcode();
                        value.add(new Result(yourClassList.get(i).getUsername(), yourClassList.get(i).getEmail()));
                        value2.add(new Result(yourClassList.get(i).getUsername(), yourClassList.get(i).getEmail(), city + street + suite + zipcode, yourClassList.get(i).getPhone()));
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Api not Responce", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    void Adaptervalue(ArrayList value) {
        adapter = new Adapter(value2, new Adapter.UserAdapterListener() {
            @Override
            public void onItemlick(Result result) {
                Intent i = new Intent(getApplicationContext(), UserList.class);
                i.putExtra("valuess", result);
                startActivity(i);

            }
        });
        rey.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        rey.setAdapter(adapter);
    }
}