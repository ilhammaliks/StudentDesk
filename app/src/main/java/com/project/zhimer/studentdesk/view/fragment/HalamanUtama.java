package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.BeritaAdapter;
import com.project.zhimer.studentdesk.model.Berita;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import cz.msebera.android.httpclient.Header;


public class HalamanUtama extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    // adapter
    private RecyclerView.Adapter adapter;
    private ArrayList<Berita> beritaList;
    SessionManager sessionManager;

    public HalamanUtama() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_halaman_utama, container, false);

        // adapter
        beritaList = new ArrayList<>();

        adapter = new BeritaAdapter(beritaList, getActivity());

        //setup recycler
        recyclerView = view.findViewById(R.id.listBerita);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        sessionManager = new SessionManager(getContext());

        DataHalamanUtama();

        return view;
    }

    private void DataHalamanUtama()
    {
        String url = "https://studentdesk.uai.ac.id/rest/index.php/api/notifikasi/getNotifikasiByNIM/nim/"+ sessionManager.getNim() +"/format/json";
        AsyncHttpClient client = new AsyncHttpClient();
        client.setBasicAuth("admin", "1234");

        Log.d("datass", url + "");

        client.get(url, new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray jsonArray = object.getJSONArray("data");

                    Log.d("dataAPI", jsonArray.length()+"");

//                    for (int i = 0; i < jsonArray.length(); i++)
                    for (int i = jsonArray.length() -1; i >= 0; i--)
                    {
                        JSONObject exploreObject = jsonArray.getJSONObject(i);
                        Berita berita = new Berita();

                        String nilai = exploreObject.getString("nilai");



                        if (nilai.equals("UAI"))
                        {
                            String judul = exploreObject.getString("JudulNotifikasi");
                            String pengirim = exploreObject.getString("pengirim");
                            String tanggal = exploreObject.getString("TanggalBuat");
                            String isi = exploreObject.getString("IsiNotifikasi");

                            String isinotif = Html.fromHtml(isi).toString();

                            SimpleDateFormat plainDate = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
                            Date date = null;
                            try {
                                date = plainDate.parse(tanggal);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm:ss");
                            String waktu = dateFormat.format(date);

                            berita.setJudul(judul);
                            berita.setPengirim(pengirim);
                            berita.setTanggal(waktu);
                            berita.setIsinotif(isinotif);

                            beritaList.add(berita);

                            //tambah method notifyDataSetChanged() tujuannya setiap data di add adapternya bakal berubah
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
