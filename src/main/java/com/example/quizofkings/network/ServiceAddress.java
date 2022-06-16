package com.example.quizofkings.network;


public class ServiceAddress {
    public static final String CREATE_GAME_SESSION = "http://192.168.228.1:8081/untitled_war_exploded/iran/game/creategame?username=%s&password=%s";
    public static final String MAIN_REGISTER = "http://192.168.1.5:8081/untitled_war_exploded/iran/main/register?username=%s&password=%s";
    public static final String MAIN_LOGIN = "http://192.168.1.5:8081/untitled_war_exploded/iran/main/login?username=%s&password=%s";
    public static final String USER_TURN = "http://192.168.1.5:8081/untitled_war_exploded/iran/game/turn?home=%s&away=%s&turn=%s";
    public static final String USER_SHOW = "http://192.168.1.5:8081/untitled_war_exploded/iran/game/show?home=%s";
    public static final String UNLIMITED_USER = "http://192.168.228.1:8081/untitled_war_exploded/iran/main/unlimit?username=%s&coin=%s";
    public static final String CHANGE_USERNAME = "http://192.168.228.1:8081/untitled_war_exploded/iran/main/changeusername?username=%s&new=%s";
    public static final String CHANGE_PASSWORD = "http://192.168.228.1:8081/untitled_war_exploded/iran/main/changepassword?username=%s&new=%s";
    public static final String GAME_CATEGORY = "http://192.168.228.1:8081/untitled_war_exploded/iran/data/%s";

}
