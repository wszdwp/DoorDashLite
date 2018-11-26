package com.codingpan.doordashlite.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.codingpan.doordashlite.R;
import com.codingpan.doordashlite.objects.Restaurant;
import com.codingpan.doordashlite.utilities.MyPreferences;
import com.codingpan.doordashlite.utilities.MyWebService;
import com.codingpan.doordashlite.utilities.Utility;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {
    private static final String LOG_TAG = "RestaurantListActivity";

    private RecyclerView recyclerView;
    private RestaurantListAdapter adapter;

    private String PROCESS_RESPONSE = "";
    private MyPreferences myPre;
    private MyRequestReceiver myReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        myPre = new MyPreferences(this);
        viewSetup();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PROCESS_RESPONSE = getResources().getString(R.string.serviceResponse);
        IntentFilter filter = new IntentFilter(PROCESS_RESPONSE);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        myReceiver = new MyRequestReceiver();
        registerReceiver(myReceiver, filter);

        sendGetRestaurantsRequest("37.422740", "-122.139956");
    }

    @Override
    protected void onPause() {
        unregisterReceiver(myReceiver);
        super.onPause();
    }

    private void viewSetup() {
        recyclerView = findViewById(R.id.restaurantList);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RestaurantListAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });
    }

    private void displayRestaurantList(@NonNull List<Restaurant> restaurantList) {
        adapter.setRestaurantList(restaurantList);
        adapter.notifyDataSetChanged();
    }

    private void sendGetRestaurantsRequest(String lat, String lng) {
        Intent intent = new Intent(this, MyWebService.class);
        intent.putExtra(MyWebService.REQUEST_TYPE, MyWebService.GET_RESTAURANTS);
        intent.putExtra(MyWebService.URL_STRING, myPre.getServiceUrl());
        intent.putExtra("lat", lat);
        intent.putExtra("lng", lng);

        startService(intent);
    }

    class MyRequestReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String requestType = intent.getStringExtra(MyWebService.REQUEST_TYPE);
            String responseString = intent.getStringExtra(MyWebService.RESPONSE_STRING);
            Log.v(LOG_TAG, requestType + " received response " + responseString);
            if (requestType.equalsIgnoreCase(MyWebService.GET_RESTAURANTS)) {
                try {
                    String jsonString = Utility.readJsonCache(RestaurantListActivity.this, responseString);
                    Gson gson = new Gson();
                    Type type = new TypeToken<ArrayList<Restaurant>>(){}.getType();
                    List<Restaurant> restaurantList = gson.fromJson(jsonString, type);
                    displayRestaurantList(restaurantList);

                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
