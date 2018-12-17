package com.project.zhimer.studentdesk.view.tabPerkuliahan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.JadwalPenggantiAdapter;
import com.project.zhimer.studentdesk.model.Kuliah;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class Pengganti extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private ArrayList<Kuliah> listKuliahPengganti;

    SessionManager sessionManager;
    Kuliah kuliah;

    ProgressView progressView;


    public Pengganti() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_perkuliahan_pengganti, container, false);

        listKuliahPengganti = new ArrayList<>();
        adapter = new JadwalPenggantiAdapter(listKuliahPengganti, getActivity());

        recyclerView = view.findViewById(R.id.rvKuliahPengganti);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        progressView = view.findViewById(R.id.circular);

        sessionManager = new SessionManager(getContext());

        progressView.setVisibility(View.VISIBLE);
        progressView.start();

        GetjadwalPengganti();

        return view;
    }

    private void GetjadwalPengganti() {
        String url = sessionManager.getUrl() + "/jadwal/JadwalKuliahPengganti/format/json";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.setBasicAuth(sessionManager.getAuthUsername(), sessionManager.getAuthPassword());
        params.put("uname", sessionManager.getNim());
        params.put("pwd", sessionManager.getPassword());

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objek = jsonArray.getJSONObject(i);
                        kuliah = new Kuliah();

                        String jamMulai = "";
                        String jamSelesai = "";

                        String jamMulaiPengganti = "";
                        String jamKelarPengganti = "";

                        String waktpengganti = "";

                        String namaMk = objek.getString("mtkl_nm");
                        String dosen = objek.getString("ds_nm");
                        String ruang = objek.getString("ruang");


                        String tanggalBerhalangan = objek.getString("tanggal_tidak_hadir");
                        String mulai = objek.getString("slot_mulai");
                        String kelar = objek.getString("slot_selesai");

                        SimpleDateFormat plainDate = new SimpleDateFormat("yyyy-M-d");
                        Date halangan = null;
                        try {
                            halangan = plainDate.parse(tanggalBerhalangan);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
                        String waktuBerhalangan = dateFormat.format(halangan);
//
                        String tanggalPengganti = objek.getString("tanggal_pengganti");
                        String mulaiPengganti = objek.getString("slot_mulai_pengganti");
                        String kelarPengganti = objek.getString("slot_selesai_pengganti");

                        if (tanggalPengganti.equals("")) {
                            Date ganti = null;

                            try {
                                ganti = plainDate.parse(tanggalPengganti);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            waktpengganti = dateFormat.format(ganti);
                        }

                        //jam mulai perkuliahan
                        if (mulai.equals("1")) {
                            jamMulai = "07.00";
                        }
                        if (mulai.equals("2")) {
                            jamMulai = "07.50";
                        }
                        if (mulai.equals("3")) {
                            jamMulai = "08.40";
                        }
                        if (mulai.equals("4")) {
                            jamMulai = "09.30";
                        }
                        if (mulai.equals("5")) {
                            jamMulai = "10.20";
                        }
                        if (mulai.equals("6")) {
                            jamMulai = "11.00";
                        }
                        if (mulai.equals("7")) {
                            jamMulai = "12.50";
                        }
                        if (mulai.equals("8")) {
                            jamMulai = "13.40";
                        }
                        if (mulai.equals("9")) {
                            jamMulai = "14.30";
                        }
                        if (mulai.equals("10")) {
                            jamMulai = "15.30";
                        }
                        if (mulai.equals("11")) {
                            jamMulai = "16.20";
                        }
                        if (mulai.equals("12")) {
                            jamMulai = "17.10";
                        }
                        if (mulai.equals("13")) {
                            jamMulai = "18.30";
                        }
                        if (mulai.equals("14")) {
                            jamMulai = "19.30";
                        }
                        if (mulai.equals("15")) {
                            jamMulai = "20.20";
                        }

                        //jam selesai perkuliahan
                        if (kelar.equals("1")) {
                            jamSelesai = "07.50";
                        }
                        if (kelar.equals("2")) {
                            jamSelesai = "08.40";
                        }
                        if (kelar.equals("3")) {
                            jamSelesai = "09.30";
                        }
                        if (kelar.equals("4")) {
                            jamSelesai = "10.20";
                        }
                        if (kelar.equals("5")) {
                            jamSelesai = "11.10";
                        }
                        if (kelar.equals("6")) {
                            jamSelesai = "12.00";
                        }
                        if (kelar.equals("7")) {
                            jamSelesai = "13.40";
                        }
                        if (kelar.equals("8")) {
                            jamSelesai = "14.30";
                        }
                        if (kelar.equals("9")) {
                            jamSelesai = "15.20";
                        }
                        if (kelar.equals("10")) {
                            jamSelesai = "16.20";
                        }
                        if (kelar.equals("11")) {
                            jamSelesai = "17.10";
                        }
                        if (kelar.equals("12")) {
                            jamSelesai = "18.00";
                        }
                        if (kelar.equals("13")) {
                            jamSelesai = "19.20";
                        }
                        if (kelar.equals("14")) {
                            jamSelesai = "20.20";
                        }
                        if (kelar.equals("15")) {
                            jamSelesai = "21.10";
                        }


                        //jam mulai perkuliahan pengganti
                        if (mulaiPengganti.equals("1")) {
                            jamMulaiPengganti = "07.00";
                        }
                        if (mulaiPengganti.equals("2")) {
                            jamMulaiPengganti = "07.50";
                        }
                        if (mulaiPengganti.equals("3")) {
                            jamMulaiPengganti = "08.40";
                        }
                        if (mulaiPengganti.equals("4")) {
                            jamMulaiPengganti = "09.30";
                        }
                        if (mulaiPengganti.equals("5")) {
                            jamMulaiPengganti = "10.20";
                        }
                        if (mulaiPengganti.equals("6")) {
                            jamMulaiPengganti = "11.00";
                        }
                        if (mulaiPengganti.equals("7")) {
                            jamMulaiPengganti = "12.50";
                        }
                        if (mulaiPengganti.equals("8")) {
                            jamMulaiPengganti = "13.40";
                        }
                        if (mulaiPengganti.equals("9")) {
                            jamMulaiPengganti = "14.30";
                        }
                        if (mulaiPengganti.equals("10")) {
                            jamMulaiPengganti = "15.30";
                        }
                        if (mulaiPengganti.equals("11")) {
                            jamMulaiPengganti = "16.20";
                        }
                        if (mulaiPengganti.equals("12")) {
                            jamMulaiPengganti = "17.10";
                        }
                        if (mulaiPengganti.equals("13")) {
                            jamMulaiPengganti = "18.30";
                        }
                        if (mulaiPengganti.equals("14")) {
                            jamMulaiPengganti = "19.30";
                        }
                        if (mulaiPengganti.equals("15")) {
                            jamMulaiPengganti = "20.20";
                        }

                        //jam selesai perkuliahan pengganti
                        if (kelarPengganti.equals("1")) {
                            jamKelarPengganti = "07.50";
                        }
                        if (kelarPengganti.equals("2")) {
                            jamKelarPengganti = "08.40";
                        }
                        if (kelarPengganti.equals("3")) {
                            jamKelarPengganti = "09.30";
                        }
                        if (kelarPengganti.equals("4")) {
                            jamKelarPengganti = "10.20";
                        }
                        if (kelarPengganti.equals("5")) {
                            jamKelarPengganti = "11.10";
                        }
                        if (kelarPengganti.equals("6")) {
                            jamKelarPengganti = "12.00";
                        }
                        if (kelarPengganti.equals("7")) {
                            jamKelarPengganti = "13.40";
                        }
                        if (kelarPengganti.equals("8")) {
                            jamKelarPengganti = "14.30";
                        }
                        if (kelarPengganti.equals("9")) {
                            jamKelarPengganti = "15.20";
                        }
                        if (kelarPengganti.equals("10")) {
                            jamKelarPengganti = "16.20";
                        }
                        if (kelarPengganti.equals("11")) {
                            jamKelarPengganti = "17.10";
                        }
                        if (kelarPengganti.equals("12")) {
                            jamKelarPengganti = "18.00";
                        }
                        if (kelarPengganti.equals("13")) {
                            jamKelarPengganti = "19.20";
                        }
                        if (kelarPengganti.equals("14")) {
                            jamKelarPengganti = "20.20";
                        }
                        if (kelarPengganti.equals("15")) {
                            jamKelarPengganti = "21.10";
                        }

                        kuliah.setMataKuliah(namaMk);
                        kuliah.setDosen(dosen);
                        kuliah.setTanggalBerhalangan(waktuBerhalangan + ", " + jamMulai + "-" + jamSelesai);

                        if (tanggalPengganti == "null") {
                            kuliah.setTanggalPengganti("null, -");
                        } else {
                            kuliah.setTanggalPengganti(waktpengganti + ", " + jamMulaiPengganti + "-" + jamKelarPengganti);
                        }
                        kuliah.setRuang(ruang);

                        listKuliahPengganti.add(kuliah);
                        adapter.notifyDataSetChanged();
                    }
                    progressView.setVisibility(View.GONE);
                    progressView.stop();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);

                Toast.makeText(getContext(), "Koneksi internet anda bermasalah\nSilahkan coba beberapa saat lagi", Toast.LENGTH_LONG).show();
            }
        });
    }

}