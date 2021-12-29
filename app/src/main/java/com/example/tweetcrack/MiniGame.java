package com.example.tweetcrack;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.appcompat.app.AppCompatActivity;

public class MiniGame extends AppCompatActivity implements SensorEventListener, SurfaceHolder.Callback {


    Paint red_fill;
    Paint white_stroke;
    Paint white_text;
    Bitmap golfball;
    Bitmap golf2;
    Bitmap holebad;
    boolean ran= true;

    SurfaceHolder holder = null;

    Animator my_animator;
    private SensorEventListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game);

        red_fill = new Paint();
        red_fill.setColor(Color.RED);
        red_fill.setStyle(Paint.Style.FILL);

        white_stroke = new Paint();
        white_stroke.setColor(Color.BLACK);
        white_stroke.setStyle(Paint.Style.STROKE);
        white_stroke.setStrokeWidth(10);

        white_text = new Paint();
        white_text.setColor(Color.BLACK);
        white_text.setTextSize(100);

        golfball = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.falling_tweets_cage),
                200, 200, false);
        golf2 = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.thumbs_up),
                200, 200, false);
        holebad = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.block),
                200, 200, false);


        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer != null) {
            manager.registerListener((SensorEventListener) this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL,SensorManager.SENSOR_DELAY_UI);
        }

        SurfaceView my_surface = findViewById(R.id.surfaceView);
        my_surface.getHolder().addCallback((SurfaceHolder.Callback) this);
        my_surface.setZOrderOnTop(true);
        SurfaceHolder sfhTrackHolder = my_surface.getHolder();
        sfhTrackHolder.setFormat(PixelFormat.TRANSPARENT);

        Object activity;
        my_animator = new Animator(this);
        my_animator.start();

    }


    public void onSensorChanged(SensorEvent sensorEvent) {
        acc_x = sensorEvent.values[0];
        acc_y = sensorEvent.values[1];
    }


    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    int score = 0;

    String message = "Collect Ten Likes!";
    float acc_x = 0;
    float acc_y = 0;
    int golfball_x_position = 0;
    int golfball_y_position = 1600;

    int golf2_x_position = 500;
    int golf2_y_position = 600;
    int holebad_x_position = 200;
    int holebad_y_position = 800;

    int golf2Speed = (int) (Math.random() * (20 - 15)) + 15;
    int holebadSpeed = (int) (Math.random() * (30 - 25)) + 15;

    public void update(int width, int height) {

        golf2_y_position += golf2Speed;
        holebad_y_position += holebadSpeed;

        golfball_x_position -= acc_x * 2;
        //golfball_y_position+=acc_y*2;

        if (golfball_x_position < 0) golfball_x_position = 0;
        else if (golfball_x_position > width - 200) golfball_x_position = width - 200;

        //if(golfball_y_position<0)golfball_y_position=0;
        //else if(golfball_y_position>height-200)golfball_y_position=height-200


        if (golf2_y_position < 0) golf2_y_position = 0;
        else if (golf2_y_position > height - 200) {
            golf2_y_position = 0;
            golf2_x_position = (int) (Math.random() * (width - 200 - 1)) + 1;
        }


        if (holebad_y_position < 0) holebad_y_position = 0;
        else if (holebad_y_position > height - 200) {
            holebad_y_position = 0;
            holebad_x_position = (int) (Math.random() * (width - 200 - 1)) + 1;
        }


        if (Math.abs(golfball_x_position - golf2_x_position) < 200 && Math.abs(golfball_y_position - golf2_y_position) < 200) {
            score += 1;
            message = ("Score: " + score);
            golf2Speed = (int) (Math.random() * (20 - 15)) + 15;
            golf2_y_position = 0;
            golf2_x_position = (int) (Math.random() * (width - 200 - 1)) + 1;
        }
        if (Math.abs(golfball_x_position - holebad_x_position) < 200 && Math.abs(golfball_y_position - holebad_y_position) < 200) {
            score -= 1;
            message = ("Score:" + score);
            holebadSpeed = (int) (Math.random() * (30 - 25)) + 15;
            holebad_y_position = 0;
            holebad_x_position = (int) (Math.random() * (width - 200 - 1)) + 1;
        }

        if (score > 4) {

            if (ran)
            {finish();
                Intent my_intent = new Intent (getBaseContext(),youWin.class);
                startActivity(my_intent);}
            ran= false;
        }
    }

    public void draw() {

        if (holder == null) return;


        Canvas c = holder.lockCanvas();

        update(c.getWidth(), c.getHeight());

        //c.drawColor(Color.rgb(255,212,124));
        c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

        //c.drawRect(0,0, 200,200,red_fill);

        //c.drawCircle(c.getWidth()/2,c.getHeight()/2,100,white_stroke);


        c.drawBitmap(golf2, golf2_x_position, golf2_y_position, null);

        c.drawBitmap(golfball, golfball_x_position, golfball_y_position, null);

        c.drawBitmap(holebad, holebad_x_position, holebad_y_position, null);

        c.drawText(message, 20, c.getHeight() - 1800, white_text);

        holder.unlockCanvasAndPost(c);
    }



    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        Log.d("Example", "Surface is created");
        holder = surfaceHolder;

        draw();
    }


    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        Log.d("Example", "Surface changed");
        holder = surfaceHolder;
    }


    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        holder = null;
    }

    @Override
    public void onDestroy() {

        my_animator.finish();
        SensorManager manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        manager.unregisterListener((SensorEventListener) this, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));

        super.onDestroy();
    }
}