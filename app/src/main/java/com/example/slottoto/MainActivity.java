package com.example.slottoto;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView kiri;
    private ImageView tengah;
    private ImageView kanan;
    private Thread threadKiri,threadTengah, threadKanan;
    private Handler handler;
    private boolean start = false;

    Random random = new Random();

    int[] imageArray = {
            R.drawable.slot_1,
            R.drawable.slot_2,
            R.drawable.slot_3,
            R.drawable.slot_4,
            R.drawable.slot_5,
            R.drawable.slot_6,
            R.drawable.slot_7,
            R.drawable.slot_8,
            R.drawable.slot_9,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btStartStop).setOnClickListener(this);
        createThread();
        this.kiri = (ImageView) findViewById(R.id.kiri);
        this.tengah = (ImageView) findViewById(R.id.tengah);
        this.kanan = (ImageView) findViewById(R.id.kanan);
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void onClick(View view) {
        if (this.threadKiri.isAlive()){
            this.threadKiri.interrupt();
        } else if (this.threadTengah.isAlive()) {
            this.threadTengah.interrupt();
        } else if (this.threadKanan.isAlive()){
            this.threadKanan.interrupt();
        } else {
            createThread();
            this.threadKiri.start();
            this.threadTengah.start();
            this.threadKanan.start();
            start = true;
        }
    }

    private void createThread() {
        this.threadKiri = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (start) {
                        final int acakKirim = random.nextInt(imageArray.length);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                kiri.setImageResource(imageArray[random.nextInt(imageArray.length)]);
                            }
                        });
                        Thread.sleep(60);
                    }
                } catch (InterruptedException e) {
                    // Thread diinterupsi, selesai
                }
            }
        });

        this.threadTengah = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (start) {
                        final int acakKirim = random.nextInt(imageArray.length);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tengah.setImageResource(imageArray[random.nextInt(imageArray.length)]);
                            }
                        });
                        Thread.sleep(60);
                    }
                } catch (InterruptedException e) {
                    // Thread diinterupsi, selesai
                }
            }
        });

        this.threadKanan = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (start) {
                        final int acakKirim = random.nextInt(imageArray.length);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                kanan.setImageResource(imageArray[random.nextInt(imageArray.length)]);
                            }
                        });
                        Thread.sleep(60);
                    }
                } catch (InterruptedException e) {
                    // Thread diinterupsi, selesai
                }
            }
        });
    }
}