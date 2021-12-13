package com.example.tweetcrack;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Random;


import static com.example.tweetcrack.Hidden.BEARER_TOKEN;
import static com.example.tweetcrack.Hidden.USER;

import androidx.core.widget.TextViewOnReceiveContentListener;

class Tuple {
    public Tuple(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String author;
    public String text;
}
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

//    public void GetOptionFromList(String listName, Button button) {
//
//        isRunning = true;
//        //GameOption result = new GameOption("null","null");
//        new Thread(() -> {
//            String url = "https://api.twitter.com/1.1/search/tweets.json?q=list:" + listName + "&count=50";
//
//            // Request a string response from the provided URL.
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            // Display the first 500 characters of the response string.
//
////                            rtnString = response;
//
//                            try {
//                                JSONObject obj = new JSONObject(response);
//                                JSONArray json = obj.getJSONArray("statuses"); //.substring(rtnString.indexOf("["), rtnString.lastIndexOf("]") + 1)
//                                int size = json.length();
//                                int tweetIndex = (int) (Math.random() * ((json.length() - 1) - 0 + 1) + 0);
//                                JSONObject user = (JSONObject) json.getJSONObject(tweetIndex).get("user");
//                                String author = (String) user.getString("screen_name");
//                                String name = (String) user.getString("name");
//                                author = name + "(@" + author +")";
//                                String text = (String) json.getJSONObject(tweetIndex).get("text");
//                                button.setText(author);
//                                button.setBackgroundColor(Color.parseColor("#FF6200EE"));
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            isRunning = false;
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.d("GetOptionFromListERROR", error.toString());
//                }
//            }) {
//
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("Authorization", "Bearer " + BEARER_TOKEN);
//                    return params;
//                }
//            };
//
//// Add the request to the RequestQueue.
//            queue.add(stringRequest);
//
//        }).start();
//
//    }
//
//
//    public void GetAnswerFromList(String listName, Button button, TextView textView) {
//
//        isRunning = true;
//        //GameOption result = new GameOption("null","null");
//        new Thread(() -> {
//            String url = "https://api.twitter.com/1.1/search/tweets.json?q=list:" + listName + "&count=50";
//
//            // Request a string response from the provided URL.
//            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            // Display the first 500 characters of the response string.
//
////                            rtnString = response;
//
//                            try {
//                                JSONObject obj = new JSONObject(response);
//                                JSONArray json = obj.getJSONArray("statuses"); //.substring(rtnString.indexOf("["), rtnString.lastIndexOf("]") + 1)
//                                int size = json.length();
//                                int tweetIndex = (int) (Math.random() * ((json.length() - 1) - 0 + 1) + 0);
//                                JSONObject user = (JSONObject) json.getJSONObject(tweetIndex).get("user");
//                                String author = (String) user.getString("screen_name");
//                                String name = (String) user.getString("name");
//                                author = name + "(@" + author +")";
//                                String text = (String) json.getJSONObject(tweetIndex).get("text");
//                                button.setText(author);
//                                button.setBackgroundColor(Color.parseColor("#0021A5"));
//                                textView.setText(text);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                            isRunning = false;
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.d("GetAnswerFromListERROR", error.toString());
//                }
//            }) {
//
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("Authorization", "Bearer " + BEARER_TOKEN);
//                    return params;
//                }
//            };
//
//// Add the request to the RequestQueue.
//            queue.add(stringRequest);
//
//        }).start();
//    }

    public void GetRoundSetUp(String listName, List<Button> options, TextView question,int correctIndex) {

        isRunning = true;
        //GameOption result = new GameOption("null","null");
        new Thread(() -> {
            //List<String> users = new ArrayList<>();
            ArrayList<String> users = new ArrayList<String>();
            ArrayList<String> tweets = new ArrayList<String>();
            String url = "https://api.twitter.com/1.1/search/tweets.json?q=list:" + listName + " -filter:retweets -filter:media -filter:replies&count=100";

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
                                while(users.size() != 4){
                                    int tweetIndex = (int) (Math.random() * ((json.length() - 1) - 0 + 1) + 0);
                                    JSONObject user = (JSONObject) json.getJSONObject(tweetIndex).get("user");
                                    String author = (String) user.getString("screen_name");
                                    String name = (String) user.getString("name");
                                    author = name + " (@" + author +")";
                                    String text = (String) json.getJSONObject(tweetIndex).get("text");
                                    if(!users.contains(author) && !text.contains("https://t.co/")){
                                        users.add(author);
                                        tweets.add(text);
                                    }
                                }
                                for (int i = 0; i < 4; i++) {
                                    if (i == correctIndex) {
                                        String aut = users.get(3-i);
                                        String text = tweets.get(3-i);
                                        options.get(i).setText(aut);
                                        question.setText(text);

                                    } else {
                                        String aut = users.get(3-i);
                                        options.get(i).setText(aut);
                                    }
                                }
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

    public void getFriendTweet(String listName, List<Button> options, TextView question,TextView correctIndexView) {

        isRunning = true;
        Random rand = new Random();
        //GameOption result = new GameOption("null","null");
        new Thread(() -> {
            //List<String> users = new ArrayList<>();
            Stack<String> users = new Stack<>();
            Stack<String> tweets = new Stack<>();
            String[] details = USER.split("&");
            String username = details[3].substring(details[3].indexOf("=") + 1);
            String getFriendsUrl = "https://api.twitter.com/1.1/friends/ids.json?screen_name=" + username;

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, getFriendsUrl,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject obj = new JSONObject(response);
                                JSONArray friendsJson = obj.getJSONArray("ids");

                                int randomFriend = rand.nextInt(friendsJson.length());

                                    String getTweetsUrl = "https://api.twitter.com/1.1/statuses/user_timeline.json?user_id=" + String.valueOf((friendsJson.getInt(randomFriend))) + "&count=10";
                                    StringRequest stringRequest = new StringRequest(Request.Method.GET, getTweetsUrl,
                                            new Response.Listener<String>() {
                                                @Override
                                                public void onResponse(String response) {
                                                    try {
                                                        JSONArray friendTweets = new JSONArray(response);

                                                        while(users.size() != 4){
                                                            int userIndex = rand.nextInt(friendsJson.length());
                                                            String author = String.valueOf(friendsJson.getInt(userIndex));
                                                            author = "@" + author;
                                                            if(!users.contains(author)) {
                                                                users.add(author);
                                                            }
                                                        }
                                                        int tweetIndex = rand.nextInt(friendTweets.length());
                                                        String tweetText = (String) friendTweets.getJSONObject(tweetIndex).get("text");
                                                        tweets.add(tweetText);
                                                        int correctIndex = rand.nextInt(4);
                                                        for (int i = 0; i < 4; i++) {
                                                            if (i == correctIndex) {
                                                                correctIndexView.setText(String.valueOf(i));
                                                                String aut = users.pop();
                                                                String text = tweets.pop();
                                                                options.get(i).setText(aut);
                                                                question.setText(text);

                                                            } else {
                                                                String aut = users.pop();
                                                                options.get(i).setText(aut);
                                                            }
                                                        }
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }

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
                                    queue.add(stringRequest);

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
}

