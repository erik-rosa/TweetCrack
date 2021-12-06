package com.example.tweetcrack;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import static com.example.tweetcrack.Hidden.ACCESS_TOKEN;
import static com.example.tweetcrack.Hidden.JTWITTER_OAUTH_KEY;
import static com.example.tweetcrack.Hidden.JTWITTER_OAUTH_SECRET;
import static com.example.tweetcrack.Hidden.TOKEN_SECRET;

public class MyRunnable implements Runnable {

    OAuthConsumer consumer;
    OAuthProvider provider;

    private String pin = "-1";
    private int var;
    private volatile String authUrl;

    public String getString() {
        return authUrl;
    }

    public void setPin(String p) {
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

    public void getCode() {
        String pinCode = pin;// ... you have to ask this from the user, or obtain it
        // from the callback if you didn't do an out of band request

        // user must have granted authorization at this point
        ACCESS_TOKEN = consumer.getToken();
        TOKEN_SECRET = consumer.getTokenSecret();
//        try {
//            provider.retrieveAccessToken(consumer, pinCode);
//        } catch (OAuthMessageSignerException e) {
//            e.printStackTrace();
//        } catch (OAuthNotAuthorizedException e) {
//            e.printStackTrace();
//        } catch (OAuthExpectationFailedException e) {
//            e.printStackTrace();
//        } catch (OAuthCommunicationException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//
//
//        oauth.signpost.http.HttpParameters hi  = consumer.getRequestParameters();
//        String test = consumer.getConsumerKey();
//        ACCESS_TOKEN = consumer.getToken();
//        TOKEN_SECRET = consumer.getTokenSecret();
//        Log.d("tes", ACCESS_TOKEN);

    }
}
