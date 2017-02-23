package com.answerbluetoohphone.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.answerbluetoohphone.Base.BaseActivity;
import com.answerbluetoohphone.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class MainActivity extends BaseActivity {

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = getSupportFragmentManager().findFragmentById(R.id.MainActivity_FrameLayout_MainContent);
        fragment = new SplashFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.MainActivity_FrameLayout_MainContent, fragment);
        ft.commit();
    }

}
