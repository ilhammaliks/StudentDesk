package com.project.zhimer.studentdesk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.model.Nilai;

import java.util.List;

public class NilaiAktifAdapter extends RecyclerView.Adapter<NilaiAktifAdapter.ViewHolder> {

    private List<Nilai> listNilaiAktif;
    private Context context;
    private LayoutInflater inflater = null;

    public NilaiAktifAdapter(List<Nilai> listNilaiAktif, Context context) {
        this.listNilaiAktif = listNilaiAktif;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public NilaiAktifAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_nilai_aktif, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NilaiAktifAdapter.ViewHolder holder, int position) {

        final Nilai nilai = listNilaiAktif.get(position);

        holder.nomor.setText(String.valueOf(position + 1));
        holder.kodeMK.setText(nilai.getKodeMK());
        holder.namaMK.setText(nilai.getNamaMK());
        holder.sksMK.setText(nilai.getSks());

        if (nilai.getHuruf().equals("D") || nilai.getHuruf().equals("E")) {
            holder.hurufMK.setText(nilai.getHuruf());
            holder.hurufMK.setTextColor(Color.RED);
        } else {
            holder.hurufMK.setText(nilai.getHuruf());
        }

    }

    @Override
    public int getItemCount() {
        return listNilaiAktif.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomor, kodeMK, namaMK, sksMK, hurufMK;
        public ViewHolder(View itemView) {
            super(itemView);

            nomor = itemView.findViewById(R.id.no);
            kodeMK = itemView.findViewById(R.id.kodeMK);
            namaMK = itemView.findViewById(R.id.namaMK);
            sksMK = itemView.findViewById(R.id.sksMK);
            hurufMK = itemView.findViewById(R.id.hurufMK);
        }
    }
}
