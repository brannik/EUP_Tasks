package com.example.tasks.DataManager;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkPositions {
    public List<Position> positions;

    public WorkPositions(){

    }
    public WorkPositions(List<Position> _position){
        this.positions=_position;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("POSITIONS",positions);
        return result;
    }
}
