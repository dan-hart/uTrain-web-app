package org.launchcode.uTrain.models;

public enum UserSex {

    MALE("Male"),
    FEMALE("Female");

    private final String userSex;

    UserSex(String userSex) { this.userSex = userSex; }

    public String getUserSex() {
        return userSex;
    }
}
