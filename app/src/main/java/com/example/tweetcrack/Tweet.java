package com.example.tweetcrack;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static com.example.tweetcrack.Hidden.ACCESS_TOKEN;
import static com.example.tweetcrack.Hidden.BEARER_TOKEN;
import static com.example.tweetcrack.Hidden.JTWITTER_OAUTH_KEY;


public class Tweet {
    RequestQueue queue;
    String rtnString;
    public Tweet(RequestQueue q) {
        queue = q;

    }

    public String GetTweet(String userName) {
        new Thread(() -> {
            String url = "https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=" + userName + "&include_rts=False&exclude_replies=True";

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                            rtnString = response;
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("error", error.toString());
                }
            }) {
//                @Override
//                protected Map<String, String> getParams() {
//                    Map<String, String> params = new HashMap<String, String>();
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
        return rtnString;
    }

}
