package com.example.tasks;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import DataManager.FirebaseManager;
import DataManager.SharedPrefManager;
import DataManager.User;


public class HomeActivity extends AppCompatActivity {

    FirebaseManager firebaseManager;
    SharedPrefManager prefManager;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        AppCompatButton btnLogOut = (AppCompatButton) findViewById(R.id.BUTTON_LOGOUT);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignOut();
            }
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        prefManager = new SharedPrefManager(getApplicationContext());
        // firebase

        firebaseManager = new FirebaseManager(getApplicationContext());
        TextView text = (TextView) findViewById(R.id.USER_DATA);
        ImageView img = (ImageView) findViewById(R.id.PROFILE_PIC);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){

            // data from google
            Uri photo = acct.getPhotoUrl();
            Picasso.get().load(photo).into(img);
            String name = acct.getDisplayName();
            String email = acct.getEmail();
            String id = acct.getId();

            // check if user is registered in firebase
            if(firebaseManager.CheckUser(id)){
                firebaseManager.ReadUser(id); // get userdata from firebase and save it to sharedprefs
                String position = prefManager.GetStringData(SharedPrefManager.STRING_FIELD_WORK_POSITION);
                StringBuilder sb = new StringBuilder();
                sb.append(name);
                sb.append("\nПозиция: ");
                sb.append(position);
                text.setText(sb.toString());
                //Log.v("FIREBASE","User was found. ");
            }else{
                // add the user in DB
                firebaseManager.AddNewUser(id,name,email,4,"Миксер");
            }
        }

    }
    private void SignOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
            }
        });
    }
}
