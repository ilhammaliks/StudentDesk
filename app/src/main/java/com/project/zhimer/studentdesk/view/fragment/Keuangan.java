package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.adapter.KeuanganAdapter;
import com.project.zhimer.studentdesk.model.Tagihan;

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

        dataKeuangan();

        return view;
    }

    private void dataKeuangan() {
        String url = "https://studentdesk.uai.ac.id/rest/index.php/api/notifikasi/getNotifikasiByNIM/nim/0102513010/format/json";
        AsyncHttpClient client = new AsyncHttpClient();
        client.setBasicAuth("admin", "1234");
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = jsonArray.length() - 1; i >= 0; i--) {
                        JSONObject object = jsonArray.getJSONObject(i);

                        Tagihan tagihan = new Tagihan();

                        String nilai = object.getString("nilai");
                        Log.d("urutan", object.get("pengirim") + "");
                        if (nilai.equals("UAI")) {
                            String semester = object.getString("pengirim");
                            String biaya = object.getString("pengirim");
                            String potongan = object.getString("pengirim");
                            String bayar = object.getString("pengirim");
                            String status = object.getString("pengirim");

                            tagihan.setSemester(semester);
                            tagihan.setBiaya(biaya);
                            tagihan.setPotongan(potongan);
                            tagihan.setBayar(bayar);
                            tagihan.setStatus(status);

                            listKeuangan.add(tagihan);
                            adapter.notifyDataSetChanged();
                        }
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
