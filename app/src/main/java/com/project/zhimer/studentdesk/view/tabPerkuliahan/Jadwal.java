package com.project.zhimer.studentdesk.view.tabPerkuliahan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.JadwalKuliahAdapter;
import com.project.zhimer.studentdesk.model.Kuliah;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Jadwal extends Fragment {

    View view;
    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private ArrayList<Kuliah> listJadwal;

    private ProgressView progressView;

    SessionManager sessionManager;
    Kuliah kuliah;

    TextView sksTotal;

    public Jadwal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_perkuliahan_jadwal, container, false);

        listJadwal = new ArrayList<>();
        adapter = new JadwalKuliahAdapter(listJadwal, getActivity());

        recyclerView = view.findViewById(R.id.recyclerViewJadwalKuliah);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        progressView = view.findViewById(R.id.circular);

        sksTotal = view.findViewById(R.id.sksTotal);

        sessionManager = new SessionManager(getContext());

        progressView.setVisibility(View.VISIBLE);
        progressView.start();

        DataJadwalKuliah();

        return view;
    }

    private void DataJadwalKuliah() {
        String url = sessionManager.getUrl() + "/jadwal/JadwalKuliah/format/json";
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

                    int jumlahSks = 0;
                    String total = "";

                    String jamMulai = "";
                    String jamSelesai = "";

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        kuliah = new Kuliah();

                        Integer sksMatakuliah = object.getInt("mtkl_sks");
                        String kodeKelas = object.getString("nama");
                        String namaMatkul = object.getString("mtkl_nm");
                        String hari = object.getString("NamaHari");
                        String mulai = object.getString("slot_mulai");
                        String kelar = object.getString("slot_selesai");
                        String dosen = object.getString("DosenPengajar");
                        String ruangan = object.getString("ruang");

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

                        kuliah.setKelas(kodeKelas);
                        kuliah.setMataKuliah(namaMatkul);
                        kuliah.setSks(sksMatakuliah);
                        kuliah.setHari(hari + ", " + jamMulai + "-" + jamSelesai);
                        kuliah.setDosen(dosen);
                        kuliah.setRuang(ruangan);

                        jumlahSks += sksMatakuliah;
                        kuliah.setJumlahSKS(jumlahSks);

                        listJadwal.add(kuliah);
                        adapter.notifyDataSetChanged();
                    }
                    progressView.setVisibility(View.GONE);
                    progressView.stop();

                    total = String.valueOf(jumlahSks);
                    sksTotal.setText(total);
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
