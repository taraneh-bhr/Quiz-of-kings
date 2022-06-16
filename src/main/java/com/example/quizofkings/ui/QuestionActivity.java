package com.example.quizofkings.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizofkings.R;
import com.example.quizofkings.model.Game;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class QuestionActivity extends AppCompatActivity {
    TextView setQuestion;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    RadioGroup radioGroup;
    Button next, remove2, twoChance;
    int index = 0;//shomare soal
    String MyAns, UserAns = "";
    int TotalCurrentAns, TotalQuestion = 3, TotalKeep, TotalWrong;
    JSONArray jsonArray = new JSONArray();
    JSONObject jsonObject = new JSONObject();
    String json;
    String[] questions = new String[3];
    String[] a = new String[3];
    String[] b = new String[3];
    String[] c = new String[3];
    String[] d = new String[3];
    String[] ca = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        radioGroup = findViewById(R.id.radiogroup);

        radioButton1 = findViewById(R.id.Rbutton1);
        radioButton2 = findViewById(R.id.Rbutton2);
        radioButton3 = findViewById(R.id.Rbutton3);
        radioButton4 = findViewById(R.id.Rbutton4);



        next = findViewById(R.id.btnNext);
        remove2 = findViewById(R.id.remove2);
        twoChance = findViewById(R.id.two_chance);


        setQuestion = findViewById(R.id.question);

        json = getIntent().getExtras().get("json").toString();
        try {
            jsonArray = (JSONArray) new JSONParser().parse(json);
        } catch (
                ParseException e) {
            e.printStackTrace();
        }
        for (Object object : jsonArray) {
            int i = 0;
            while (i <= 3) {
                JSONObject jsonObject = (JSONObject) object;
         /*  setQuestion.setText(jsonObject.get("Ques").toString());
           radioButton1.setText(jsonObject.get("A1").toString());
            radioButton2.setText(jsonObject.get("A2").toString());
            radioButton3.setText(jsonObject.get("A3").toString());
            radioButton4.setText(jsonObject.get("A4").toString());
            MyAns=jsonObject.get("CA1").toString();*/
                questions[i] = (jsonObject.get("Ques").toString());
                a[i] = (jsonObject.get("A1").toString());
                b[i] = (jsonObject.get("A2").toString());
                c[i] = (jsonObject.get("A3").toString());
                d[i] = (jsonObject.get("A4").toString());
                ca[i] = jsonObject.get("CA1").toString();
                i++;
            }
        }
        radioButton1.setChecked(false);
        radioButton2.setChecked(false);
        radioButton3.setChecked(false);
        radioButton4.setChecked(false);
        setQuesOne();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                UserAns = radioButton.getText().toString().trim();
                Toast.makeText(QuestionActivity.this, "your answer: " + UserAns, Toast.LENGTH_SHORT).show();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserAns.equals("")) {
                    Toast.makeText(QuestionActivity.this, "choose one", Toast.LENGTH_SHORT).show();                }
                if (UserAns.equals(MyAns)) {
                    TotalCurrentAns++;
                    UserAns = "";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    next();
                } else {
                    TotalWrong++;
                    UserAns = "";
                    radioButton1.setChecked(false);
                    radioButton2.setChecked(false);
                    radioButton3.setChecked(false);
                    radioButton4.setChecked(false);
                    next();
                }

            }
        });
    }

    private void setQuesOne() {

        setQuestion.setText(questions[0]);
        radioButton1.setText(a[0]);
        radioButton2.setText(b[0]);
        radioButton3.setText(c[0]);
        radioButton4.setText (d[0]);
        MyAns=ca[0];
    }
    public void next() {
        if (index == 3) {
            Intent intent=new Intent(QuestionActivity.this,GameActivity.class);
            intent.putExtra("home",TotalCurrentAns);
            intent.putExtra("turn","2");
            startActivity(intent);
        } else {
            index++;
            setQuestion.setText(questions[index]);
            radioButton1.setText(a[index]);
            radioButton2.setText(b[index]);
            radioButton3.setText(c[index]);
            radioButton4.setText (d[index]);
            MyAns=ca[index];
        }
    }
}