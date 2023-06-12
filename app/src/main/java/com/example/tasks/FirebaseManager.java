package com.example.tasks;

public class FirebaseManager {
    private static final String FIELD_NAME = "name";
    private static final String FIELD_ID = "id";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_RANK = "rank";
    private static final String FIELD_WORK_POSITION = "work_position";
    private static final String FIELD_WORK_NUMBER = "work_number";
    private final String TAG = "FIREBASE";

    public FirebaseManager(){

    }
    public void AddUser(){
        // add new user into db
    }
    public boolean IsUserExisting(String _userId){
        // find user by id
        return false;
    }
}
