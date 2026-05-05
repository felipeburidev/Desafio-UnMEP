package org.example;

public class UserSummary {

    private int userId;
    private String userName;
    private int totalMinutes;



    public UserSummary(int userId, String userName, int totalMinutes) {
        this.userId = userId;
        this.userName = userName;
        this.totalMinutes = totalMinutes;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }
}