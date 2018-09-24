package com.project.zhimer.studentdesk.view.tabSurat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.project.zhimer.studentdesk.R;

import java.util.ArrayList;
import java.util.List;

public class SuratPenelitian extends Fragment {

    View view;
    Spinner spinner;


    public SuratPenelitian() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_surat_penelitian, container, false);

        //spinner
        spinner = (Spinner) view.findViewById(R.id.keperluan);

        List<String> keperluan = new ArrayList<String>();
        keperluan.add("Penelitian Keperluan Skripsi");
        keperluan.add("Penelitian Keperluan Mata Kuliah");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, keperluan);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner.setAdapter(adapter);

        return view;
    }

}
