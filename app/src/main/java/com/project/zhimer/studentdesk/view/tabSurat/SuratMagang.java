package com.project.zhimer.studentdesk.view.tabSurat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.project.zhimer.studentdesk.R;

public class SuratMagang extends Fragment {
    View view;

    private EditText keterangan, perusahaan, ditujukan, jabatan, divisi;
    private Button save;

    public SuratMagang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_surat_magang, container, false);

        keterangan = view.findViewById(R.id.etKeterangan);
        perusahaan = view.findViewById(R.id.etPerusahaan);
        ditujukan = view.findViewById(R.id.etUntuk);
        jabatan = view.findViewById(R.id.etJabatan);
        divisi = view.findViewById(R.id.etDivisi);

        save = view.findViewById(R.id.bSave);

        return view;
    }

}
