package com.example.tweetcrack;

public class Animator extends Thread{

    MiniGame surfaceActivity;
    boolean is_running=false;

    public Animator(MiniGame activity){
        surfaceActivity=activity;
    }

    public void run(){
        is_running=true;

        while(is_running){

            surfaceActivity.draw();

            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void finish(){
        is_running=false;
    }
}
