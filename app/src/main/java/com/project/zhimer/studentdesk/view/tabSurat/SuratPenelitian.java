package com.project.zhimer.studentdesk.view.tabSurat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.project.zhimer.studentdesk.R;

import java.util.ArrayList;
import java.util.List;

public class SuratPenelitian extends Fragment {

    View view;
    private Spinner spinKeperluan, spinBahasa;
    private EditText etKeterangan, etNamaPerusahaan, etDitujukan, etJabatan, etDivisi;
    private Button bSave;

    String bahasa, keperluan, keterangan, perusahaan, ditujukan, jabatan, divisi;


    public SuratPenelitian() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_surat_penelitian, container, false);

        spinBahasa = view.findViewById(R.id.bahasa);
        spinKeperluan = view.findViewById(R.id.penelitian);
        etKeterangan = view.findViewById(R.id.etKeterangan);
        etNamaPerusahaan = view.findViewById(R.id.etPerusahaan);
        etDitujukan = view.findViewById(R.id.etUntuk);
        etJabatan = view.findViewById(R.id.etJabatan);
        etDivisi = view.findViewById(R.id.etDivisi);
        bSave = view.findViewById(R.id.bSave);

        //spinnerBahasa
        List<String> Bahasa = new ArrayList<String>();
        Bahasa.add("Bahasa Indonesia");
        Bahasa.add("English");

        ArrayAdapter<String> adapterBahasa = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Bahasa);
        adapterBahasa.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinBahasa.setAdapter(adapterBahasa);

        //spinKeperluan
        final List<String> Keperluan = new ArrayList<String>();
        Keperluan.add("Penelitian Keperluan Skripsi");
        Keperluan.add("Penelitian Keperluan Mata Kuliah");

        ArrayAdapter<String> adapterKeperluan = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Keperluan);
        adapterKeperluan.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinKeperluan.setAdapter(adapterKeperluan);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean checkPerusahaan = false, checkDitujakn = false, checkJabatan = false, checkDivisi = false;

                bahasa = spinBahasa.toString();
                keperluan = spinKeperluan.toString();

                keterangan = etKeterangan.getText().toString();
                perusahaan = etNamaPerusahaan.getText().toString();
                ditujukan = etDitujukan.getText().toString();
                jabatan = etJabatan.getText().toString();
                divisi = etDivisi.getText().toString();

                //set condition if user do not input data
                if (perusahaan.isEmpty()) {
                    etNamaPerusahaan.setError("nama perusahaan tidak boleh kosong");
                } else {
                    checkPerusahaan = true;
                }

                if (ditujukan.isEmpty()) {
                    etDitujukan.setError("nama personel tidak boleh kosong");
                } else {
                    checkDitujakn = true;
                }

                if (jabatan.isEmpty()) {
                    etJabatan.setError("nama perusahaan tidak boleh kosong");
                } else {
                    checkJabatan = true;
                }

                if (divisi.isEmpty()) {
                    etDivisi.setError("nama perusahaan tidak boleh kosong");
                } else {
                    checkDivisi = true;
                }
                //End Condition


                if (checkPerusahaan && checkDitujakn && checkJabatan && checkDivisi) {

                }

            }
        });

        return view;
    }

}
