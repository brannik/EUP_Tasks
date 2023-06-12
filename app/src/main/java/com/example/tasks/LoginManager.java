package com.example.tasks;

import android.content.Context;

public class LoginManager {

    private Context context;
    PrefsManager prefsManager;
    public LoginManager(Context _context){
        context = _context;
        prefsManager = new PrefsManager(context);
    }
    public boolean Login(){
        return false;
    }
    public boolean Register(){
        return true;
    }

}
