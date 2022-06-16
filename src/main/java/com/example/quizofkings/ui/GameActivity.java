package com.example.quizofkings.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizofkings.R;
import com.example.quizofkings.model.Game;
import com.example.quizofkings.model.User;
import com.example.quizofkings.network.WebSocket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GameActivity extends AppCompatActivity {
Button startTurn,back;
TextView home,result,away;
static int round;
Game game=new Game();
JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        startTurn=findViewById(R.id.turn_button);
        back=findViewById(R.id.back_button);
        home=findViewById(R.id.home_name);
        result=findViewById(R.id.score_board);
        away=findViewById(R.id.away_name);

     //   home.setText(LoginActivity.username);
     //   away.setText(getIntent().getExtras().get("rival").toString());
     //   game.setHomePoint(getIntent().getExtras().get("home").toString());
      // result.setText(game.getHomePoint()+"-"+getIntent().getExtras().get("away").toString());


    }

    public void back(View view) {
        Intent intent=new Intent(GameActivity.this,MainPage.class);
        startActivity(intent);
        finish();

    }

    public void startTurn(View view) {
        Intent intent=new Intent(GameActivity.this,MainPage.class);
round=round+3;
if (round>16){

        }
        else {
            intent.putExtra("round",round);
            startActivity(intent);
        }
    }
    public class Details extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            String url = String.format(objects[0].toString(), objects[1].toString());
            return WebSocket.getContent(url);
        }

        @Override
        protected void onPostExecute(Object o) {
            String json = o.toString();
            try {
                jsonArray = (JSONArray) new JSONParser().parse(json);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                Game game = new Game(jsonObject.get("home").toString(), jsonObject.get("away").toString(),
                        jsonObject.get("home_point").toString(), jsonObject.get("home_point").toString(), jsonObject.get("turn").toString());
           home.setText(game.getHome());
           away.setText(game.getAway());
           result.setText(game.getHomePoint()+"-"+game.getAwayPoint());
           if (game.getTurn().equals("2")){
               startTurn.setEnabled(false);
           }
            }
        }


    }
}