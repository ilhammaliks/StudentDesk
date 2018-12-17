package com.project.zhimer.studentdesk.view.tabPerkuliahan;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.JadwalKehadiranAdapter;
import com.project.zhimer.studentdesk.model.Kuliah;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Kehadiran extends Fragment {

    View view;
    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private ArrayList<Kuliah> listKehadiran;

    private ProgressView progressView;

    SessionManager sessionManager;
    Kuliah kuliah;

    public Kehadiran() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_perkuliahan_kehadiran, container, false);

        listKehadiran = new ArrayList<>();
        adapter = new JadwalKehadiranAdapter(listKehadiran, getActivity());

        recyclerView = view.findViewById(R.id.rvKehadiranKuliah);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        progressView = view.findViewById(R.id.circular);

        sessionManager = new SessionManager(getContext());
        progressView.setVisibility(View.VISIBLE);
        progressView.start();

        GetDataKehadiran();

        return view;
    }

    private void GetDataKehadiran() {
        String url = sessionManager.getUrl() + "/jadwal/KehadiranKuliah/format/json";
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

                    double persentase;
                    double jumlahkehadiran = 0;
                    double totalpertemuan = 0;
                    DecimalFormat decimalFormat = new DecimalFormat("#");
                    String hasilPersen = "";

                    for (int i = 0; i <jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        kuliah = new Kuliah();

                        String namaMk = object.getString("mtkl_nm");
                        Integer sks = object.getInt("mtkl_sks");
                        Integer hadir = object.getInt("hadir");
                        Integer sakit = object.getInt("sakit");
                        Integer izin = object.getInt("ijin");
                        Integer alpa = object.getInt("alpa");

                        //hitung jumlah pertemuan
                        totalpertemuan = hadir + sakit + izin + alpa;
                        //end

                        //hitung jumlah kehadiran
                        jumlahkehadiran = hadir + sakit + izin;
                        //end

                        //hitung persentase kehadiran
                        persentase = (jumlahkehadiran / totalpertemuan) * 100;
                        Log.d("dataPrese", persentase+"");
                        //end

                        hasilPersen = decimalFormat.format(persentase);

                        kuliah.setMataKuliah(namaMk);
                        kuliah.setSks(sks);
                        kuliah.setHadir(hadir);
                        kuliah.setSakit(sakit);
                        kuliah.setIzin(izin);
                        kuliah.setAlpa(alpa);
                        kuliah.setPersentase(hasilPersen);

                        listKehadiran.add(kuliah);
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
