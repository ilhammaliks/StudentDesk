package com.project.zhimer.studentdesk.view.fragment;


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
import com.project.zhimer.studentdesk.adapter.KeuanganAdapter;
import com.project.zhimer.studentdesk.model.Tagihan;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Keuangan extends Fragment {
    View view;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Tagihan> listKeuangan;
    Tagihan tagihan;


    ProgressView progressView;

    SessionManager sessionManager;


    public Keuangan() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_keuangan, container, false);

        listKeuangan = new ArrayList<>();

        adapter = new KeuanganAdapter(listKeuangan, getActivity());

        recyclerView = view.findViewById(R.id.recyclerViewKeuangan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        progressView = view.findViewById(R.id.circular);

        sessionManager = new SessionManager(getContext());

        progressView.setVisibility(View.VISIBLE);
        progressView.start();
        GetDataKeuangan();

        return view;
    }

    private void GetDataKeuangan() {
        String url = sessionManager.getUrl() + "/keuangan/RekapKeuangan/format/json";
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

                        tagihan = new Tagihan();

                        String semester = object.getString("semester");
                        String biaya = object.getString("jumlah_biaya");
                        String potongan = object.getString("jumlah_potongan");
                        String bayar = object.getString("jumlah_bayar");
                        String status = object.getString("kurang_lebih_bayar");

                        tagihan.setSemester(semester);
                        tagihan.setBiaya(biaya);
                        tagihan.setPotongan(potongan);
                        tagihan.setBayar(bayar);
                        tagihan.setStatus(status);

                        listKeuangan.add(tagihan);
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
