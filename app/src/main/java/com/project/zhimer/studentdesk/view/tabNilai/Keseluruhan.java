package com.project.zhimer.studentdesk.view.tabNilai;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.NilaiKeseluruhanAdapter;
import com.project.zhimer.studentdesk.model.Nilai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class Keseluruhan extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private RecyclerView.Adapter adapter;
    private ArrayList<Nilai> listSeluruhNilai;
    SessionManager sessionManager;
    Nilai nilai;

    TextView ipk, totalSks, sksLulus, sksUlang, nilaiUet;


    public Keseluruhan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_nilai_keseluruhan, container, false);

        listSeluruhNilai = new ArrayList<>();
        adapter = new NilaiKeseluruhanAdapter(listSeluruhNilai, getActivity());

        recyclerView = view.findViewById(R.id.recyclerViewSeluruhNilai);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        sessionManager = new SessionManager(getContext());
        NilaiKeseluruhan();

        ipk = view.findViewById(R.id.tvIpk);
        sksLulus = view.findViewById(R.id.tvSksLulus);
        sksUlang = view.findViewById(R.id.tvSksUlang);
        nilaiUet = view.findViewById(R.id.tvNilaiUet);


        return view;
    }

    private void NilaiKeseluruhan() {
        String url = "https://studentdesk.uai.ac.id/api/index.php/akademik/daftarnilaikeseluruhan/format/json";
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
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray jsonArray = object.getJSONArray("data");

                    Log.d("jumlah", jsonArray.length() + "");

                    int jumlahSks = 0;

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objek = jsonArray.getJSONObject(i);
                        nilai = new Nilai();

                        String kodeMK = objek.getString("mtkl_kd");
                        String namaMK = objek.getString("mtkl_nm");
                        Integer sks = objek.getInt("mtkl_sks");
                        String huruf = objek.getString("HM");
                        Integer angka = objek.getInt("HA");

                        nilai.setKodeMK(kodeMK);
                        nilai.setNamaMK(namaMK);
                        nilai.setSks(sks);
                        nilai.setHuruf(huruf);
                        nilai.setAngka(angka);
                        nilai.setBobot(sks * angka);
                        nilai.setSksTotal(jumlahSks += sks);

                        listSeluruhNilai.add(nilai);

                        adapter.notifyDataSetChanged();
                    }

                    totalSks = view.findViewById(R.id.tvTotalSks);
                    totalSks.setText(jumlahSks);

                    Log.d("jumlahsks", jumlahSks+"");
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
