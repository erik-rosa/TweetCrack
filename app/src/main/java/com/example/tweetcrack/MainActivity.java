package com.example.tweetcrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.net.Uri;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

class MyRunnable implements Runnable {

    OAuthConsumer consumer;
    OAuthProvider provider;
    private String pin = "-1";
    private int var;
    private volatile String authUrl;
    public String getString(){
        return authUrl;
    }
    public void setPin(String p){
        pin = p;
    }
    public MyRunnable(int var) {
        this.var = var;
    }

    public void run() {
        consumer = new DefaultOAuthConsumer(
                // the consumer key of this app (replace this with yours)
                JTWITTER_OAUTH_KEY,
                // the consumer secret of this app (replace this with yours)
                JTWITTER_OAUTH_SECRET);

        provider = new DefaultOAuthProvider(
                "https://api.twitter.com/oauth/request_token",
                "https://api.twitter.com/oauth/access_token",
                "https://api.twitter.com/oauth/authorize");

        /****************************************************
         * The following steps should only be performed ONCE
         ***************************************************/

        // we do not support callbacks, thus pass OOB
        try {
            authUrl = provider.retrieveRequestToken(consumer, OAuth.OUT_OF_BAND);
            Log.d("h", "H");

        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthNotAuthorizedException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }

    }
    public void getCode(){
        String pinCode = pin;// ... you have to ask this from the user, or obtain it
        // from the callback if you didn't do an out of band request

        // user must have granted authorization at this point
        try {
            provider.retrieveAccessToken(consumer, pinCode);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthNotAuthorizedException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }

        ACCESS_TOKEN = consumer.getToken();
        TOKEN_SECRET = consumer.getTokenSecret();
        Log.d("tes", ACCESS_TOKEN);

    }
}
public class MainActivity extends AppCompatActivity {

    MyRunnable myRunnable = new MyRunnable(10);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.text);
// ...


        Thread t = new Thread(myRunnable);
        t.start();
    }
    public void quickPlayClick(View view){
        Intent my_intent = new Intent(getBaseContext(),Home.class);
        startActivity(my_intent);
    }
    public void twitterLoginClick(View view){
        Button button = findViewById(R.id.twitterLogin);
        button.setVisibility(View.GONE);

        Button button2 = findViewById(R.id.QuickPlay);
        button2.setVisibility(View.GONE);

        Button button3 = findViewById(R.id.submitButton);
        button3.setVisibility(View.VISIBLE);

        EditText editText = findViewById(R.id.editTextTextPersonName);
        editText.setVisibility(View.VISIBLE);

        Uri uri = Uri.parse(myRunnable.getString()); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        startActivity(intent);
    }
    public void click2(View view){
        EditText editText = findViewById(R.id.editTextTextPersonName);
        myRunnable.setPin(editText.getText().toString());
        myRunnable.getCode();
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}