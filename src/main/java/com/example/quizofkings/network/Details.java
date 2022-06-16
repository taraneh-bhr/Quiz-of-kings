package com.example.quizofkings.network;

import android.os.AsyncTask;

import com.example.quizofkings.model.User;


public class Details extends AsyncTask {
    @Override
    protected Object doInBackground(Object[] objects) {
        String url = String.format(objects[0].toString(), objects[1].toString());
        return WebSocket.getContent(url);
    }

    @Override
    protected void onPostExecute(Object o) {
        String[] result = o.toString().split("/");
        User user=new User(Integer.parseInt(result[0]),Integer.parseInt(result[1]));

    }
}

