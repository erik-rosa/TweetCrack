package com.example.tweetcrack;

import android.graphics.Color;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.example.tweetcrack.Hidden.BEARER_TOKEN;

import androidx.core.widget.TextViewOnReceiveContentListener;


public class Tweet {
    RequestQueue queue;
    String rtnString;
    Boolean isRunning = false;

    public Tweet(RequestQueue q) {
        queue = q;

    }

    public String GetTweet(String userName, TextView textView) {
        isRunning = true;
        new Thread(() -> {
            String url = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=" + userName + "&include_rts=False&exclude_replies=True";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.

//                            rtnString = response;
                            JSONArray json;
                            try {
                                json = new JSONArray(response); //.substring(rtnString.indexOf("["), rtnString.lastIndexOf("]") + 1)
                                int size = json.length();
                                //Log.d("TWEET", String.valueOf(json));
                                rtnString = (String) json.getJSONObject((int) (Math.random() * ((json.length() - 1) - 0 + 1) + 0)).get("text");
                                textView.setText(rtnString);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            isRunning = false;
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error", error.toString());
                }
            }) {
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<String, St ring>();
//                    params.put("screen_name", userName);
//                    params.put("include_rts", "False");
//                    params.put("exclude_replies", "True");
//
//                    return params;
//                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "Bearer " + BEARER_TOKEN);
                    return params;
                }
            };

// Add the request to the RequestQueue.
            queue.add(stringRequest);

        }).start();
//
        return rtnString;
    }

    public void GetOptionFromList(String listName, Button button) {

        isRunning = true;
        //GameOption result = new GameOption("null","null");
        new Thread(() -> {
            String url = "https://api.twitter.com/1.1/search/tweets.json?q=list:" + listName;

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.

//                            rtnString = response;

                            try {
                                JSONObject obj = new JSONObject(response);
                                JSONArray json = obj.getJSONArray("statuses"); //.substring(rtnString.indexOf("["), rtnString.lastIndexOf("]") + 1)
                                int size = json.length();
                                int tweetIndex = (int) (Math.random() * ((json.length() - 1) - 0 + 1) + 0);
                                JSONObject user = (JSONObject) json.getJSONObject(tweetIndex).get("user");
                                String author = (String) user.getString("screen_name");
                                author = "@" + author;
                                String text = (String) json.getJSONObject(tweetIndex).get("text");
                                button.setText(author);
                                button.setBackgroundColor(Color.parseColor("#FF6200EE"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            isRunning = false;
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("GetOptionFromListERROR", error.toString());
                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "Bearer " + BEARER_TOKEN);
                    return params;
                }
            };

// Add the request to the RequestQueue.
            queue.add(stringRequest);

        }).start();

    }


    public void GetAnswerFromList(String listName, Button button, TextView textView) {

        isRunning = true;
        //GameOption result = new GameOption("null","null");
        new Thread(() -> {
            String url = "https://api.twitter.com/1.1/search/tweets.json?q=list:" + listName;

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.

//                            rtnString = response;

                            try {
                                JSONObject obj = new JSONObject(response);
                                JSONArray json = obj.getJSONArray("statuses"); //.substring(rtnString.indexOf("["), rtnString.lastIndexOf("]") + 1)
                                int size = json.length();
                                int tweetIndex = (int) (Math.random() * ((json.length() - 1) - 0 + 1) + 0);
                                JSONObject user = (JSONObject) json.getJSONObject(tweetIndex).get("user");
                                String author = (String) user.getString("screen_name");
                                author = "@" + author;
                                String text = (String) json.getJSONObject(tweetIndex).get("text");
                                button.setText(author);
                                button.setBackgroundColor(Color.parseColor("#0021A5"));
                                textView.setText(text);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            isRunning = false;
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("GetAnswerFromListERROR", error.toString());
                }
            }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Authorization", "Bearer " + BEARER_TOKEN);
                    return params;
                }
            };

// Add the request to the RequestQueue.
            queue.add(stringRequest);

        }).start();
    }


}

