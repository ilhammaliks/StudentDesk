package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;

public class RingkasanAkademik extends Fragment {

    View view;
    TextView nama, nim, prodi, pa, jalur, status, jumlahSks, ipk, a, b, c, d, e;
    private RecyclerView recyclerView;


    public RingkasanAkademik() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ringkasan_akademik, container, false);

        nama = view.findViewById(R.id.mahasiswa_nama);
        nim = view.findViewById(R.id.mahasiswa_nim);
        prodi = view.findViewById(R.id.mahasiswa_prodi);
        pa = view.findViewById(R.id.mahasiswa_dosen);
        jalur = view.findViewById(R.id.mahasiswa_jalur);
        status = view.findViewById(R.id.mahasiswa_status);
        jumlahSks = view.findViewById(R.id.mahasiswa_sks);
        ipk = view.findViewById(R.id.mahasiswa_ipk);
        a = view.findViewById(R.id.jumlahA);
        b = view.findViewById(R.id.jumlahB);
        c = view.findViewById(R.id.jumlahC);
        d = view.findViewById(R.id.jumlahD);
        e = view.findViewById(R.id.jumlahE);

        recyclerView = view.findViewById(R.id.recyclerView);

        return view;
    }

}
