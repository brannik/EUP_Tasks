package Fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tasks.R;

import DataManager.SharedPrefManager;

public class FragmentMaterials extends Fragment {
    SharedPrefManager prefManager;
    public FragmentMaterials() {
        super(R.layout.fragment_materials_layout);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefManager = new SharedPrefManager(requireContext());


    }
}
