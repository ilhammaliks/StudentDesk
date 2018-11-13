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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;
import com.project.zhimer.studentdesk.model.Nilai;
import com.project.zhimer.studentdesk.model.SemesterChild;
import com.project.zhimer.studentdesk.model.SemesterGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class Semester extends Fragment {
    View view;
    private RecyclerView listSemester;
    private LinearLayoutManager linearLayoutManager;

    List<SemesterGroup> semesterGroupList;
    List<SemesterChild> semesterChildList;

    SessionManager sessionManager;
    ArrayList<String> dataTahun;
    ArrayList<String> dataSemester;
    Nilai nilai;

    Spinner spinnerTahun, spinnerSemester;
    ArrayAdapter<String> spinnerAdapter, spinnerAdapter2;

    public Semester() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_nilai_semester, container, false);
        listSemester = view.findViewById(R.id.listSemester);
        spinnerTahun = view.findViewById(R.id.spinnerTahun);
        spinnerSemester = view.findViewById(R.id.spinnerSemester);

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
                        String semester = object.getString("semester2");

                        if (!dataTahun.contains(tahunAjaran)) {
                            dataTahun.add(tahunAjaran);
                        }
                        if (!dataSemester.contains(semester)) {
                            dataSemester.add(semester);
                        }
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

        for (int i = 0; i < jsonArray.length(); i++) {
            Log.d("length", jsonArray.length() + "");
            try {
                JSONObject object = jsonArray.getJSONObject(i);

                String tahunAjaran = object.getString("tahun_ajaran2");
                String semester = object.getString("semester2");

                if (!dataSemester.contains(semester) && tahunAjaran.equals(dataTahun.get(position))) {
                    dataSemester.add(semester);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //todo spinner semester
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
        for (int i = 0; i < jsonArray.length(); i++) {
            Log.d("length", jsonArray.length() + "");
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                nilai = new Nilai();

                String tahunAjaran = object.getString("tahun_ajaran2");
                String semester = object.getString("semester2");

                String kodeMk = object.getString("KodeMK");
                String namaMk = object.getString("NamaMK");
                Integer sks = object.getInt("mtkl_sks");
                String nilaiHuruf = object.getString("HM");
                Integer nilaiAngka = object.getInt("HA");




                if (tahunAjaran.equals(dataTahun.get(tahunAjar)) && semester.equals(dataSemester.get(semes))) {
                    //todo cek disini lognya untuk hasil final

                    Log.d("namaMatkul", namaMk);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}


