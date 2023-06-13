package DataManager;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String DisplayName;
    public String Email;
    public String ID;
    public int Rank;
    public String WPosition;

    public User(){

    }
    public User(String _id,String _displayName,String _email,int _rank,String _wPosition){
        this.ID = _id;
        this.DisplayName = _displayName;
        this.Email = _email;
        this.Rank = _rank;
        this.WPosition = _wPosition;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("ID",ID);
        result.put("DisplayName", DisplayName);
        result.put("Email", Email);
        result.put("Rank", Rank);
        result.put("WPosition", WPosition);

        return result;
    }

}
