package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.view.tabPerkuliahan.Jadwal;
import com.project.zhimer.studentdesk.view.tabPerkuliahan.Kehadiran;
import com.project.zhimer.studentdesk.view.tabPerkuliahan.Pengganti;

import java.util.ArrayList;
import java.util.List;


public class Perkuliahan extends Fragment {

    View view;
    private TabLayout tabLayout;
    private ViewPager pager;


    public Perkuliahan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_perkuliahan, container, false);

        tabLayout = view.findViewById(R.id.tabs);
        pager = view.findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(pager);
        setupViewPager(pager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new Jadwal(), "Jadwal Kuliah");
        adapter.addFragment(new Kehadiran(), "Kehadiran");
        adapter.addFragment(new Pengganti(), "Jadwal Pengganti");
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> Fragment = new ArrayList<>();
        private List<String> PageTitle = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        private void addFragment(Fragment fragment, String title) {
            Fragment.add(fragment);
            PageTitle.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return PageTitle.get(position);
        }

        @Override
        public int getCount() {
            return PageTitle.size();
        }
    }

}
