package com.project.zhimer.studentdesk.view.tabNilai;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhimer.studentdesk.R;


public class Semester extends Fragment {


    public Semester() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tab_nilai_semester, container, false);
    }

}
