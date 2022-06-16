package com.example.quizofkings.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizofkings.R;
import com.example.quizofkings.model.User;
import com.example.quizofkings.network.ServiceAddress;
import com.example.quizofkings.network.WebSocket;

public class ChangeUsername extends AppCompatActivity {
EditText newUsername;
Button confirmPassword;
 String newUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);
        newUsername=findViewById(R.id.newUsername);
        confirmPassword=findViewById(R.id.confirmUsername);

    }

    public void confirmUsername(View view) {
        Details details=new Details();
        newUser=newUsername.getText().toString();
        details.execute(ServiceAddress.CHANGE_USERNAME,MainActivity.username,newUser);
    }
    public class Details extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            String url = String.format(objects[0].toString(), objects[1].toString(),objects[2]);
            return WebSocket.getContent(url);
        }

        @Override
        protected void onPostExecute(Object o) {
         if (o.toString().equals("user is duplicate")){
             Toast.makeText(ChangeUsername.this, "this username already exists", Toast.LENGTH_SHORT).show();
         }
         else if (o.toString().equals("error")){
             Toast.makeText(ChangeUsername.this, "error", Toast.LENGTH_SHORT).show();
         }
         else {
             Toast.makeText(ChangeUsername.this, "username changed", Toast.LENGTH_SHORT).show();
            MainActivity.username=newUser;
         }


        }
    }
}