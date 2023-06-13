package DataManager;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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
    public void AddNewUser(String _id,String _displayName,String _email,int _rank,String _wPosition){
        User user = new User(_id,_displayName,_email,_rank,_wPosition);
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
                    Log.v(TAG,user.WPosition);
                }

            }
        });
    }
    public boolean CheckUser(String _id){
        db.collection("users").document(_id).get();
        return true;
    }

}
