package com.example.contentarticle.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.example.contentarticle.R;
import com.example.contentarticle.helper.DatabaseClient;
import com.example.contentarticle.model.room.Content;

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
    String status;

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

        status="";

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radiomale:
                        status = "Male";
                        break;
                    case R.id.radiofemale:
                        status = "Female";
                        break;
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (judul.getText().toString().equals("") || content.getText().toString().equals("")
                || tanggal.getText().toString().equals("") || phone.getText().toString().equals("")
                || status.equals("")){
                    Toast.makeText(AddActivity.this, "Mohon Lengkapi Data Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }
                else {
                    Save();
                    finish();
                    Toast.makeText(AddActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                }

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

    private void Save(){
        final String mJudul   = judul.getText().toString();
        final String mContent = content.getText().toString();
        final String mGender  = status;
        final String mPhone   = phone.getText().toString();
        final String mTanggal = tanggal.getText().toString();

            class SaveContent extends AsyncTask<Void, Void, Void> {

                @Override
                protected Void doInBackground(Void... voids) {
                    Content content = new Content();
                    content.setJudul(mJudul);
                    content.setMycontent(mContent);
                    content.setCategory(mGender);
                    content.setTanggal(mTanggal);
                    content.setPhone(mPhone);

                    DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                            .contentDao()
                            .insert(content);

                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(AddActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddActivity.this,HomeActivity.class));
                    finish();
                }
            }

            SaveContent saveContent = new SaveContent();
            saveContent.execute();
    }
}
