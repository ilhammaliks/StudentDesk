package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.rey.material.widget.ProgressView;


public class DaftarSidang extends Fragment {

    View view;
    SessionManager sessionManager;

    TextView mahasiswa_nama, mahasiswa_nim, mahasiswa_prodi, mahasiswa_telp, mahasiswa_phone,
            mahasiswa_alamat, mahasiswa_kota, mahasiswa_kode_pos, mahasiswa_dosen1, mahasiswa_dosen2,
            mahasiswa_judul;

    ProgressView progressView;


    public DaftarSidang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daftar_sidang, container, false);
    }

}
