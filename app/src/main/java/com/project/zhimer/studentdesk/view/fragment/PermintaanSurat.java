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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.view.tabSurat.HistorySurat;
import com.project.zhimer.studentdesk.view.tabSurat.SuratLulus;
import com.project.zhimer.studentdesk.view.tabSurat.SuratMagang;
import com.project.zhimer.studentdesk.view.tabSurat.SuratMahasiswa;
import com.project.zhimer.studentdesk.view.tabSurat.SuratPenelitian;

import java.util.ArrayList;
import java.util.List;


public class PermintaanSurat extends Fragment implements Spinner.OnItemSelectedListener {
    View view;

    TextView nim, prodi, nama, ttl, phone, email, alamat;

    Spinner spinner;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public PermintaanSurat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_permintaan_surat, container, false);

        nim = (TextView) view.findViewById(R.id.mahasiswa_nim);
        prodi = (TextView) view.findViewById(R.id.mahasiswa_prodi);
        nama = (TextView) view.findViewById(R.id.mahasiswa_nama);
        ttl = (TextView) view.findViewById(R.id.mahasiswa_ttl);
        phone = (TextView) view.findViewById(R.id.mahasiswa_phone);
        email = (TextView) view.findViewById(R.id.mahasiswa_email);
        alamat = (TextView) view.findViewById(R.id.mahasiswa_alamat);

        nim.setText("0102513010");
        prodi.setText("Teknik Informatika");
        nama.setText("Ilham Malik Muhammad");
        ttl.setText("Jakarta, 06 Oktober 1994");
        phone.setText("083895671999");
        email.setText("ilhammalikmuhammad@if.uai.ac.id");
        alamat.setText("Jl. Kepodang VIII K1/24 Rt/Rw 001/006 Rengas, Ciputat Timur, Tangerang Selatan");


        tabLayout = view.findViewById(R.id.tabs);
        viewPager = view.findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

        //spinner
        spinner = (Spinner) view.findViewById(R.id.bahasa);

        List<String> Bahasa = new ArrayList<String>();
        Bahasa.add("Bahasa Indonesia");
        Bahasa.add("English");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, Bahasa);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner.setAdapter(adapter);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new SuratMahasiswa(), "Mahasiswa");
        adapter.addFragment(new SuratPenelitian(), "Penelitian");
        adapter.addFragment(new SuratMagang(), "Magang");
        adapter.addFragment(new SuratLulus(), "Lulus");
        adapter.addFragment(new HistorySurat(), "History");

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
