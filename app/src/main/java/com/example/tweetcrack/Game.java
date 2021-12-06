package com.example.tweetcrack;

import android.content.SharedPreferences;
import android.view.View;

import com.android.volley.RequestQueue;

public class Game {
    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;
    public Game(SharedPreferences m) {
        mPrefs = m;
        mEditor = mPrefs.edit();

    }
    public int getHighScore(String category){
        return mPrefs.getInt("highScore" + category, 0);
    }
    public int getTotalGamesPlayed(String category){
        return mPrefs.getInt("gamesPlayed" + category, 0);
    }
    public void completeGame(int score, String category){
        int prevHighScore = mPrefs.getInt("highScore" + category, 0);
        if(prevHighScore < score)
            mEditor.putInt("highScore" + category, score).commit();
        mEditor.putInt("gamesPlayed" + category, mPrefs.getInt("gamesPlayed" + category, 0)).commit();
    }
}
