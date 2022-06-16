package com.example.quizofkings.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quizofkings.R;

public class SettingFragment extends Fragment {
    Intent intent;
    Button changeUsername,changePassword;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_setting,container,false);
        changeUsername=view.findViewById(R.id.changeUsername);
        changePassword=view.findViewById(R.id.changePassword);

        changeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getActivity(),ChangeUsername.class);
                startActivity(intent);
            }
        });
changePassword.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        intent=new Intent(getActivity(),ChangePassword.class);
        startActivity(intent);
    }
});
         return view;
    }
}
