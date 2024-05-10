package com.example.guidedexercise11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText name, age;
    RadioButton male, female;
    CheckBox appdet, intcomp, comprog1, comprog2;
    Spinner job;
    ListView thesis;
    Button submit;
    Intent intent;
    Adapter adapter;
    String[] jobList = {"Software Developer", "Software QA", "System Analyst", "Data Scientist"};
    String[] thesisTopics = {"Web Based/On-Line Application", "Network-Based Application ",
            "System/Software Development ", "Computer Aided Instruction "};
    String gender, subjects, topic = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        displayResult();
    }

    public void init() {
        name = findViewById(R.id.etNameGE11);
        age = findViewById(R.id.etAgeGE11);
        male = findViewById(R.id.rbtnMaleGE11);
        female = findViewById(R.id.rbtnFemaleGE11);
        appdet = findViewById(R.id.chkAppdetGE11);
        intcomp = findViewById(R.id.chkIntcompGE11);
        comprog1 = findViewById(R.id.chkComprog1GE11);
        comprog2 = findViewById(R.id.chkComprog2GE11);
        submit = findViewById(R.id.btnSubmitGE11);

        intent = new Intent(this, IntentResultActivity.class);

        job = findViewById(R.id.spnrJobGE11);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, jobList);
        job.setAdapter((SpinnerAdapter) adapter);
        thesis = findViewById(R.id.lvThesisGE11);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, thesisTopics);
        thesis.setAdapter((ListAdapter) adapter);
    }

    public void displayResult() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                } else if (age.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your age", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Integer.parseInt(age.getText().toString()) < 17) {
                    Toast.makeText(getApplicationContext(), "You must be at least 17 years old", Toast.LENGTH_SHORT).show();
                    return;
                } else if (getSubjects().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please select at least 1 for your favorite subject", Toast.LENGTH_SHORT).show();
                    return;
                } else if (getThesis().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please select your Thesis Topic", Toast.LENGTH_SHORT).show();
                    return;
                }

                intent.putExtra("id_name", name.getText().toString());
                intent.putExtra("id_age", age.getText().toString());
                intent.putExtra("id_gender", getGender());
                intent.putExtra("id_subjects", getSubjects());
                intent.putExtra("id_job", jobList[job.getSelectedItemPosition()]);
                intent.putExtra("id_thesis", getThesis());
                startActivity(intent);
            }
        });
    }

    public String getSubjects() {
        subjects = "";
        if (appdet.isChecked()) {
            subjects += appdet.getText().toString() + "\n";
        }
        if (intcomp.isChecked()) {
            subjects += intcomp.getText().toString() + "\n";
        }
        if (comprog1.isChecked()) {
            subjects += comprog1.getText().toString() + "\n";
        }
        if (comprog2.isChecked()) {
            subjects += comprog2.getText().toString() + "\n";
        }
        return subjects;
    }

    public String getGender() {
        return male.isChecked() ? male.getText().toString() : female.getText().toString();
    }

    public String getThesis() {
        thesis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "You select " + thesisTopics[position], Toast.LENGTH_SHORT).show();
                topic = thesisTopics[position];
            }
        });
        return topic;
    }
}
