package com.example.tasks.DataManager;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class UserAccess {
    public static final String ACCESS_LEVEL_ENTRY = "1";
    public static final String ACCESS_LEVEL_NORMAL = "2";
    public static final String ACCESS_LEVEL_ADVANCED = "3";
    public static final String ACCESS_LEVEL_MODERATOR = "4";
    public static final String ACCESS_LEVEL_ADMINISTRATOR = "5";
    public String AccessLevel;
    public boolean AccessPermission;

    public String AccessDescription;
    public UserAccess(){

    }
    public UserAccess(String _level,boolean _permission,String _description){
        this.AccessLevel = _level;
        this.AccessPermission = _permission;
        this.AccessDescription = _description;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("TARGET",AccessLevel);
        result.put("PERMISSION", AccessPermission);
        result.put("DESCRIPTION", AccessDescription);
        return result;
    }
}
