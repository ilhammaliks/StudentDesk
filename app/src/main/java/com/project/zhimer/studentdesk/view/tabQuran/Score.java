package com.project.zhimer.studentdesk.view.tabQuran;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.HasilQuranAdapter;
import com.project.zhimer.studentdesk.model.Quran;

import java.util.ArrayList;

public class Score extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Quran> listHasilTest;

    SessionManager sessionManager;


    public Score() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_quran_score, container, false);

        listHasilTest = new ArrayList<>();
        adapter = new HasilQuranAdapter(listHasilTest, getActivity());

        recyclerView = view.findViewById(R.id.recyclerViewScoreQuran);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        sessionManager = new SessionManager(getContext());

        return view;
    }

}
