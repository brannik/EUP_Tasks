package com.example.tasks;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {
    private Context context;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginEditor;
    public static final String LoginRecords = "LoginRecords";
    public PrefsManager(Context _context){
        context = _context;
        loginPreferences = _context.getSharedPreferences(LoginRecords, Context.MODE_PRIVATE);
        loginEditor = loginPreferences.edit();
    }
    public void SetAutoLogin(boolean _state){
        loginEditor.putBoolean("AUTO_LOGIN",_state);
        loginEditor.commit();
    }
    public boolean GetAutoLogin(){
        return loginPreferences.getBoolean("AUTO_LOGIN",false);
    }
}
