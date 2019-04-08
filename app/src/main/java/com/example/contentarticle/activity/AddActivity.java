package com.example.contentarticle.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.contentarticle.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    ImageView back;
    EditText judul, tanggal, phone, content;
    Button btn_save;
    RadioGroup radioGroup;
    RadioButton radio_male, radio_female;
    TextView gender;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        back = (ImageView) findViewById(R.id.back);
        judul = (EditText) findViewById(R.id.judul);
        tanggal = (EditText) findViewById(R.id.tanggal);
        phone = (EditText) findViewById(R.id.phone);
        content = (EditText) findViewById(R.id.kontent);
        btn_save = (Button) findViewById(R.id.save);
        radioGroup = (RadioGroup) findViewById(R.id.rGroup);
        radio_male = (RadioButton) findViewById(R.id.radiomale);
        radio_female = (RadioButton) findViewById(R.id.radiofemale);
        gender = (TextView) findViewById(R.id.gender);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddActivity.this, HomeActivity.class));
                finish();
            }
        });

        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel(){
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tanggal.setText(sdf.format(myCalendar.getTime()));
    }
}
