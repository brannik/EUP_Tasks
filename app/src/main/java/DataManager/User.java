package DataManager;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class User {
    public String DisplayName;
    public String Email;
    public String ID;
    public String WPosition;
    public List<UserAccess> access;

    public User(){

    }
    public User(String _id,String _displayName,String _email,String _wPosition,@Nullable List<UserAccess> _access){
        this.ID = _id;
        this.DisplayName = _displayName;
        this.Email = _email;
        this.WPosition = _wPosition;
        if(_access != null)
            this.access = _access;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ID",ID);
        result.put("DisplayName", DisplayName);
        result.put("Email", Email);
        result.put("WPosition", WPosition);

        return result;
    }

}
