package com.project.zhimer.studentdesk.view.tabNilai;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.adapter.NilaiSemesterAdapter;
import com.project.zhimer.studentdesk.model.Nilai;
import com.project.zhimer.studentdesk.model.SemesterChild;
import com.project.zhimer.studentdesk.model.SemesterGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class Semester extends Fragment {
    View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    private RecyclerView.Adapter adapter;
    private ArrayList<Nilai> listNilaiSemester;

    SessionManager sessionManager;
    Nilai nilai;

    List<SemesterGroup> semesterGroupList;
    List<SemesterChild> semesterChildList;

    ArrayList<String> dataTahun;
    ArrayList<String> dataSemester;

    Spinner spinnerTahun, spinnerSemester;
    ArrayAdapter<String> spinnerAdapter, spinnerAdapter2;
    TextView sks, ips;

    public Semester() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_nilai_semester_new, container, false);

        spinnerTahun = view.findViewById(R.id.spinnerTahun);
        spinnerSemester = view.findViewById(R.id.spinnerSemester);

        listNilaiSemester = new ArrayList<>();
        adapter = new NilaiSemesterAdapter(listNilaiSemester, getActivity());

        recyclerView = view.findViewById(R.id.listSemester);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        sks = view.findViewById(R.id.sksTotal);
        ips = view.findViewById(R.id.ips);

        sessionManager = new SessionManager(getContext());
        semesterGroupList = new ArrayList<>();

        //get data
        GetNilaiSemester();

        dataTahun = new ArrayList<>();
        dataSemester = new ArrayList<>();
        NilaiPersemester();

        return view;
    }

    private void GetNilaiSemester() {
        String url = sessionManager.getUrl() + "/akademik/daftarnilaipersemester/format/json";
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
                        String semester = object.getString("semester2");

                        semesterChildList = new ArrayList<>();

                        String kodeMk = object.getString("KodeMK");
                        String namaMk = object.getString("mtkl_nm");
                        Integer sks = object.getInt("mtkl_sks");
                        String nilaiHuruf = object.getString("HM");

                        SemesterChild semesterChild = new SemesterChild(kodeMk, namaMk, nilaiHuruf, sks);
                        semesterChildList.add(semesterChild);
                        semesterGroupList.add(new SemesterGroup(semester, semesterChildList));
                    }

                    Log.d("resultArray", semesterChildList.size() + " " + semesterGroupList.size());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void NilaiPersemester() {
        String url = sessionManager.getUrl() + "/akademik/daftarnilaipersemester/format/json";
        AsyncHttpClient client = new AsyncHttpClient();
        final RequestParams params = new RequestParams();

        client.setBasicAuth(sessionManager.getAuthUsername(), sessionManager.getAuthPassword());
        params.put("uname", sessionManager.getNim());
        params.put("pwd", sessionManager.getPassword());

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    final JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String tahunAjaran = object.getString("tahun_ajaran2");
                        String matkul = object.getString("NamaMK");

                        Log.d("naaa", matkul);

                        if (!dataTahun.contains(tahunAjaran)) {
                            dataTahun.add(tahunAjaran);
                        }
                       /* if (!dataSemester.contains(semester)) {
                            dataSemester.add(semester);
                        }*/
                    }

                    //todo spinner tahun ajaran
                    spinnerAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dataTahun);
                    spinnerTahun.setAdapter(spinnerAdapter);
                    spinnerTahun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                            getDataTahunAjaran(jsonArray, position);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

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

    private void getDataTahunAjaran(final JSONArray jsonArray, final int position) {
        dataSemester.clear();
        for (int i = 0; i < jsonArray.length(); i++) {
            Log.d("length", jsonArray.length() + "");
            try {
                JSONObject object = jsonArray.getJSONObject(i);

                String tahunAjaran = object.getString("tahun_ajaran2");
                String semester = object.getString("semester2");
                String matkul = object.getString("NamaMK");

                Log.d("naaaa", matkul);

                if (!dataSemester.contains(semester) && tahunAjaran.equals(dataTahun.get(position))) {
                    dataSemester.add(semester);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //todo spinner semester
        if (dataSemester.get(0).equals("3")){
            dataSemester.set(0, "Konversi");
            dataSemester.set(1, "Ganjil");
            dataSemester.set(2, "Genap");
        }
        if (dataSemester.get(0).equals("1")){
            if (dataSemester.size() == 1) {
                dataSemester.set(0, "Ganjil");
            } else {
                dataSemester.set(0, "Ganjil");
                dataSemester.set(1, "Genap");
            }
        }

        //todo
        Log.d("datasemes", dataSemester.toString());
        spinnerAdapter2 = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, dataSemester);
        spinnerSemester.setAdapter(spinnerAdapter2);
        spinnerSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int positionSemester, long id) {
                getDataSpecific(jsonArray, position, positionSemester);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getDataSpecific(JSONArray jsonArray, int tahunAjar, int semes) {
        listNilaiSemester.clear();
        int jumlahSKS = 0;
        String total = "";

        //inisialisasi penjumlahan IPS
        double jumlahBobot = 0;
        double penjumlahSKS = 0;
        double penjumlahanIPS;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        String nilaiHuruf = null;
        Integer nilaiAngka = null;

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                nilai = new Nilai();

                String tahunAjaran = object.getString("tahun_ajaran2");
                String semester = object.getString("semester2");

                String kodeMk = object.getString("KodeMK");
                String namaMk = object.getString("NamaMK");
                Integer sks = object.getInt("mtkl_sks");

                if (!object.getString("HM").equals("null")) {
                    nilaiHuruf = object.getString("HM");
                } else {
                    nilaiHuruf = "null";
                }

                if (!object.getString("HA").equals("null")) {
                    nilaiAngka = object.getInt("HA");
                } else {
                    nilaiAngka = 0;
                }

                if (tahunAjaran.equals(dataTahun.get(tahunAjar)) && semester.equals(dataSemester.get(semes))) {
                    //todo disini lognya untuk hasil final

                    Log.d("checkData", namaMk);

                    nilai.setKodeMK(kodeMk);
                    nilai.setNamaMK(namaMk);
                    nilai.setSks(sks);
                    nilai.setHuruf(nilaiHuruf);
                    nilai.setBobot(sks * nilaiAngka);

                    jumlahSKS += sks;
                    penjumlahSKS += sks;
                    total = String.valueOf(jumlahSKS);
                    jumlahBobot += (sks * nilaiAngka);

                    listNilaiSemester.add(nilai);
                    adapter.notifyDataSetChanged();

                    Log.d("namaMatkul", namaMk);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        sks.setText(total);

        //operasi penjumlahan ipk
        penjumlahanIPS = jumlahBobot / penjumlahSKS;
        ips.setText(decimalFormat.format(penjumlahanIPS));

    }
}


