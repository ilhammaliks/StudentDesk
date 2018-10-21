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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ForceUpdateChecker.OnRemoteConfigListener {
    //instance layout variable
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private boolean doubleBackToExitPressedOnce = false;

    NavigationView navigationView;
    //firebase instance

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

    SessionManager sessionManager;

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

        TextView sks = (TextView) header.findViewById(R.id.mahasiswa_sks);
        TextView ipk = (TextView) header.findViewById(R.id.mahasiswa_ipk);
        TextView uet = (TextView) header.findViewById(R.id.mahasiswa_uet);
        TextView tilawah = (TextView) header.findViewById(R.id.mahasiswa_tilawah);

        TextView nama = (TextView) header.findViewById(R.id.mahasiswa_nama);
        TextView nim = (TextView) header.findViewById(R.id.mahasiswa_nim);
        TextView prodi = (TextView) header.findViewById(R.id.mahasiswa_prodi);
        TextView tahun = (TextView) header.findViewById(R.id.mahasiswa_tahun);

        //Hardcode
        Picasso.with(getApplicationContext()).load(R.drawable.photo).into(foto);

        sks.setText("139");
        ipk.setText("3.75");
        uet.setText("560");
        tilawah.setText("---");

        nama.setText("Ilham Malik Muhammad");
        nim.setText("0102513010");
        prodi.setText("Teknik Informatika");
        tahun.setText("2013");

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
    public void onUpdateNeeded(final String updateUrl)
    {
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
    public void onSetMenuKrs()
    {
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

    private void redirectStore(String updateUrl)
    {
        final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
