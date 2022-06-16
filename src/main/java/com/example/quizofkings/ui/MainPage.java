package com.example.quizofkings.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizofkings.R;
import com.example.quizofkings.model.Game;
import com.example.quizofkings.model.MyTurnAdapter;

import com.example.quizofkings.network.ServiceAddress;
import com.example.quizofkings.network.WebSocket;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {
    BottomNavigationView bottomNav;
    FragmentManager fragmentManager;
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        bottomNav = findViewById(R.id.bottom_nav);
  /*      getIntent().getExtras().get("coin").toString();
      String coin=  getIntent().getExtras().get("point").toString();
Intent intent=new Intent();
intent.putExtra("coin",coin);
        intent.putExtra("point",coin);*/

         fragmentManager = getSupportFragmentManager();
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                //Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.item_1:
                        fragment=new HomeFragment();
                        break;
                    case R.id.item_2:
                        fragment=new AccountFragment();
                        break;
                        case R.id.item_3:
                            fragment=new SettingFragment();
            }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment,fragment).commit();
                return true;            }
        }
        );
        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.item_1);
        }
    }

}
