package Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tasks.R;

import java.util.Random;

import DataManager.SharedPrefManager;

public class FragmentAdministrator extends Fragment {
    SharedPrefManager prefManager;
    public FragmentAdministrator() {
        super(R.layout.fragment_administrator_layout);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefManager = new SharedPrefManager(requireContext());


    }
}
