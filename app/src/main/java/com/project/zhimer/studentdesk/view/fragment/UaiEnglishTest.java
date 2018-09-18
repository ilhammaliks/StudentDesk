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
import com.project.zhimer.studentdesk.adapter.UetAdapter;
import com.project.zhimer.studentdesk.model.UET;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class UaiEnglishTest extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private RecyclerView.Adapter adapter;
    private ArrayList<UET> uetList;



    public UaiEnglishTest() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_uai_english_test, container, false);


        uetList = new ArrayList<>();

        adapter = new UetAdapter(uetList, getActivity());

        recyclerView = view.findViewById(R.id.recyclerViewUET);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        gettingUET();
        return view;
    }

    private void gettingUET()
    {
        String url = "https://studentdesk.uai.ac.id/rest/index.php/api/notifikasi/getNotifikasiByNIM/nim/0102512008/format/json";
        AsyncHttpClient client = new AsyncHttpClient();
        client.setBasicAuth("admin", "1234");
        client.get(url, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray jsonArray = object.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        UET uet = new UET();

                        String nilai = jsonObject.getString("nilai");
                        if (nilai.equals("UAI"))
                        {
                            String tanggal = jsonObject.getString("TanggalBuat");
                            String pengirim = jsonObject.getString("pengirim");

                            uet.setTanggal(tanggal);
                            uet.setPengirim(pengirim);

                            uetList.add(uet);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }catch (JSONException e) {
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
