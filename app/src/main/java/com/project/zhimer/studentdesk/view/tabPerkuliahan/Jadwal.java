package com.project.zhimer.studentdesk.view.tabPerkuliahan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.JadwalKuliahAdapter;
import com.project.zhimer.studentdesk.model.Kuliah;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class Jadwal extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private RecyclerView.Adapter adapter;
    private ArrayList<Kuliah> listJadwal;

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

        sksTotal = view.findViewById(R.id.sksTotal);

        sessionManager = new SessionManager(getContext());

        DataJadwalKuliah();
        return view;
    }

    private void DataJadwalKuliah() {
        String url = "https://studentdesk.uai.ac.id/api/index.php/jadwal/JadwalKuliah/format/json";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.setBasicAuth("admin", "1234");
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

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        kuliah = new Kuliah();

                        Integer sksMatakuliah = object.getInt("mtkl_sks");
                        String kodeKelas = object.getString("nama");
                        String namaMatkul = object.getString("mtkl_nm");
                        String hari = object.getString("NamaHari");
                        String ruangan = object.getString("ruang");

                        kuliah.setKelas(kodeKelas);
                        kuliah.setMataKuliah(namaMatkul);
                        kuliah.setSks(sksMatakuliah);
                        kuliah.setHari(hari);
//                        kuliah.setDosen();
//                        kuliah.setMulai();
//                        kuliah.setKelar();
                        kuliah.setRuang(ruangan);

                        jumlahSks += sksMatakuliah;
                        kuliah.setJumlahSKS(jumlahSks);

                        listJadwal.add(kuliah);
                        adapter.notifyDataSetChanged();
                    }

                    total = String.valueOf(jumlahSks);
                    sksTotal.setText(total);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
