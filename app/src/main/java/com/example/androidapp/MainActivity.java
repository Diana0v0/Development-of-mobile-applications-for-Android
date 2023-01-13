package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity  implements EnterData.OnFragmentSendDataListener {

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "OnCreateBegin");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnterData enterData = new EnterData();
        getSupportFragmentManager().beginTransaction().add(enterData, TAG).commit();
        Log.d(TAG, "OnCreateBegin_enterData");
        ShowData showDataFragment  = new ShowData();
        getSupportFragmentManager().beginTransaction().add(showDataFragment, TAG).commit();
        Log.d(TAG, "OnCreateBegin_showData");
    }

    @Override
    public void onSendData(String authorData, String publicationYearData) {
        ShowData showDataFragment = (ShowData) getSupportFragmentManager().findFragmentById(R.id.showDataFragment);
        if (showDataFragment != null)
            showDataFragment.setSelectedItems(authorData, publicationYearData);
    }
}