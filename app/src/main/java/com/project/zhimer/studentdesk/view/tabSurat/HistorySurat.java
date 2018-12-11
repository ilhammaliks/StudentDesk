package com.project.zhimer.studentdesk.view.tabSurat;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.SuratHistoryAdapter;
import com.project.zhimer.studentdesk.model.Surat;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class HistorySurat extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private SuratHistoryAdapter mAdapter;
    private List<Surat> suratList = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;

    SessionManager sessionManager;

    ProgressView progressView;


    public HistorySurat() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.tab_surat_histori, container, false);


//        belom ada arraylist dari json
//        listSurat = new ArrayList<>();
//        adapter = new SuratHistoryAdapter(listSurat, getActivity());

        mAdapter = new SuratHistoryAdapter(suratList);

        recyclerView = view.findViewById(R.id.listSurat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);

        sessionManager = new SessionManager(getContext());

        progressView = view.findViewById(R.id.circular);

        progressView.setVisibility(View.VISIBLE);
        progressView.start();

        DataListSurat();


        return view;
    }

    private void DataListSurat() {
        String url = sessionManager.getUrl() + "";
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

                        //TODO nama parameter nya sesuaikan dengan yang ada pada json asli
                        String tanggalBuat = object.getString("tanggalBuat");
                        String jenisSurat = object.getString("jenis");
                        String tanggalSiap = object.getString("tanggalJadi");

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
            }
        });

    }

}
