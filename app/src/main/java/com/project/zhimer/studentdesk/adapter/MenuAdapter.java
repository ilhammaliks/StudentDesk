package com.project.zhimer.studentdesk.adapter;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.model.Menu;
import com.project.zhimer.studentdesk.view.fragment.Biodata;
import com.project.zhimer.studentdesk.view.fragment.HalamanUtama;
import com.project.zhimer.studentdesk.view.fragment.Keuangan;
import com.project.zhimer.studentdesk.view.fragment.Nilai;
import com.project.zhimer.studentdesk.view.fragment.Perkuliahan;
import com.project.zhimer.studentdesk.view.fragment.RingkasanAkademik;
import com.project.zhimer.studentdesk.view.fragment.UaiEnglishTest;
import com.project.zhimer.studentdesk.view.tabQuran.Score;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private Context context;
    private List<Menu> listMenu;

    public MenuAdapter(Context context, List<Menu> listMenu) {
        this.context = context;
        this.listMenu = listMenu;
    }

    @NonNull
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_menu, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.ViewHolder holder, final int position) {

        final Menu menu = listMenu.get(position);

        holder.title.setText(menu.getTitle());
        Picasso.with(context).load(menu.getImage()).into(holder.icon);

        //set onClickListener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Menu " + menu.getTitle(), Toast.LENGTH_SHORT).show();

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                Fragment fragment = new Fragment();

                HalamanUtama halamanUtama = new HalamanUtama();
                Biodata biodata = new Biodata();
                Score quran = new Score();
                UaiEnglishTest uet = new UaiEnglishTest();
                RingkasanAkademik ringkasanAkademik = new RingkasanAkademik();
                Keuangan keuangan = new Keuangan();
                Perkuliahan perkuliahan = new Perkuliahan();
                Nilai nilai = new Nilai();


                switch (position) {

                    case 0:
                          menuFragment(activity, halamanUtama);
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, halamanUtama).addToBackStack(null).commit();
                        break;

                    case 1:
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, biodata).addToBackStack(null).commit();
                        break;

                    case 2:
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, quran).addToBackStack(null).commit();
                        break;

                    case 3:
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, uet).addToBackStack(null).commit();
                        break;

                    case 4:
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, ringkasanAkademik).addToBackStack(null).commit();
                        break;

                    case 5:
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, keuangan).addToBackStack(null).commit();
                        break;

                    case 6:
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, perkuliahan).addToBackStack(null).commit();
                        break;

                    case 7:
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, nilai).addToBackStack(null).commit();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.menu_title);
            icon = itemView.findViewById(R.id.menu_icon);
        }
    }

    //todo biasain nama method awalnya huruf kecil
    private void menuFragment(AppCompatActivity activity, Fragment fragment) {
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).addToBackStack(null).commit();
    }
}
