package com.codingpan.doordashlite.utilities;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.codingpan.doordashlite.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class MyWebService extends IntentService {
    public static final String LOG_TAG = "MyWebService";
    public static final String REQUEST_TYPE = "requestType";
    public static final String RESPONSE_STRING = "response";
    public static final String URL_STRING = "requestURL";

    private String PROCESS_RESPONSE = "";
    private static final int REGISTRATION_TIMEOUT = 30 * 1000;
    private static final int REQUEST_TIMEOUT = 60 * 1000;
    private String URLString = "";

    public static final String GET_RESTAURANTS = "restaurant";


    public MyWebService() {
        super("MyWebService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String requestType = intent.getStringExtra(REQUEST_TYPE);
        String responseString = "";
        if (intent != null) {
            responseString = handleWebRequest(intent);
        }
        Intent broadcastIntent = new Intent();
        PROCESS_RESPONSE = getBaseContext().getResources().getString(R.string.serviceResponse);
        broadcastIntent.setAction(PROCESS_RESPONSE);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(REQUEST_TYPE, requestType);
        broadcastIntent.putExtra(RESPONSE_STRING, responseString);
        sendBroadcast(broadcastIntent);
    }

    private String handleWebRequest(Intent intent) {
        String requestType = intent.getStringExtra(REQUEST_TYPE);
        String responseString =  "";
        boolean error = false;

        URLString = getRequestURL(intent);
        Log.d(LOG_TAG, "The request URL is " + URLString);

        URL url = null;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(URLString);
            if (url != null) {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setConnectTimeout(REGISTRATION_TIMEOUT);
                urlConnection.setReadTimeout(REQUEST_TIMEOUT);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(false);

                urlConnection.connect();
                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream response = urlConnection.getInputStream();
                    responseString = processWebResponse(requestType, response);
                }
                else {
                    error = true;
                    throw new IOException(urlConnection.getResponseMessage());
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        if (error) {
            responseString = "error";
        }
        return  responseString;
    }

    private String getRequestURL(Intent intent) {
        String requestType = intent.getStringExtra(REQUEST_TYPE);
        String myURL = intent.getStringExtra(URL_STRING) + requestType + "/?";
        if (requestType.equals(GET_RESTAURANTS)) {
            String lat = intent.getStringExtra("lat").trim();
            String lng = intent.getStringExtra("lng").trim();
            myURL += "lat=" + lat + "&lng=" + lng;
        }
        return myURL;
    }

    private String processWebResponse(String requestType, InputStream response) throws IOException {
        String responseString = "";
        if (requestType.equalsIgnoreCase(GET_RESTAURANTS)) {
            responseString = "myJsonCache";
            Utility.cacheJson(getBaseContext(), responseString, response);
        }
        else {
            Log.d(LOG_TAG, "unknown request type " + requestType);
        }

        return responseString;
    }

}

