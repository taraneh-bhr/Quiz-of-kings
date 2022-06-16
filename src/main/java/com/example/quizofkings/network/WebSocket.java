package com.example.quizofkings.network;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class WebSocket {
    public static String getContent(String stringUrl) {
        String content = "";
        try {
            URL url = new URL(stringUrl);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            int ascii = inputStream.read();
            while (ascii != -1) {
                char current = (char) ascii;
                ascii = inputStream.read();
                content += current;
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return content;
    }
}
