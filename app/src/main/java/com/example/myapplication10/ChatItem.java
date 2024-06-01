package com.example.myapplication10;

public class ChatItem {

    private String userName;
    private String message;

    public ChatItem(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}
