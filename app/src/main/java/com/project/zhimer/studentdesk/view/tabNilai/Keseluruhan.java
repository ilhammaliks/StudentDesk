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

import java.text.DecimalFormat;
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

        ipk = view.findViewById(R.id.tvIpk);
        sksLulus = view.findViewById(R.id.tvSksLulus);
        sksUlang = view.findViewById(R.id.tvSksUlang);
        nilaiUet = view.findViewById(R.id.tvNilaiUet);
        totalSks = view.findViewById(R.id.tvTotalSks);

        NilaiKeseluruhan();

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
                    String total = "";

                    //inisialisasi penjumlahan IPk
                    double jumlahBobot = 0;
                    double penjumlahSKS = 0;
                    double penjumlahanIPK;
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");

                    //end inisialisasi penjumlahan IPK

                    int sksABC = 0;
                    String totalLulus = "";

                    int sksDE = 0;
                    String totalUlang = "";

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

                        jumlahSks += sks;
                        penjumlahSKS += sks;
                        total = String.valueOf(jumlahSks);

                        jumlahBobot += (sks * angka);

                        if (huruf.equals("A") || huruf.equals("B") || huruf.equals("C")) {
                            sksABC += sks;
                            totalLulus = String.valueOf(sksABC);
                        } else {
                            if (huruf.equals("D") || huruf.equals("E"))
                            {
                                sksDE += sks;
                                totalUlang = String.valueOf(sksDE);
                            }
                        }

                        listSeluruhNilai.add(nilai);

                        adapter.notifyDataSetChanged();
                    }
                    //TODO Set Text gabisa di masukin value int, jadi harus di convert ke String dulu

                    //operasi penjumlahan ipk
                    penjumlahanIPK = jumlahBobot / penjumlahSKS;
                    ipk.setText(decimalFormat.format(penjumlahanIPK));

                    totalSks.setText(total);

                    sksLulus.setText(totalLulus);

                    if (sksDE == 0)
                    {
                        sksUlang.setText("0");
                    }
                    else{
                        sksUlang.setText(totalUlang);
                    }

                    nilaiUet.setText("490");
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
