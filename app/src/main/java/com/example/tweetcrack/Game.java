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
    public void completeGame(int score, int totNumberQuestions, String category){
        int prevHighScore = mPrefs.getInt("highScore" + category, 0);
        if(prevHighScore > totNumberQuestions || prevHighScore == 0)
            mEditor.putInt("highScore" + category, totNumberQuestions).commit();
        mEditor.putInt("gamesPlayed" + category, mPrefs.getInt("gamesPlayed" + category, 0) +1).commit();
    }
}
