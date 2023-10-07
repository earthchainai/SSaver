package com.kessi.wawbstatussaver;

import static com.kessi.wawbstatussaver.util.Utils.permissions;
import static com.kessi.wawbstatussaver.util.Utils.permissions13;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

import com.kessi.wawbstatussaver.intro.IntroActivity;
import com.kessi.wawbstatussaver.util.SharedPrefs;
import com.kessi.wawbstatussaver.util.Utils;

public class SplashActivity extends AppCompatActivity {


//    private int REQUEST_CODE_NOTIFICATION_LISTENER = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AppCompatDelegate.setDefaultNightMode(SharedPrefs.getAppNightDayMode(this));

        Utils.setLanguage(SplashActivity.this, SharedPrefs.getLang(SplashActivity.this));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        {
            if (!Utils.hasPermissions(this, permissions13) && Utils.isNotificationServiceRunning(this)){
                gotoNext();
            }else {
                gotoIntro();
            }
        }
        else
        {
            if (!Utils.hasPermissions(this, permissions) && Utils.isNotificationServiceRunning(this)){
                gotoNext();
            }else {
                gotoIntro();
            }
        }




    }

    void gotoNext(){
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        }, 600);

    }

    void gotoIntro(){
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, IntroActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        }, 600);

    }

    @Override
    public void onBackPressed() { }


}