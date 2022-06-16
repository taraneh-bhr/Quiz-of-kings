package com.example.quizofkings.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizofkings.R;
import com.example.quizofkings.network.ServiceAddress;
import com.example.quizofkings.network.WebSocket;

public class RegisterActivity extends AppCompatActivity {
    Button register1;
    EditText usernameRegister, passwordRegister,passwordConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         register1= findViewById(R.id.register1);
        usernameRegister = findViewById(R.id.usernameRegister);
        passwordRegister = findViewById(R.id.passwordRegister);
        passwordConfirm = findViewById(R.id.passwordConfirm);

        usernameRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        passwordRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (usernameRegister.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "please complete username field", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        passwordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (passwordRegister.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "please complete field", Toast.LENGTH_SHORT).show();
                }
                else if(passwordRegister.getText().toString().length()<5){
                    Toast.makeText(RegisterActivity.this, "minimum password character is 5", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (passwordConfirm.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "please complete field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Register(View view) {
        String username = usernameRegister.getText().toString();
        String password = passwordRegister.getText().toString();

        if (username.isEmpty()||password.isEmpty()||passwordConfirm.getText().toString().isEmpty()){
            Toast.makeText(this, "please complete fields", Toast.LENGTH_SHORT).show();
        }
        else {
            Register register=new Register();
            MainActivity.username=username;
            register.execute(ServiceAddress.MAIN_REGISTER,username, password);
        }
    }
    public class Register extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            String url = String.format(objects[0].toString(), objects[1].toString(), objects[2].toString());
            return WebSocket.getContent(url);
        }

        @Override
        protected void onPostExecute(Object o) {
            String role = o.toString();
            switch (role) {
                case "you are registered":
                    Toast.makeText(RegisterActivity.this, "you are registered", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(RegisterActivity.this, MainPage.class);
                    startActivity(intent);
                  /*  intent.putExtra("coin","2000");
                    intent.putExtra("point","50");*/
                    finish();
                    break;
                case "user is duplicate":
                    Toast.makeText(RegisterActivity.this, "this username already exists", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(RegisterActivity.this, "a error occurred", Toast.LENGTH_SHORT).show();
            }

        }
    }
}