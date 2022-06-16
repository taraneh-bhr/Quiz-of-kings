package com.example.quizofkings.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizofkings.R;
import com.example.quizofkings.model.User;
import com.example.quizofkings.network.ServiceAddress;
import com.example.quizofkings.network.WebSocket;

import static com.example.quizofkings.ui.MainActivity.username;

public class LoginActivity extends AppCompatActivity {
    Button login1;
    EditText usernameValue, passwordValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login1 = findViewById(R.id.login1);
        usernameValue = findViewById(R.id.usernameValue);
        passwordValue = findViewById(R.id.passwordValue);

        usernameValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (usernameValue.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "please complete field", Toast.LENGTH_SHORT).show();
                }
            }
        });
        passwordValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (passwordValue.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "please complete field", Toast.LENGTH_SHORT).show();
                }       }
        });
    }

    public void login(View view) {

          username = usernameValue.getText().toString();
        String password = passwordValue.getText().toString();
        Login login = new Login();
        if (username.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "please complete fields", Toast.LENGTH_SHORT).show();
        }
        else {
            login.execute(ServiceAddress.MAIN_LOGIN, username, password);
        }
    }


    public class Login extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            String url = String.format(objects[0].toString(), objects[1].toString(), objects[2].toString());
            return WebSocket.getContent(url);
        }

        @Override
        protected void onPostExecute(Object o) {
            String role = o.toString();
            switch (role) {
                case "failed":
                    Toast.makeText(LoginActivity.this, "username or password is incorrect", Toast.LENGTH_SHORT).show();
                    break;
                case "error":
                    Toast.makeText(LoginActivity.this, "a error occurred", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    String[] result=role.split("/");
                    //User user=new User(Integer.parseInt(result[0]),Integer.parseInt(result[1]));
                    Intent intent=new Intent(LoginActivity.this,MainPage.class);
                    intent.putExtra("coin",result[0]);
                    intent.putExtra("point",result[1]);
                    startActivity(intent);
                    finish();
                    break;



            }

        }
    }

}