package com.project.zhimer.studentdesk.view.tabSurat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.project.zhimer.studentdesk.R;

import java.util.ArrayList;
import java.util.List;

public class SuratMahasiswa extends Fragment {

    View view;

    private Spinner spinner;
    private EditText etKeterangan;
    private Button bSave;

    String keterangan;

    public SuratMahasiswa() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_surat_mahasiswa, container, false);

        spinner = view.findViewById(R.id.bahasa);
        etKeterangan = view.findViewById(R.id.etKeterangan);
        bSave = view.findViewById(R.id.bSave);

        //spinner
        spinner = view.findViewById(R.id.bahasa);

        List<String> Bahasa = new ArrayList<String>();
        Bahasa.add("Bahasa Indonesia");
        Bahasa.add("English");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Bahasa);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner.setAdapter(adapter);

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                keterangan = etKeterangan.getText().toString();


            }
        });


        return view;
    }
}
