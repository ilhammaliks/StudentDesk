package com.project.zhimer.studentdesk.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class RingkasanAkademik extends Fragment {

    View view;
    SessionManager sessionManager;
    TextView mahasiswa_nama, mahasiswa_nim, mahasiswa_prodi, mahasiswa_dosen, mahasiswa_jalur, mahasiswa_status,
            jumlahSks, ipk, a, b, c, d, e;
    private RecyclerView recyclerView;


    public RingkasanAkademik() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ringkasan_akademik, container, false);

        mahasiswa_nama = view.findViewById(R.id.mahasiswa_nama);
        mahasiswa_nim = view.findViewById(R.id.mahasiswa_nim);
        mahasiswa_prodi = view.findViewById(R.id.mahasiswa_prodi);
        mahasiswa_dosen = view.findViewById(R.id.mahasiswa_dosen);
        mahasiswa_jalur = view.findViewById(R.id.mahasiswa_jalur);
        mahasiswa_status = view.findViewById(R.id.mahasiswa_status);

        jumlahSks = view.findViewById(R.id.mahasiswa_sks);
        ipk = view.findViewById(R.id.mahasiswa_ipk);
        a = view.findViewById(R.id.jumlahA);
        b = view.findViewById(R.id.jumlahB);
        c = view.findViewById(R.id.jumlahC);
        d = view.findViewById(R.id.jumlahD);
        e = view.findViewById(R.id.jumlahE);

        recyclerView = view.findViewById(R.id.recyclerView);

        sessionManager = new SessionManager(getContext());

        DataBio();
        DataRingkasAkademik();
        return view;
    }


    private void DataBio() {
        String url = "https://studentdesk.uai.ac.id/api/index.php/biodata/LihatBiodata/format/json";
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
//                        mahasiswa = new Mahasiswa();

                        String nim = object.getString("mhs_nim");
                        String nama = object.getString("mhs_nm");
                        String prodi = object.getString("NamaProgdi");
                        String pembimbing = object.getString("DosenPembimbing");
                        String jalurMasuk = object.getString("NamaJalurMasuk");
                        String statusAkademik = object.getString("NamaStatusAkademik");

                        mahasiswa_nama.setText(nama);
                        mahasiswa_nim.setText(nim);
                        mahasiswa_prodi.setText(prodi);
                        mahasiswa_dosen.setText(pembimbing);
                        mahasiswa_jalur.setText(jalurMasuk);
                        mahasiswa_status.setText(statusAkademik);
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

    private void DataRingkasAkademik() {
        String url = "https://studentdesk.uai.ac.id/api/index.php/akademik/daftarnilaikeseluruhan/format/json";
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
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray jsonArray = object.getJSONArray("data");

                    int jumlahSksLulus = 0;
                    String totalLulus = "";

                    //inisialisasi penjumlahan IPk
                    double jumlahBobot = 0;
                    double penjumlahSKS = 0;
                    double penjumlahanIPK;
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");

                    int nilaiA = 0;
                    int nilaiB = 0;
                    int nilaiC = 0;
                    int nilaiD = 0;
                    int nilaiE = 0;

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objek = jsonArray.getJSONObject(i);

                        String kodeMK = objek.getString("mtkl_kd");
                        String namaMK = objek.getString("mtkl_nm");
                        Integer sks = objek.getInt("mtkl_sks");
                        String huruf = objek.getString("HM");
                        Integer angka = objek.getInt("HA");

                        if (huruf.equals("A") || huruf.equals("B") || huruf.equals("C")) {
                            jumlahSksLulus += sks;
                            totalLulus = String.valueOf(jumlahSksLulus);
                        }

                        if (huruf.equals("A")) {
                            nilaiA += 1;
                        }
                        if (huruf.equals("B")) {
                            nilaiB += 1;
                        }
                        if (huruf.equals("C")) {
                            nilaiC += 1;
                        }
                        if (huruf.equals("D")) {
                            nilaiD += 1;
                        }
                        if (huruf.equals("E")) {
                            nilaiE += 1;
                        }

                        jumlahBobot += (sks * angka);
                        penjumlahSKS += sks;
                    }
                    //TODO Set Text gabisa di masukin value int, jadi harus di convert ke String dulu

                    jumlahSks.setText(totalLulus);

                    //operasi penjumlahan ipk
                    penjumlahanIPK = jumlahBobot / penjumlahSKS;
                    ipk.setText(decimalFormat.format(penjumlahanIPK));
                    //End operation

                    a.setText(String.valueOf(nilaiA));
                    b.setText(String.valueOf(nilaiB));
                    c.setText(String.valueOf(nilaiC));
                    d.setText(String.valueOf(nilaiD));
                    e.setText(String.valueOf(nilaiE));
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
