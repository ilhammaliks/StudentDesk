package com.project.zhimer.studentdesk.view.tabSurat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhimer.studentdesk.R;

public class SuratMagang extends Fragment {
    View view;


    public SuratMagang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_surat_magang, container, false);


        return view;
    }

}
