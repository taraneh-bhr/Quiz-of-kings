package com.example.quizofkings.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizofkings.R;
import com.example.quizofkings.model.Game;
import com.example.quizofkings.model.MyTurnAdapter;
import com.example.quizofkings.network.ServiceAddress;
import com.example.quizofkings.network.WebSocket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    TextView coinTextView, pointTextView;
    Button newGame, unLimitedGame;
    Details details = new Details();

    ArrayList<Game> list = new ArrayList<>();
    MyTurnAdapter adapter;
    RecyclerView recyclerView_MyTurn, recyclerView_Waiting;
    JSONArray jsonArray;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        coinTextView = view.findViewById(R.id.coin_textView);
        pointTextView = view.findViewById(R.id.point_textView);
        newGame = view.findViewById(R.id.new_game);
        unLimitedGame = view.findViewById(R.id.unlimit_button);
       /* String coin = getActivity().getIntent().getExtras().get("coin").toString();
        String point = getActivity().getIntent().getExtras().get("point").toString();*/
        details.execute(ServiceAddress.USER_SHOW, MainActivity.username);
       /* coinTextView.setText("سکه: " + coin);
        pointTextView.setText("امتیاز: " + point);*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL, false);
        adapter = new MyTurnAdapter(getActivity().getApplicationContext(), list);

        recyclerView_MyTurn = view.findViewById(R.id.my_turn_listView);
        recyclerView_MyTurn.setLayoutManager(layoutManager);
        //recyclerView_MyTurn.setAdapter(adapter);

        recyclerView_Waiting = view.findViewById(R.id.waiting_listView);
//        recyclerView_Waiting.setLayoutManager(layoutManager);
        // recyclerView_Waiting.setAdapter(adapter);

        return view;
    }

    public void unLimit(View view) {
        UnLimit unLimit=new UnLimit();
        unLimit.execute(ServiceAddress.UNLIMITED_USER, MainActivity.username, coinTextView.getText().toString());
    }

    public void newGame(View view) {
        if (list.size()>=5&&newGame.getVisibility()==View.VISIBLE){
            Toast.makeText(getActivity(), "your max active game is over", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent=new Intent(getActivity(),NewGameActivity.class);
            startActivity(intent);
        }
    }


    public class Details extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            String url = String.format(objects[0].toString(),objects[1].toString());
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
          /*  for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                Game game = new Game(jsonObject.get("home").toString(), jsonObject.get("away").toString(),
                        jsonObject.get("home_point").toString(), jsonObject.get("home_point").toString(), jsonObject.get("turn").toString());
                list.add(game);
                if (game.getTurn().equals("1")) {
                    recyclerView_MyTurn.setAdapter(adapter);
                } else {
                    recyclerView_Waiting.setAdapter(adapter);
                }
            }*/
        }


    }
    public class UnLimit extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            String url = String.format(objects[0].toString(), objects[1].toString(), objects[2]);
            return WebSocket.getContent(url);
        }

        @Override
        protected void onPostExecute(Object o) {
            String result = o.toString();
            if (result.equals("failed")) {
                Toast.makeText(getActivity(), "try again", Toast.LENGTH_SHORT).show();
            } else if (result.equals("error")) {
                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                unLimitedGame.setVisibility(View.INVISIBLE);
            }
        }
    }
}