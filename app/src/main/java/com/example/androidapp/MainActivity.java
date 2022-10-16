package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{
    // array of strings to be displayed in drop down list
    String[] authors = {"",
            "J.R.R. Tolkien", "Agatha Christie",
            "Ray Bradbury", "Robert Sheckley",
            "Stephen King", "J.K. Rowling"
             };

    String chosen_author = "";
    String chosen_publication_year = "";
    TextView selection;
    RadioGroup radGrp;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get selection TextView object
        selection = findViewById(R.id.selection);
        radGrp = findViewById(R.id.radios);
        button = findViewById(R.id.button_ok);
        Toast toast = Toast.makeText(this, R.string.incorrect_input, Toast.LENGTH_LONG);
        /*
            Set an adapter for spinner (list)
            Convert data array and set (view) as a dropdown list
        */
        // get spinner id
        Spinner spinner = findViewById(R.id.spinner);
        // create an ArrayAdapter using an array of strings and the standard markup of the spinner element
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, authors);
        // define markup to use when selecting an element
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // apply adapter to the spinner element
        spinner.setAdapter(adapter);

        // create a listener for spinner
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // get chosen item
                chosen_author = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        // apply listener to the spinner element
        spinner.setOnItemSelectedListener(itemSelectedListener);

        // switch state switching listener
        radGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup arg0, int id) {
                switch(id) {
                    case R.id.rb_1937:
                        chosen_publication_year = getResources().getString(R.string.rb_1937);
                        break;
                    case R.id.rb_1939:
                        chosen_publication_year = getResources().getString(R.string.rb_1939);
                        break;
                    case R.id.rb_1953:
                        chosen_publication_year = getResources().getString(R.string.rb_1953);
                        break;
                    case R.id.rb_1968:
                        chosen_publication_year = getResources().getString(R.string.rb_1968);
                        break;
                    case R.id.rb_1982:
                        chosen_publication_year = getResources().getString(R.string.rb_1982);
                        break;
                    case R.id.rb_1997:
                        chosen_publication_year = getResources().getString(R.string.rb_1997);
                        break;
                    default:
                        break;
                }
            }});

        // listener for the button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(!chosen_author.isEmpty() && !chosen_publication_year.isEmpty()) {
                    selection.setText(getString(R.string.choice_message, chosen_author, chosen_publication_year));
                }
                else {
                    toast.show();
                }
            }
        });

    }

}