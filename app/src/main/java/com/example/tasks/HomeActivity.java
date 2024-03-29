package com.example.tasks;
import static com.example.tasks.DataManager.UserAccess.ACCESS_LEVEL_ADMINISTRATOR;
import static com.example.tasks.DataManager.UserAccess.ACCESS_LEVEL_ADVANCED;
import static com.example.tasks.DataManager.UserAccess.ACCESS_LEVEL_ENTRY;
import static com.example.tasks.DataManager.UserAccess.ACCESS_LEVEL_MODERATOR;
import static com.example.tasks.DataManager.UserAccess.ACCESS_LEVEL_NORMAL;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.tasks.Fragments.FragmentAdministrator;
import com.example.tasks.Fragments.FragmentCards;
import com.example.tasks.Fragments.FragmentMaterials;
import com.example.tasks.Fragments.FragmentModerator;
import com.example.tasks.Fragments.FragmentProportions;
import com.example.tasks.Fragments.FragmentSchedule;
import com.example.tasks.Fragments.FragmentTasks;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.squareup.picasso.Picasso;

import com.example.tasks.DataManager.FirebaseManager;
import com.example.tasks.DataManager.SharedPrefManager;


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
        Bundle bundle = new Bundle();
        //bundle.put("some_int", 0);
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, FragmentTasks.class, null)
                .commit();
        setContentView(R.layout.home_activity);
        
        AppCompatButton btnLogOut = (AppCompatButton) findViewById(R.id.BUTTON_LOGOUT);
        btnLogOut.setOnClickListener(view -> SignOut());

        Button btnNotify = (Button) findViewById(R.id.BUTTON_NOTIFICATIONS);
        btnNotify.setOnClickListener(view -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.notify_popup, null, false),600,1000, true);

            pw.showAtLocation(findViewById(R.id.BUTTON_NOTIFICATIONS), Gravity.CENTER, 100, -150);

        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        prefManager = new SharedPrefManager(getApplicationContext());
        // firebase
        firebaseManager = new FirebaseManager(getApplicationContext());

        TextView text = (TextView) findViewById(R.id.USER_DATA);
        ImageView img = (ImageView) findViewById(R.id.PROFILE_PIC);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
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
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                int bb = prefManager.GetIntData(SharedPrefManager.INT_FIELD_WORK_POSITION);
                firebaseManager.GetUserWorkPosition(bb);
                String position = prefManager.GetStringData(SharedPrefManager.STRING_FIELD_WORK_POSITION);
                StringBuilder sb = new StringBuilder();
                sb.append(name);
                sb.append("\nПозиция: ");
                sb.append(position);
                text.setText(sb.toString());
                CheckPermissions();
                //firebaseManager.CreateWorkPositions();
                //Log.v("FIREBASE","User was found. ");
            }else{
                // add the user in DB
                firebaseManager.AddNewUser(id,name,email,0);
            }
        }

    }
    private void SignOut(){
        gsc.signOut().addOnCompleteListener(task -> {
            finish();
            startActivity(new Intent(HomeActivity.this,LoginActivity.class));
        });
    }
    private void CheckPermissions(){ // level access the buttons
        boolean isEntry,isNormal,isAdvanced,isModerator,isAdministrator;
        isEntry = prefManager.GetBooleanData(ACCESS_LEVEL_ENTRY);
        isNormal = prefManager.GetBooleanData(ACCESS_LEVEL_NORMAL);
        isAdvanced = prefManager.GetBooleanData(ACCESS_LEVEL_ADVANCED);
        isModerator = prefManager.GetBooleanData(ACCESS_LEVEL_MODERATOR);
        isAdministrator = prefManager.GetBooleanData(ACCESS_LEVEL_ADMINISTRATOR);

        AppCompatButton btnTasks,btnCards,btnSchedule,btnMats,btnProportions,btnMcp,btnAcp;

        btnTasks = (AppCompatButton) findViewById(R.id.BTN_MAIN_TASKS);
        btnCards = (AppCompatButton) findViewById(R.id.BTN_MAIN_CARDS);
        btnSchedule = (AppCompatButton) findViewById(R.id.BTN_MAIN_SCHEDULE);
        btnProportions = (AppCompatButton) findViewById(R.id.BTN_MAIN_PROPORTIONS);
        btnMats = (AppCompatButton) findViewById(R.id.BTN_MAIN_MATERIALS);
        btnMcp = (AppCompatButton) findViewById(R.id.BTN_MAIN_MCP);
        btnAcp = (AppCompatButton) findViewById(R.id.BTN_MAIN_ACP);

        btnTasks.setVisibility(View.GONE);
        btnSchedule.setVisibility(View.GONE);
        btnCards.setVisibility(View.GONE);
        btnProportions.setVisibility(View.GONE);
        btnMats.setVisibility(View.GONE);
        btnMcp.setVisibility(View.GONE);
        btnAcp.setVisibility(View.GONE);
        btnTasks.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, FragmentTasks.class, null)
                    .commit();
        });
        btnCards.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, FragmentCards.class, null)
                    .commit();
        });
        btnSchedule.setOnClickListener(view -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, FragmentSchedule.class, null)
                .commit());
        btnProportions.setOnClickListener(view -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, FragmentProportions.class, null)
                .commit());
        btnMats.setOnClickListener(v -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, FragmentMaterials.class, null)
                .commit());
        btnMcp.setOnClickListener(view -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, FragmentModerator.class, null)
                .commit());
        btnAcp.setOnClickListener(view -> getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, FragmentAdministrator.class, null)
                .commit());

        String level = "";
        if(isEntry) {
            level = "Нов";
        }
        if (isNormal) {
            level = "Потребител";
            btnTasks.setVisibility(View.VISIBLE);
            btnSchedule.setVisibility(View.VISIBLE);
            btnCards.setVisibility(View.VISIBLE);
        }
        if (isAdvanced) {
            level = "Началник";
            btnTasks.setVisibility(View.VISIBLE);
            btnSchedule.setVisibility(View.VISIBLE);
            btnCards.setVisibility(View.VISIBLE);
            btnProportions.setVisibility(View.VISIBLE);
        }
        if (isModerator) {
            level = "Модератор";
            btnTasks.setVisibility(View.VISIBLE);
            btnSchedule.setVisibility(View.VISIBLE);
            btnCards.setVisibility(View.VISIBLE);
            btnProportions.setVisibility(View.VISIBLE);
            btnMats.setVisibility(View.VISIBLE);
            btnMcp.setVisibility(View.VISIBLE);
        }
        if (isAdministrator) {
            level = "Администратор";
            btnTasks.setVisibility(View.VISIBLE);
            btnSchedule.setVisibility(View.VISIBLE);
            btnCards.setVisibility(View.VISIBLE);
            btnProportions.setVisibility(View.VISIBLE);
            btnMats.setVisibility(View.VISIBLE);
            btnMcp.setVisibility(View.VISIBLE);
            btnAcp.setVisibility(View.VISIBLE);
        }

        TextView secondUserText = (TextView) findViewById(R.id.SECOND_USER_INFO_TEXT);
        secondUserText.setText(level);



    }

    private void test(String btn){
        Log.v("DEBUG","DEBUG-> " + btn);
    }
}
