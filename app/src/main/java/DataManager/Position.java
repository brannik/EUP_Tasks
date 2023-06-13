package DataManager;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Position {

    public String PositionName;

    public Position(){

    }
    public Position(String _name){
        this.PositionName = _name;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("POSITION_NAME",PositionName);
        return result;
    }
}
