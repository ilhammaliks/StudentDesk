package com.project.zhimer.studentdesk.view.tabSurat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.adapter.HistoriSuratAdapter;
import com.project.zhimer.studentdesk.model.Surat;

import java.util.ArrayList;
import java.util.List;


public class HistorySurat extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private HistoriSuratAdapter mAdapter;
    private List<Surat> suratList = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;

//    private RecyclerView.Adapter adapter;
//    private ArrayList<DaftarSurat> listSurat;


    public HistorySurat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.tab_surat_histori, container, false);



//        belom ada arraylist dari json
//        listSurat = new ArrayList<>();
//        adapter = new HistoriSuratAdapter(listSurat, getActivity());

        mAdapter = new HistoriSuratAdapter(suratList);

        recyclerView = view.findViewById(R.id.listSurat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        DataListSurat();


        return view;
    }

    private void DataListSurat()
    {
        Surat surat = new Surat("1 Desember 2018", "Magang\n(Bahasa Indonesia)", "8 Desember 2018");
        suratList.add(surat);

        surat = new Surat("1 November 2018", "Magang\n(Bahasa Indonesia)", "8 november 2018");
        suratList.add(surat);

        surat = new Surat("1 Oktober 2018", "Mahasiswa\n(Bahasa Indonesia)", "8 Oktober 2018");
        suratList.add(surat);

        surat = new Surat("10 September 2018", "Mahasiswa\n(Bahasa Indonesia)", "18 September 2018");
        suratList.add(surat);

        surat = new Surat("11 September 2018", "Surat PKL / Magang\n(Bahasa Indonesia)", "18 September 2018");
        suratList.add(surat);

        mAdapter.notifyDataSetChanged();
    }

}
