package com.example.quizofkings.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizofkings.R;
import com.example.quizofkings.network.ServiceAddress;
import com.example.quizofkings.network.WebSocket;

public class Category extends AppCompatActivity {
int round;
Button button1,button2,button3;
TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        title=findViewById(R.id.title_category);

        round= (int) getIntent().getExtras().get("round");
    }

    public void start_question(View view) {
        Question question=new Question();
        switch (view.getId()){
                    case R.id.button1:
                        question.execute(ServiceAddress.GAME_CATEGORY,round);
                        break;
                    case R.id.button2:
                        question.execute(ServiceAddress.GAME_CATEGORY,round+1);
                        break;
                    case R.id.button3:
                        question.execute(ServiceAddress.GAME_CATEGORY,round+2);
                        break;
                }


    }
    public class Question extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            String url = String.format(objects[0].toString(), objects[1].toString());
            return WebSocket.getContent(url);
        }

        @Override
        protected void onPostExecute(Object o) {
            String json = o.toString();
            Intent intent=new Intent(Category.this,QuestionActivity.class);
            intent.putExtra("question",json);
            startActivity(intent);
            finish();
        }
    }
}