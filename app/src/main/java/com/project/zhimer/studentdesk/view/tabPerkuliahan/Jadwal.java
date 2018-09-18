package com.project.zhimer.studentdesk.view.tabPerkuliahan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.adapter.JadwalKuliahAdapter;
import com.project.zhimer.studentdesk.model.Kuliah;

import java.util.ArrayList;
import java.util.List;


public class Jadwal extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private JadwalKuliahAdapter adapter;
    private List<Kuliah> kuliahList = new ArrayList<>();

    public Jadwal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_perkuliahan_jadwal, container, false);


        adapter = new JadwalKuliahAdapter(kuliahList);

        recyclerView = view.findViewById(R.id.recyclerViewJadwalKuliah);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        DataJadwalKuliah();
        return view;
    }

    private void DataJadwalKuliah()
    {
        Kuliah jadwal = new Kuliah("IF15A", "Tugas Akhir", "6", "Senin, 07:00 - 12:00", "RIRI SAFITRI", "516");
        kuliahList.add(jadwal);

        jadwal = new Kuliah("IF15A", "Basis Data", "3", "Selasa, 07:00 - 12:00", "Endang Ripmiatin", "518");
        kuliahList.add(jadwal);

        jadwal = new Kuliah("IF15A", "Basis Data Lanjut", "3", "Rabu, 07:00 - 12:00", "Endang Ripmiatin", "512");
        kuliahList.add(jadwal);

        jadwal = new Kuliah("IF15A", "Applied Networking", "3", "Kamis, 07:00 - 12:00", "Pandri", "511A");
        kuliahList.add(jadwal);
    }
}
