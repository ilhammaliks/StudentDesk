package com.project.zhimer.studentdesk.view.tabSemesterPendek;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhimer.studentdesk.R;

public class JadwalSp extends Fragment {
    View view;


    public JadwalSp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_sp_jadwal, container, false);


        return view;
    }

}
