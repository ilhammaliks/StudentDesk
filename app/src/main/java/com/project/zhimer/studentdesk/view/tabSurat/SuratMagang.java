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
import android.widget.Toast;

import com.project.zhimer.studentdesk.R;

import java.util.ArrayList;
import java.util.List;

public class SuratMagang extends Fragment {
    View view;

    private Spinner spinner;
    private EditText etKeterangan, etPerusahaan, etDitujukan, etJabatan, etDivisi;
    private Button bSave;

    String bahasa, keterangan, perusahaan, ditujukan, jabatan, divisi;

    public SuratMagang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_surat_magang, container, false);

        spinner = view.findViewById(R.id.bahasa);
        etKeterangan = view.findViewById(R.id.etKeterangan);
        etPerusahaan = view.findViewById(R.id.etPerusahaan);
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

        spinner.setAdapter(adapterBahasa);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean checkPerusahaan = false, checkDitujakn = false, checkJabatan = false, checkDivisi = false;


                bahasa = spinner.toString();

                keterangan = etKeterangan.getText().toString();
                perusahaan = etPerusahaan.getText().toString();
                ditujukan = etDitujukan.getText().toString();
                jabatan = etJabatan.getText().toString();
                divisi = etDivisi.getText().toString();

                //set condition if user do not input data
                if (perusahaan.isEmpty()) {
                    etPerusahaan.setError("nama perusahaan tidak boleh kosong");
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


                    Toast.makeText(getContext(),"Surat berhasil dibuat", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
