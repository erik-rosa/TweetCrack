package com.example.tweetcrack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;

//import org.apache.http.params.HttpParams;
//import org.json.JSONObject;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
//import winterwell.jtwitter.User;

import static com.example.tweetcrack.Hidden.JTWITTER_OAUTH_KEY;
import static com.example.tweetcrack.Hidden.JTWITTER_OAUTH_SECRET;
import static com.example.tweetcrack.Hidden.ACCESS_TOKEN;
import static com.example.tweetcrack.Hidden.TOKEN_SECRET;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class GameScreen extends AppCompatActivity {
    //    MyRunnableGetQuery myRunnableQuery = new MyRunnableGetQuery();
    Tweet h;
    MyRunnable myRunnable = new MyRunnable(10);
    List<Button> options = new ArrayList<Button>();
    TextView question, score;
    Button opt1, opt2, opt3, opt4;
    String category = "";
    int gameScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        question = (TextView) findViewById(R.id.question);
        score = findViewById(R.id.score);
        score.setText(String.valueOf(gameScore));
        opt1 = (Button) findViewById(R.id.opt1);
        opt2 = (Button) findViewById(R.id.opt2);
        opt3 = (Button) findViewById(R.id.opt3);
        opt4 = (Button) findViewById(R.id.opt4);
        options.add(opt1);
        options.add(opt2);
        options.add(opt3);
        options.add(opt4);
        Bundle extras = getIntent().getExtras();
        if (extras.get("category") != null) {
            category = extras.getString("category");
        }

        h = new Tweet(GlobalVariables.queue);
        nextRound();
        Thread t = new Thread(myRunnable);
        t.start();

    }

    public void incorrectAnswer() {
        gameScore -= 1;
        score.setText(String.valueOf(gameScore));
        nextRound();
    }

    public void correctAnswer() {
        gameScore += 1;
        score.setText(String.valueOf(gameScore));
        nextRound();
    }

    public void nextRound() { // gets new tweets and rearranges the order of the correct and incorrect buttons
        int correctIndex = (int) (Math.random() * ((3) + 1));
        for (int i = 0; i < 4; i++) {
            if (i == correctIndex) {
                h.GetAnswerFromList(category, options.get(i), question);
                options.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        correctAnswer();
                    }
                });
            } else {
                h.GetOptionFromList(category, options.get(i));
                options.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        incorrectAnswer();
                    }
                });
            }
        }
    }
}

class MyRunnableGetQuery implements Runnable {
    OAuthConsumer consumer;
    OAuthProvider provider;

    public void run() {
        consumer = new DefaultOAuthConsumer(
                // the consumer key of this app (replace this with yours)
                JTWITTER_OAUTH_KEY,
                // the consumer secret of this app (replace this with yours)
                JTWITTER_OAUTH_SECRET);


//        provider = new DefaultOAuthProvider(
//                "http://api.twitter.com/oauth/request_token",
//                "http://api.twitter.com/oauth/access_token",
//                "http://api.twitter.com/oauth/authorize");
        consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

        // create a request that requires authentication
        URL url = null;
        try {
            url = new URL("https://api.twitter.com/2/users/:id/followers");
//            url = new URL("https://api.twitter.com/statuses/mentions.xml");
//            url = new URL("https://api.twitter.com/1.1/users/show.json?user_id=1892261251");
//            url = new URL("https://api.twitter.com/1.1/friends/ids.json?user_id=1892261251");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection request = null;
        try {
            request = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // sign the request
        try {
            consumer.sign(request);
        } catch (OAuthMessageSignerException e) {
            e.printStackTrace();
        } catch (OAuthExpectationFailedException e) {
            e.printStackTrace();
        } catch (OAuthCommunicationException e) {
            e.printStackTrace();
        }
        request.setRequestProperty("id", "1892261251");
        // send the request
        try {
            request.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // response status should be 200 OK
        try {
            int statusCode = request.getResponseCode();
//            InputStream inputStream = request.getInputStream();
//            String inputStreamString = inputStream.toString();
            BufferedReader br = null;
//            br = new BufferedReader(new InputStreamReader((request.getInputStream())));

            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String strCurrentLine;
            List<String> jsonlist = new ArrayList<>();
            while ((strCurrentLine = br.readLine()) != null) {
                jsonlist.add(strCurrentLine);
            }

            String response = request.getResponseMessage();
            String body = request.getContent().toString();
            Map<String, List<String>> temp = request.getHeaderFields();
            InputStream d = request.getInputStream();
            Object h = request.getContent();
            String l = request.toString();
//            d.BaseStream.Seek(0, SeekOrigin.Begin);
//            request.cont
            Log.d("d", "H");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}