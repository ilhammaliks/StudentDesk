package com.project.zhimer.studentdesk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.project.zhimer.studentdesk.model.Nilai;

import java.util.List;

public class MatakuliahUlangAdapter extends RecyclerView.Adapter<MatakuliahUlangAdapter.ViewHolder> {

    private List<Nilai> listMatkulUlang;
    private Context context;
    private LayoutInflater inflater = null;

    public MatakuliahUlangAdapter(List<Nilai> listMatkulUlang, Context context) {
        this.listMatkulUlang = listMatkulUlang;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_matkul_ulang, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Nilai nilai = listMatkulUlang.get(position);

        holder.kode.setText(nilai.getKodeMK());
        holder.nama.setText(nilai.getNamaMK());
        holder.sks.setText(nilai.getSks().toString());
        holder.nilai.setText(nilai.getHuruf());

    }

    @Override
    public int getItemCount() {
        return listMatkulUlang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView kode, nama, sks, nilai;

        public ViewHolder(View itemView) {
            super(itemView);

            kode = itemView.findViewById(R.id.kodeMK);
            nama = itemView.findViewById(R.id.namaMK);
            sks = itemView.findViewById(R.id.sksMK);
            nilai = itemView.findViewById(R.id.hurufMK);
        }
    }
}
