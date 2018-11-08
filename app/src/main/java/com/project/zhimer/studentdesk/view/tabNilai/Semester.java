package com.project.zhimer.studentdesk.view.tabNilai;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

    public Semester() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.tab_nilai_semester, container, false);
        listSemester = view.findViewById(R.id.listSemester);

        sessionManager = new SessionManager(getContext());

        semesterGroupList = new ArrayList<>();

        //get data
        GetNilaiSemester();

//        dataTahun = new ArrayList<>();
//        NilaiPersemester();

        return view;
    }

    private void GetNilaiSemester() {
        String url = sessionManager.getUrl() + "/akademik/daftarnilaipersemester/format/json";
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
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String semester = object.getString("semester2");

                        String kodeMk = object.getString("KodeMK");
                        String namaMk = object.getString("mtkl_nm");
                        Integer sks = object.getInt("mtkl_sks");
                        String nilaiHuruf = object.getString("HM");

                        SemesterChild semesterChild = new SemesterChild(kodeMk, namaMk,nilaiHuruf, sks);
                        semesterChildList.add(semesterChild);
                        semesterGroupList.add(new SemesterGroup(semester,semesterChildList));
                    }

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
        RequestParams params = new RequestParams();

        client.setBasicAuth("admin", "1234");
        params.put("uname", sessionManager.getNim());
        params.put("pwd", sessionManager.getPassword());

        client.post(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
/*

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String tahunAjaran = object.getString("tahun_ajaran2");

                        if (!dataTahun.contains(tahunAjaran)) {
                            dataTahun.add(tahunAjaran);
                        }
                    }

                    for (int j = 0; j < dataTahun.size(); j++) {

                        Log.d("DataTahunAjaran", dataTahun.get(j));
                    }

                    getDataTahunAjaran(jsonArray); //get data tahun ajaran
*/

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

    private void getDataTahunAjaran(JSONArray jsonArray) {

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject object = jsonArray.getJSONObject(i);
                String tahunAjaran = object.getString("tahun_ajaran2");
                Integer semester = object.getInt("semester2");
                String dataMatkul = object.getString("NamaMK");

                Integer k = 0;
                if (tahunAjaran.equals(dataTahun.get(k))) {
                    if (semester == 1) {
                        Log.d("matkul", semester + ", " + dataMatkul);
                    }

                    if (semester == 2) {
                        Log.d("matkul", semester + ", " + dataMatkul);
                    }

                    if (semester == 3) {
                        Log.d("matkul", semester + ", " + dataMatkul);
                    }
                    k++;
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void getDataTahunAjaran1(JSONArray jsonArray) throws JSONException {

        Integer x = 0;

        for (int j = 0; j < jsonArray.length(); j++) {
            JSONObject object = jsonArray.getJSONObject(j);
            Integer semester = object.getInt("semester2");
            String tahunAjaran = object.getString("tahun_ajaran2");
            if (tahunAjaran.equals(dataTahun.get(0))) {
                if (semester == 1) {
                    x++;
                }
            }
        }

        String[] semester1 = new String[x];

        for (int i = 0; i < jsonArray.length(); i++) {

            try {

                int bobot = 0;

                JSONObject object = jsonArray.getJSONObject(i);
                String tahunAjaran = object.getString("tahun_ajaran2");

                Integer semester = object.getInt("semester2");
                String dataMatkul = object.getString("NamaMK");
                Integer sks = object.getInt("mtkl_sks");
                String nilaiHuruf = object.getString("HM");
                Integer nilaiAngka = object.getInt("HA");

                bobot = sks * nilaiAngka;

                Log.d("DataTahun", tahunAjaran);


                if (tahunAjaran.equals(dataTahun.get(0))) {
                    if (semester == 1) {
                        semester1[i] = dataMatkul;
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("semester1", Arrays.toString(semester1));
    }
}


