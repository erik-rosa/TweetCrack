package com.example.tweetcrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import static com.example.tweetcrack.Hidden.JTWITTER_OAUTH_KEY;
import static com.example.tweetcrack.Hidden.JTWITTER_OAUTH_SECRET;
import static com.example.tweetcrack.Hidden.ACCESS_TOKEN;
import static com.example.tweetcrack.Hidden.TOKEN_SECRET;
import static com.example.tweetcrack.Hidden.USER;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GlobalVariables.myRunnable = new MyRunnable(10);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GlobalVariables.friends = null;
        SharedPreferences mPrefs = getSharedPreferences("GameState", 0);
        GlobalVariables.gameState =  new Game(mPrefs);


        GlobalVariables.loggedIn = false;
        USER = "";

        GlobalVariables.queue = Volley.newRequestQueue(MainActivity.this);
        final TextView textView = (TextView) findViewById(R.id.textView);

        Tweet h = new Tweet(GlobalVariables.queue);
        //String temp = h.GetTweet("twitter", textView);
//        try {
//            Thread.sleep(10000);
//        } catch (Exception e) {}
//        try {
//            wait(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        textView.setText(temp);

// ...


        Thread t = new Thread(GlobalVariables.myRunnable);
        t.start();
    }

    protected void onResume () {
        super.onResume();
        GlobalVariables.loggedIn = false;
        USER = "";
    }
    public void quickPlayClick(View view) {
        Intent my_intent = new Intent(getBaseContext(), Home.class);
        startActivity(my_intent);
    }

    public void twitterLoginClick(View view) {
//        Button button = findViewById(R.id.twitterLogin);
//        button.setVisibility(View.GONE);
//
//        Button button2 = findViewById(R.id.QuickPlay);
//        button2.setVisibility(View.GONE);
//
//        Button button3 = findViewById(R.id.submitButton);
//        button3.setVisibility(View.VISIBLE);
//
//        EditText editText = findViewById(R.id.editTextTextPersonName);
//        editText.setVisibility(View.VISIBLE);

        Intent intent = new Intent(MainActivity.this, TwitterLogin.class);
        startActivity(intent);

        Uri uri = Uri.parse(GlobalVariables.myRunnable.getString()); // missing 'http://' will cause crashed
        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent2);
    }


}