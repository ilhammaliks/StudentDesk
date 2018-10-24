package com.project.zhimer.studentdesk.view.tabSurat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;

public class SuratLulus extends Fragment {

    View view;

    TextView tanggalLulus;


    public SuratLulus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_surat_lulus, container, false);

        tanggalLulus = (TextView) view.findViewById(R.id.tvTanggalLulus);

        tanggalLulus.setText("---");

        return view;
    }

}
