package DataManager;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseManager {
    private Context context;
    private static final String TAG = "FIREBASE";
    FirebaseFirestore db;
    SharedPrefManager prefManager;

    public FirebaseManager(Context _context){
        db = FirebaseFirestore.getInstance();
        prefManager = new SharedPrefManager(_context);
        context = _context;
    }
    public void AddNewUser(String _id,String _displayName,String _email,int _wPosition){
        List<UserAccess> access = new ArrayList<>();
        access.add(new UserAccess(UserAccess.ACCESS_LEVEL_ENTRY,true,"Basic access, entry level"));
        access.add(new UserAccess(UserAccess.ACCESS_LEVEL_NORMAL,false,"Normal user"));
        access.add(new UserAccess(UserAccess.ACCESS_LEVEL_ADVANCED,false,"Advanced user"));
        access.add(new UserAccess(UserAccess.ACCESS_LEVEL_MODERATOR,false,"Moderator"));
        access.add(new UserAccess(UserAccess.ACCESS_LEVEL_ADMINISTRATOR,false,"Administrator"));
        User user = new User(_id,_displayName,_email,_wPosition,access);
        db.collection("users").document(_id).set(user);
    }
    public void ReadUser(String _id){
        DocumentReference drf = db.collection("users").document(_id);
        drf.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                if (user != null) {
                    prefManager.SaveData(user); // save to sharedPrefs
                    Log.v(TAG, String.valueOf(user.WPosition));
                }
            }
        });
    }
    public boolean CheckUser(String _id){
        String prefId = prefManager.GetStringData(SharedPrefManager.STRING_FIELD_ID);
        String dbId = db.collection("users").document(_id).getId();
        //Log.v(TAG,"CHECK-> " + dbId + " <> " + prefId);
        return prefId.equals(dbId);
    }
    public String GetUserWorkPosition(int _posId){
        // find position name from firebase
        return "Position fom db " + _posId;
    }

}
