package com.example.quizofkings.model;

public class Game {
    static final int MAX_ACTIVE_GAME=5;
    String home,away;
    String homePoint,awayPoint;
    public Game() {

    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getAway() {
        return away;
    }

    public void setAway(String away) {
        this.away = away;
    }

    public String getHomePoint() {
        return homePoint;
    }

    public void setHomePoint(String homePoint) {
        this.homePoint = homePoint;
    }

    public String getAwayPoint() {
        return awayPoint;
    }

    public void setAwayPoint(String awayPoint) {
        this.awayPoint = awayPoint;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public Game(String home, String away, String homePoint, String awayPoint, String turn) {
        this.home = home;
        this.away = away;
        this.homePoint = homePoint;
        this.awayPoint = awayPoint;
        this.turn = turn;
    }

    String turn; //1=home 2=away
}
