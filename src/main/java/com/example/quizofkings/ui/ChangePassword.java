package com.example.quizofkings.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizofkings.R;
import com.example.quizofkings.network.ServiceAddress;
import com.example.quizofkings.network.WebSocket;

public class ChangePassword extends AppCompatActivity {
    EditText newPassword,confirmNewPassword;
    Button confirmPassword;
    String newPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        newPassword=findViewById(R.id.newpassword);
        confirmNewPassword=findViewById(R.id.new_password_Confirm);
        confirmPassword=findViewById(R.id.confirmbutton);
    }

    public void confirmPassword(View view) {
        if (newPassword.getText().toString().equals(confirmPassword.getText().toString())){
            if (confirmPassword.length()>=5){
                Details details=new Details();
                newPass=confirmPassword.getText().toString();
                details.execute(ServiceAddress.CHANGE_PASSWORD,MainActivity.username,newPass);

            }
        }
    }
    public class Details extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            String url = String.format(objects[0].toString(), objects[1].toString(),objects[2]);
            return WebSocket.getContent(url);
        }

        @Override
        protected void onPostExecute(Object o) {
            if (o.toString().equals("error")){
                Toast.makeText(ChangePassword.this, "error", Toast.LENGTH_SHORT).show();
            }

            else {
                Toast.makeText(ChangePassword.this, "password changed", Toast.LENGTH_SHORT).show();
            }


        }
    }
}