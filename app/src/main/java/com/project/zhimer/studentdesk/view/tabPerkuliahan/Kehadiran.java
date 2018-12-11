package com.project.zhimer.studentdesk.view.tabPerkuliahan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.model.Kuliah;

import java.util.ArrayList;

public class Kehadiran extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private RecyclerView.Adapter adapter;
    private ArrayList<Kuliah> listMatkul;

    SessionManager sessionManager;
    Kuliah kuliah;

    public Kehadiran() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_perkuliahan_kehadiran, container, false);

        listMatkul = new ArrayList<>();

//        adapter = new (listJadwal, getActivity());

        recyclerView = view.findViewById(R.id.rvKehadiranKuliah);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        sessionManager = new SessionManager(getContext());


        return view;
    }

    private void GetDataKehadiran() {

    }

}
