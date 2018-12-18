package com.project.zhimer.studentdesk.view.tabNilai;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.project.zhimer.studentdesk.adapter.NilaiAktifAdapter;
import com.project.zhimer.studentdesk.model.Nilai;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class NilaiAktif extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private RecyclerView.Adapter adapter;
    private ArrayList<Nilai> listNilaiAktif;

    SessionManager sessionManager;
    Nilai nilai;

    TextView ips, totalSks;
    ProgressView progressView;


    public NilaiAktif() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.tab_nilai_aktif, container, false);

        listNilaiAktif = new ArrayList<>();
        adapter = new NilaiAktifAdapter(listNilaiAktif, getActivity());

        recyclerView = view.findViewById(R.id.recyclerViewNilaiAktif);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        ips = view.findViewById(R.id.ips);
        totalSks = view.findViewById(R.id.sksTotal);
        progressView = view.findViewById(R.id.circular);

        sessionManager = new SessionManager(getContext());

        progressView.setVisibility(View.VISIBLE);
        progressView.start();

        NilaiAktif();

        return view;
    }

    private void NilaiAktif() {
        String url = sessionManager.getUrl() + "/akademik/DaftarNilaiSemesterAktif/format/json";
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
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray jsonArray = object.getJSONArray("data");

                    int jumlahSks = 0;
                    String total = "";

                    //inisialisasi penjumlahan IPS
                    double jumlahBobot = 0;
                    double penjumlahanIPS;
                    String nilaiHuruf = "";
                    Integer nilaiAngka = null;
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");

                    //end inisialisasi penjumlahan IPS


                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objek = jsonArray.getJSONObject(i);
                        nilai = new Nilai();


                        String kodeMK = objek.getString("Kode_MK");
                        String namaMK = objek.getString("Nama_MK");
                        Integer sks = objek.getInt("SKS");
                        String huruf = objek.getString("Nilai_Huruf");
                        Boolean nilaiAngka2 = object.isNull("Nilai_Angka");


                        //kodisi
                        if (huruf.equals("null")) {
                            nilaiHuruf = "-";
                        }


                        if (nilaiAngka2 == true) {
                            nilaiAngka = 0;
                        } else {
                            nilaiAngka = object.getInt("Nilai_Angka");
                        }

                        Log.d("dataNilai", nilaiAngka + "");


                        nilai.setKodeMK(kodeMK);
                        nilai.setNamaMK(namaMK);
                        nilai.setSks(sks);
                        nilai.setHuruf(nilaiHuruf);
                        nilai.setAngka(nilaiAngka);

                        jumlahSks += sks;
                        total = String.valueOf(jumlahSks);

                        jumlahBobot += (sks * nilaiAngka);


                        listNilaiAktif.add(nilai);
                        adapter.notifyDataSetChanged();

                        progressView.stop();
                        progressView.setVisibility(View.GONE);
                    }

                    penjumlahanIPS = jumlahBobot / jumlahSks;
                    ips.setText(decimalFormat.format(penjumlahanIPS));

                    totalSks.setText(total);

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
