package com.example.androidapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class ShowData extends Fragment {

    protected TextView selection;

    private final static String TAG = "ContentFragment";

    public ShowData() {
        // Required empty public constructor
    }

    public static ShowData newInstance(String param1, String param2) {
        Bundle args = new Bundle();
        ShowData fragment = new ShowData();
        fragment.setArguments(args);
        Log.d(TAG, "ShowData constructor");
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate Show");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_data, container, false);
        selection = v.findViewById(R.id.selection);
        return v;
    }

    public void setSelectedItems(String chosen_author, String chosen_publication_year) {
        selection.setText(getString(R.string.choice_message, chosen_author, chosen_publication_year));
    }
}