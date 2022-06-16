package com.example.quizofkings.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizofkings.R;

import java.util.ArrayList;

public class MyTurnAdapter extends RecyclerView.Adapter<MyTurnAdapter.ProductViewHolder> {
    public Context context;
    public ArrayList<Game> list;

    public MyTurnAdapter(Context context, ArrayList<Game> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.main_list,viewGroup,false);
        return new ProductViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int position) {
        Game model= list.get(position);
        productViewHolder.textView.setText(model.getHome()+model.getHomePoint()+"-"+model.getAway()+model.getAwayPoint());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.text_list);
    }
}
}
