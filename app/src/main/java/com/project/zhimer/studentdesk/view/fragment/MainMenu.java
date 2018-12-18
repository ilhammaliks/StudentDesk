package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.adapter.MenuAdapter;
import com.project.zhimer.studentdesk.model.Menu;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private List<Menu> menuList;
    private RecyclerView.Adapter adapter;


    public MainMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        menuList = new ArrayList<>();
        menuList.add(new Menu("Pengumuman", R.drawable.menu_biodata));
        menuList.add(new Menu("Biodata", R.drawable.menu_biodata));
        menuList.add(new Menu("Test Qur'an", R.drawable.menu_quran));
        menuList.add(new Menu("UET", R.drawable.menu_uet));
        menuList.add(new Menu("Ringkasan\nAkademik", R.drawable.menu_ringkasan));
        menuList.add(new Menu("Keuangan", R.drawable.menu_keuangan));
        menuList.add(new Menu("Perkuliahan", R.drawable.menu_perkuliahan));
        menuList.add(new Menu("Nilai", R.drawable.menu_nilai));

        adapter = new MenuAdapter(getContext(), menuList);

        recyclerView = view.findViewById(R.id.recyclerViewMenu);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setAdapter(adapter);




        return view;
    }

}
