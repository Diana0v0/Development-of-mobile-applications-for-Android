package com.example.androidapp;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
/*
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
*/

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
        Log.d(TAG, "OnSendDataBegin");
        //ShowData showDataFragment  = new ShowData();
        //getSupportFragmentManager().beginTransaction().add(showDataFragment, TAG).commit();
        ShowData showDataFragment = (ShowData) getSupportFragmentManager().findFragmentById(R.id.showDataFragment);
        if (showDataFragment != null)
            showDataFragment.setSelectedItems(authorData, publicationYearData);
        Log.d(TAG, "OnSendDataEnd");
    }

    /*
    String chosen_author = "";
    String chosen_publication_year = "";
    TextView selection;
    RadioGroup radio_buttons;
    Button button;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selection = findViewById(R.id.selection);
        radio_buttons = findViewById(R.id.radios);
        button = findViewById(R.id.button_ok);
        spinner = findViewById(R.id.spinner);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.spinner_items));
        spinner.setAdapter(adapter);


        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(@NonNull AdapterView<?> parent, View view, int position, long id) {

                chosen_author = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner.setOnItemSelectedListener(itemSelectedListener);


        radio_buttons.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup arg0, int id) {
                RadioButton selected_button = (RadioButton) findViewById(radio_buttons.getCheckedRadioButtonId());
                chosen_publication_year = selected_button.getText().toString();
            }});


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if(!chosen_author.isEmpty() && !chosen_publication_year.isEmpty()) {
                    selection.setText(getString(R.string.choice_message, chosen_author, chosen_publication_year));
                }
                else {
                    selection.setText(R.string.empty);
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.incorrect_input, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

    }
*/
}