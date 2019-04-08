package com.example.contentarticle.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import com.example.contentarticle.R;
import com.example.contentarticle.model.retrovit.ResponseLogin;
import com.example.contentarticle.model.retrovit.ResponseRegister;
import com.example.contentarticle.network.InitRetrofit;
import com.example.contentarticle.network.RestApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button btn_register;
    TextView text_login;
    EditText username, email, password, phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        text_login = (TextView) findViewById(R.id.login);
        btn_register = (Button) findViewById(R.id.register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.mail);
        phone = (EditText) findViewById(R.id.phone);

        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                //bikin toast (massagebox)
                Toast.makeText(RegisterActivity.this, "Silahkan Login", Toast.LENGTH_SHORT).show();

                finish();

            }
        });


        //SetClickOnlistener berfungsi untuk memberi aksi
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals("") || password.getText().toString().equals("") ||
                        username.getText().toString().equals("") || phone.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "Harap Isi Data Dengan Lengkap", Toast.LENGTH_SHORT).show();
                } else {

                    register();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        RegisterActivity.super.onBackPressed();
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    private void register() {

        RestApi restApi = InitRetrofit.getInstance();

        Call<ResponseRegister> registerCall = restApi.registerUser(
                username.getText().toString(),
                password.getText().toString(),
                email.getText().toString(),
                phone.getText().toString()
        );

        registerCall.enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {

                if (response.isSuccessful()) {
                    String result = response.body().getResponse();

                    if (result.equals("success")) {
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        //bikin toast (massagebox)
                        finish();
                        Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                    } else if (result.equals(("failed"))) {
                        Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Periksa Koneksi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
