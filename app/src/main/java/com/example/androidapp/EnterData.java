package com.example.androidapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.annotation.NonNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import android.util.Log;

public class EnterData extends Fragment {

    protected RadioGroup radio_buttons;
    protected Button button;
    protected Spinner spinner;
    protected String chosen_author = "";
    protected String chosen_publication_year = "";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private final static String TAG = "ContentFragment";

    public EnterData() {
        // Required empty public constructor
    }

    public static EnterData newInstance(String param1, String param2) {
        EnterData fragment = new EnterData();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);

        Log.d(TAG, "EnterData constructor");

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Log.d(TAG, "onCreate");
    }


    interface OnFragmentSendDataListener {
        void onSendData(String authorData, String publicationYearData);
    }

    private OnFragmentSendDataListener fragmentSendDataListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            fragmentSendDataListener = (OnFragmentSendDataListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " interface OnFragmentInteractionListener");
        }
        Log.d(TAG, "onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enter_data, container, false);
    }

    public void onViewCreated (View view,  Bundle savedInstanceState)
    {
        Log.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);

        radio_buttons = view.findViewById(R.id.radios);
        button = view.findViewById(R.id.button_ok);
        spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.spinner_items));
        spinner.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(@NonNull AdapterView<?> parent, View view, int position, long id) {
                chosen_author = (String) parent.getItemAtPosition(position);
                Log.d(TAG, "onItemSelected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "onNothingSelected");
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);

        radio_buttons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup arg0, int id) {
                RadioButton selected_button = (RadioButton) view.findViewById(radio_buttons.getCheckedRadioButtonId());
                chosen_publication_year = selected_button.getText().toString();
                Log.d(TAG, "onCheckedChanged");
            }});

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(!chosen_author.isEmpty() && !chosen_publication_year.isEmpty()) {
                    fragmentSendDataListener.onSendData(chosen_author, chosen_publication_year);
                    Log.d(TAG, "onClick");
                }
                else {
                    Toast toast = Toast.makeText(getActivity(), R.string.incorrect_input, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
    }

}