package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhimer.studentdesk.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaftarWisuda extends Fragment {


    public DaftarWisuda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar_wisuda, container, false);
    }

}
