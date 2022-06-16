package com.example.quizofkings.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class NewGameActivity extends AppCompatActivity {
    EditText selectRival;
    Button randomRival,searchRival;
    Details details=new Details();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        selectRival=findViewById(R.id.search_Rival);
        randomRival=findViewById(R.id.randomButton);
        searchRival=findViewById(R.id.search_rival_button);



    }

    public void randomRival(View view) {
        details.execute(ServiceAddress.CREATE_GAME_SESSION,MainActivity.username,null);
    }

    public void searchRival(View view) {
    }
    public class Details extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            String url = String.format(objects[0].toString(), objects[1].toString(),objects[2].toString());
            return WebSocket.getContent(url);
        }

        @Override
        protected void onPostExecute(Object o) {
            String result=o.toString();
            switch (result){
                case "your active game is over":
                    Toast.makeText(NewGameActivity.this, "5 game already exists", Toast.LENGTH_SHORT).show();
                    break;
                case "error":
                    Toast.makeText(NewGameActivity.this, "error", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Intent intent=new Intent(NewGameActivity.this,GameActivity.class);
                    intent.putExtra("rival",result);
                    startActivity(intent);
            }

        }
    }
}