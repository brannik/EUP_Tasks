package com.example.tasks.DataManager;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class SharedPrefManager extends AppCompatActivity {
    public static final String STRING_FIELD_ID = "ID";
    public static final String STRING_FIELD_NAME = "NAME";
    public static final String STRING_FIELD_EMAIL = "EMAIL";
    public static final String INT_FIELD_WORK_POSITION = "WORK_POSITION";
    public static final String STRING_FIELD_WORK_POSITION = "WORK_POSITION_NAME";

    // store user information here for easy access from other classes
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    public SharedPrefManager(Context _context){
        sharedPreferences = _context.getSharedPreferences("UserInfoFromDB",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
    }

    public void SaveData(User _user){
        myEdit.putString(STRING_FIELD_ID,_user.ID);
        myEdit.putString(STRING_FIELD_NAME,_user.DisplayName);
        myEdit.putString(STRING_FIELD_EMAIL,_user.Email);
        myEdit.putInt(INT_FIELD_WORK_POSITION,_user.WPosition);
        List<UserAccess> access = _user.access;
        for(int i=0;i< access.size();i++){
            //Log.v("FIREBASE","DBG->" + access.get(i).AccessLevel + " | " + access.get(i).AccessDescription + " | " + access.get(i).AccessPermission);
            myEdit.putBoolean(access.get(i).AccessLevel,access.get(i).AccessPermission);
        }
        myEdit.commit();
    }
    public String GetStringData(String STRING_FIELD_NAME){
        return sharedPreferences.getString(STRING_FIELD_NAME,"");
    }
    public int GetIntData(String INT_FIELD_NAME){
        return sharedPreferences.getInt(INT_FIELD_NAME,0);
    }
    public boolean GetBooleanData(String BOOL_FIELD_NAME){
        return sharedPreferences.getBoolean(BOOL_FIELD_NAME,false);
    }

    public void AddPositionName(String _name){
        myEdit.putString(STRING_FIELD_WORK_POSITION,_name);
        myEdit.commit();
    }
}
