package com.example.tasks.Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tasks.R;

import java.util.Random;

import com.example.tasks.DataManager.SharedPrefManager;

public class FragmentTasks extends Fragment {
    SharedPrefManager prefManager;
    public FragmentTasks() {
        super(R.layout.fragment_tasks_layout);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefManager = new SharedPrefManager(requireContext());
        TextView txt = (TextView) view.findViewById(R.id.TEXT);
        TextView txtTop = (TextView) view.findViewById(R.id.HD);
        Button btn = (Button) view.findViewById(R.id.TEST_BUTTON);
        String position = prefManager.GetStringData(SharedPrefManager.STRING_FIELD_WORK_POSITION);
        txtTop.setText("Fragment: Задачи за: " + position);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                int i1 = (r.nextInt(80) + 65);
                txt.setText("Random number after button pressed: " + i1);
            }
        });

    }
}
