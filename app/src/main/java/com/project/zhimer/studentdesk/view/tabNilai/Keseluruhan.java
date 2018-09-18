package com.project.zhimer.studentdesk.view.tabNilai;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;


public class Keseluruhan extends Fragment {
    View view;
    private RecyclerView recyclerView;

    TextView ipk, totalSks, sksLulus, sksUlang, nilaiUet;


    public Keseluruhan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_nilai_keseluruhan, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewSeluruhNilai);

        ipk = view.findViewById(R.id.tvIpk);
        totalSks = view.findViewById(R.id.tvTotalSks);
        sksLulus = view.findViewById(R.id.tvSksLulus);
        sksUlang = view.findViewById(R.id.tvSksUlang);
        nilaiUet = view.findViewById(R.id.tvNilaiUet);

        return view;
    }

}
