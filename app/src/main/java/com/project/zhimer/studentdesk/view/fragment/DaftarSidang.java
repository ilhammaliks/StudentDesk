package com.project.zhimer.studentdesk.view.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.DaftarSidangAdapter;
import com.project.zhimer.studentdesk.model.SyaratSidang;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


public class DaftarSidang extends Fragment {

    View view;
    SessionManager sessionManager;

    //info sidang
    TextView tanggalSidang, jam, ruangan, ketuaSidang, penguji1, penguji2;

    //data skripsi
    TextView mahasiswa_nama, mahasiswa_nim, mahasiswa_prodi, pembimbing1, pembimbing2, judulSkripsi;

    LinearLayout directWebsite;


    ProgressView progressView;

    ArrayList<SyaratSidang> listSyaratSidang;
    DaftarSidangAdapter adapter;
    RecyclerView recyclerView;
    SyaratSidang syaratSidang;


    public DaftarSidang() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_daftar_sidang_new, container, false);

        progressView = view.findViewById(R.id.circular);

        //info sidang
        tanggalSidang = view.findViewById(R.id.tvTanggalsidang);
        jam = view.findViewById(R.id.tvJam);
        ruangan = view.findViewById(R.id.tvRuangan);
        ketuaSidang = view.findViewById(R.id.tvKetuaSidang);
        penguji1 = view.findViewById(R.id.tvPenguji1);
        penguji2 = view.findViewById(R.id.tvPenguji2);

        //biodata
        mahasiswa_nama = view.findViewById(R.id.mahasiswa_nama);
        mahasiswa_nim = view.findViewById(R.id.mahasiswa_nim);
        mahasiswa_prodi = view.findViewById(R.id.mahasiswa_prodi);
        pembimbing1 = view.findViewById(R.id.tvPembimbing1);
        pembimbing2 = view.findViewById(R.id.tvPembimbing2);
        judulSkripsi = view.findViewById(R.id.tvJudul);


        sessionManager = new SessionManager(getContext());

        progressView.setVisibility(View.VISIBLE);
        progressView.start();

        //syarat sidang
        recyclerView = view.findViewById(R.id.recyclerView);
        setRecyclerView(getActivity());

        GetDataMahasiswa();
        GetDataSkripsiMahasiswa();

        return view;
    }

    private void setRecyclerView(Activity activity) {

        listSyaratSidang = new ArrayList<>();
        adapter = new DaftarSidangAdapter(listSyaratSidang, activity);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

    }

    private void GetDataMahasiswa() {
        String url = sessionManager.getUrl() + "/biodata/LihatBiodata/format/json";
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
                        JSONObject object = jsonArray.getJSONObject(i);

                        String nama = object.getString("mhs_nm");
                        String nim = object.getString("mhs_nim");
                        String prodi = object.getString("NamaProgdi");

                        mahasiswa_nama.setText(nama);
                        mahasiswa_nim.setText(nim);
                        mahasiswa_prodi.setText(prodi);
                    }
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

    private void GetDataSkripsiMahasiswa() {
        String url = sessionManager.getUrl() + "/sidang/SyaratSidang/format/json";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.setBasicAuth(sessionManager.getAuthUsername(), sessionManager.getAuthPassword());
        params.put("uname", sessionManager.getNim());
        params.put("pwd", sessionManager.getPassword());
        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                progressView.stop();

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONObject objectData = jsonObject.getJSONObject("data");
                    JSONObject objectBio = objectData.getJSONObject("biodata");
                    JSONArray listSyarat = objectData.getJSONArray("syarat");

                    String pembimbingSatu = objectBio.getString("pembimbing1");
                    String pembimbingDua = objectBio.getString("pembimbing2");
                    String judul = objectBio.getString("judulskripsi");

                    if (!judul.equals("")) {

                    }

                    pembimbing1.setText(pembimbingSatu);

                    if (pembimbingDua.equalsIgnoreCase("null")) {
                        pembimbing2.setText("-");
                    } else {
                        pembimbing2.setText(pembimbingDua);
                    }

                    judulSkripsi.setText(judul);

                    //for rv syarat sidang
                    for (int i = 0; i < listSyarat.length(); i++) {

                        syaratSidang = new SyaratSidang();

                        JSONObject object = listSyarat.getJSONObject(i);
                        String syarat = object.getString("NamaSyarat");
                        String status = object.getString("status");

                        syaratSidang.setSyarat(syarat);
                        syaratSidang.setStatus(status);

                        listSyaratSidang.add(syaratSidang);
                        adapter.notifyDataSetChanged();
                    }
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
