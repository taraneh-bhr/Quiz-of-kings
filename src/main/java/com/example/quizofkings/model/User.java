package com.example.quizofkings.model;

public class User {
   private int coin,point;
   private String username,password;

    public User() {

    }

    public int getCoin() {
        return coin;
    }

    public User(int coin, int point) {
        this.coin = coin;
        this.point = point;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public User(String username) {
        this.username = username;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
