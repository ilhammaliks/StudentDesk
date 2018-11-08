package com.project.zhimer.studentdesk;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.project.zhimer.studentdesk.model.Mahasiswa;
import com.project.zhimer.studentdesk.model.UET;
import com.project.zhimer.studentdesk.view.Login;
import com.project.zhimer.studentdesk.view.fragment.Biodata;
import com.project.zhimer.studentdesk.view.fragment.HalamanUtama;
import com.project.zhimer.studentdesk.view.fragment.IsiKrs;
import com.project.zhimer.studentdesk.view.fragment.Keuangan;
import com.project.zhimer.studentdesk.view.fragment.Nilai;
import com.project.zhimer.studentdesk.view.fragment.Perkuliahan;
import com.project.zhimer.studentdesk.view.fragment.PermintaanSurat;
import com.project.zhimer.studentdesk.view.fragment.RingkasanAkademik;
import com.project.zhimer.studentdesk.view.fragment.SemesterPendek;
import com.project.zhimer.studentdesk.view.fragment.TestQuran;
import com.project.zhimer.studentdesk.view.fragment.UaiEnglishTest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ForceUpdateChecker.OnRemoteConfigListener {
    //instance layout variable
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private boolean doubleBackToExitPressedOnce = false;

    NavigationView navigationView;
    //    model instance
    Mahasiswa mahasiswa;

    //fragment instance
    private HalamanUtama halamanUtama;
    private Biodata biodata;
    private PermintaanSurat permintaanSurat;
    private TestQuran testQuran;
    private UaiEnglishTest uaiEnglishTest;
    private RingkasanAkademik ringkasanAkademik;
    private Keuangan keuangan;
    private IsiKrs isiKrs;
    private Perkuliahan perkuliahan;
    private Nilai nilai;
    private SemesterPendek semesterPendek;

    //    session instance
    SessionManager sessionManager;

    private TextView sks, ipk, uet, tilawah, nama, nim, prodi, tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Drawer Layout
        drawerLayout = findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);
        sessionManager.getToken();

        Log.d("token", sessionManager.getToken() + "");

        //firebase instance
        ForceUpdateChecker.with(this).onUpdateNeeded(this).check();

        //header sidebar
        View header = navigationView.getHeaderView(0);

        ImageView foto = (ImageView) header.findViewById(R.id.mahasiswa_foto);

        sks = header.findViewById(R.id.mahasiswa_sks);
        ipk = header.findViewById(R.id.mahasiswa_ipk);
        uet = header.findViewById(R.id.mahasiswa_uet);
        tilawah = header.findViewById(R.id.mahasiswa_tilawah);

        nama = header.findViewById(R.id.mahasiswa_nama);
        nim = header.findViewById(R.id.mahasiswa_nim);
        prodi = header.findViewById(R.id.mahasiswa_prodi);
        tahun = header.findViewById(R.id.mahasiswa_tahun);

        MenuRequirements();
        DataMahasiswa();
        DataGradeSksIpk();
        DataUet();

        //Hardcode
        Picasso.with(getApplicationContext()).load(R.drawable.photo).into(foto);

        tilawah.setText("---");


        //fragment variable
        halamanUtama = new HalamanUtama();
        biodata = new Biodata();
        permintaanSurat = new PermintaanSurat();
        testQuran = new TestQuran();
        uaiEnglishTest = new UaiEnglishTest();
        ringkasanAkademik = new RingkasanAkademik();
        keuangan = new Keuangan();
        isiKrs = new IsiKrs();
        perkuliahan = new Perkuliahan();
        nilai = new Nilai();
        semesterPendek = new SemesterPendek();


        if (savedInstanceState == null) {
            setFragment(halamanUtama);
        } else {
            onResumeFragments();
        }

        navigationView.setNavigationItemSelectedListener(this);
    }

    private void DataMahasiswa() {
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

                        String nimMahasiswa = object.getString("mhs_nim");
                        String namaMahasiswa = object.getString("mhs_nm");
                        String tahunMahasiswa = object.getString("mhs_ank");
                        String prodiMahasiswa = object.getString("NamaProgdi");

                        nama.setText(namaMahasiswa);
                        nim.setText(nimMahasiswa);
                        prodi.setText(prodiMahasiswa);
                        tahun.setText(tahunMahasiswa);

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

    private void DataGradeSksIpk() {
        String url = sessionManager.getUrl() + "/akademik/daftarnilaikeseluruhan/format/json";
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
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray jsonArray = object.getJSONArray("data");

                    int jumlahSks = 0;

                    //inisialisasi penjumlahan IPk
                    double jumlahBobot = 0;
                    double penjumlahSKS = 0;
                    double penjumlahanIPK;
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");

                    //end inisialisasi penjumlahan IPK

                    int sksABC = 0;
                    String totalLulus = "";

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject objek = jsonArray.getJSONObject(i);

                        Integer sks = objek.getInt("mtkl_sks");
                        String huruf = objek.getString("HM");
                        Integer angka = objek.getInt("HA");

                        jumlahSks += sks;

                        penjumlahSKS += sks;
                        jumlahBobot += (sks * angka);

                        if (huruf.equals("A") || huruf.equals("B") || huruf.equals("C")) {
                            sksABC += sks;
                            totalLulus = String.valueOf(sksABC);
                        }
                    }
                    //TODO Set Text gabisa di masukin value int, jadi harus di convert ke String dulu

                    //operasi penjumlahan ipk
                    penjumlahanIPK = jumlahBobot / penjumlahSKS;
                    ipk.setText(decimalFormat.format(penjumlahanIPK));

                    sks.setText(totalLulus);
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

    private void DataUet() {
        String url = sessionManager.getUrl() + "/akademik/UET/format/json";
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
                    JSONObject object = new JSONObject(response.toString());
                    JSONArray jsonArray = object.getJSONArray("data");

                    Log.d("dataUet", object.length() + "");

                    for (int i = jsonArray.length() - 1; i >= 0; i--) {
                        JSONObject objek = jsonArray.getJSONObject(i);

                        String score = objek.getString("nilai");

                        uet.setText(score);
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

    private void MenuRequirements() {

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

                        String namaMK = object.getString("mtkl_nm");
                        String nilai = object.getString("HA");

                        if (namaMK.equals("Tugas Akhir") || namaMK.equals("Skripsi")) {
                            Menu nav_menu = navigationView.getMenu();
                            nav_menu.findItem(R.id.daftar_sidang).setVisible(true);
                        } else {
                            Menu nav_menu = navigationView.getMenu();
                            nav_menu.findItem(R.id.daftar_sidang).setVisible(false);
                        }

                        if (namaMK.equals("Tugas Akhir") || namaMK.equals("Skripsi")) {
                            Menu nav_menu = navigationView.getMenu();
                            nav_menu.findItem(R.id.daftar_wisuda).setVisible(true);
                        } else {
                            Menu nav_menu = navigationView.getMenu();
                            nav_menu.findItem(R.id.daftar_wisuda).setVisible(false);
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            //general
            case R.id.halamanUtama:
                setFragment(halamanUtama);
                return true;

            case R.id.biodata:
                setFragment(biodata);
                return true;

            case R.id.surat:
                setFragment(permintaanSurat);
                return true;

            case R.id.quran:
                setFragment(testQuran);
                return true;

            case R.id.uet:
                setFragment(uaiEnglishTest);
                return true;

            //perkuliahan
            case R.id.akademik:
                setFragment(ringkasanAkademik);
                return true;

            case R.id.keuangan:
                setFragment(keuangan);
                return true;

            case R.id.krs:
                setFragment(isiKrs);
                return true;

            case R.id.perkuliahan:
                setFragment(perkuliahan);
                return true;

            case R.id.nilai:
                setFragment(nilai);
                return true;

            //semester pendek
            case R.id.sp_perkuliahan:
                setFragment(semesterPendek);

                //daftar sidang & wisuda


                //logout
            case R.id.logout:
                sessionManager.setLogin(false);
                Intent logout = new Intent(MainActivity.this, Login.class);
                startActivity(logout);
                finish();

            default:
                return true;
        }
    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, fragment);

        drawerLayout.closeDrawers();
        transaction.addToBackStack("screen");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            int fragments = getSupportFragmentManager().getBackStackEntryCount();
            if (fragments == 1) {
                if (doubleBackToExitPressedOnce) {
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startMain);
                    finish();
                }

                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 1000);
            } else {
                if (getFragmentManager().getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStack();
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    @Override
    public void onUpdateNeeded(final String updateUrl) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("New version available")
                .setMessage("Please, update app to new version to continue.")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        redirectStore(updateUrl);
                    }
                }).setNegativeButton("No, Thanks", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void onSetMenuKrs() {
        Menu nav_menu = navigationView.getMenu();
        nav_menu.findItem(R.id.krs).setVisible(false);
    }

    @Override
    public void onSetMenuKrsSP() {
        Menu nav_menu = navigationView.getMenu();
        nav_menu.findItem(R.id.sp_krs).setVisible(false);
    }

    @Override
    public void onSetMenuPerkuliahanSp() {
        Menu nav_menu = navigationView.getMenu();
        nav_menu.findItem(R.id.sp_perkuliahan).setVisible(false);
    }

    @Override
    public void onSetMenuDaftarSidang() {
        Menu nav_menu = navigationView.getMenu();
        nav_menu.findItem(R.id.daftar_sidang).setVisible(false);
    }

    @Override
    public void onSetMenuDaftarWisuda() {
        Menu nav_menu = navigationView.getMenu();
        nav_menu.findItem(R.id.daftar_wisuda).setVisible(false);
    }

    private void redirectStore(String updateUrl) {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
