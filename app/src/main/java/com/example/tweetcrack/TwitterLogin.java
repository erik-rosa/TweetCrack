package com.example.tweetcrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import static com.example.tweetcrack.Hidden.ACCESS_TOKEN;
import static com.example.tweetcrack.Hidden.JTWITTER_OAUTH_KEY;
import static com.example.tweetcrack.Hidden.USER;

public class TwitterLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_login);
    }

    public void click2(View view) {
        EditText editText = findViewById(R.id.editTextTextPersonName);
        GlobalVariables.myRunnable.setPin(editText.getText().toString());
        new Thread(() -> {
            GlobalVariables.myRunnable.getCode();

            StringRequest sr = new StringRequest(Request.Method.POST, "https://api.twitter.com/oauth/access_token?", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    USER = response;
                    GlobalVariables.loggedIn = true;
                    Intent intent = new Intent(TwitterLogin.this, Home.class);
                    startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    TextView errorText = findViewById(R.id.errorMessage);
                    errorText.setText("Twitter did not authorize or incorrect pin");
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("oauth_consumer_key", JTWITTER_OAUTH_KEY);
                    params.put("oauth_token", ACCESS_TOKEN);
                    params.put("oauth_verifier", editText.getText().toString());

                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            GlobalVariables.queue.add(sr);
        }).start();



    }
}