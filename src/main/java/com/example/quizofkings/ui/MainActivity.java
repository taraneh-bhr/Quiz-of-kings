package com.example.quizofkings.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizofkings.R;


public class MainActivity extends AppCompatActivity {
    Button login,signUp,guest;
    Intent intent;
    static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.login);
        signUp=findViewById(R.id.signUp);
        guest=findViewById(R.id.guest);


    }

    public void login(View view) {
        intent=new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        intent=new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);

    }


    public void guest(View view) {
        intent=new Intent(MainActivity.this, MainPage.class);
        intent.putExtra("coin",0);
        intent.putExtra("point",0);
        username="guest";
        startActivity(intent);

    }

}