package com.example.fang.walmartproject.splashScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.homePage.HomePageActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    sleep(5000);

                    Intent intent = new Intent(SplashActivity.this, HomePageActivity.class);
                    startActivity(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        };
        thread.start();

    }
}
