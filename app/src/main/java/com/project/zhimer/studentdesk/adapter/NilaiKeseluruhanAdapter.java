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

public class NilaiKeseluruhanAdapter extends RecyclerView.Adapter<NilaiKeseluruhanAdapter.ViewHolder> {

    private List<Nilai> listSeluruhNilai;
    private Context context;
    private LayoutInflater inflater = null;

    public NilaiKeseluruhanAdapter(List<Nilai> listSeluruhNilai, Context context) {
        this.listSeluruhNilai = listSeluruhNilai;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public NilaiKeseluruhanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_nilai_keseluruhan, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NilaiKeseluruhanAdapter.ViewHolder holder, int position) {

        final Nilai nilai = listSeluruhNilai.get(position);

        holder.kodeMK.setText(nilai.getKodeMK());
        holder.namaMK.setText(nilai.getNamaMK());
        holder.hurufMK.setText(nilai.getHuruf());
        holder.angkaMK.setText(nilai.getAngka());
        holder.sksMK.setText(nilai.getSks());
        holder.bobotMK.setText(nilai.getBobot());
    }

    @Override
    public int getItemCount() {
        return listSeluruhNilai.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomor, kodeMK, namaMK, sksMK, hurufMK, angkaMK, bobotMK;
        public ViewHolder(View itemView) {
            super(itemView);

            nomor = itemView.findViewById(R.id.no);
            kodeMK = itemView.findViewById(R.id.kodeMK);
            namaMK = itemView.findViewById(R.id.namaMK);
            sksMK = itemView.findViewById(R.id.sksMK);
            hurufMK = itemView.findViewById(R.id.hurufMK);
            angkaMK = itemView.findViewById(R.id.angkaMK);
            bobotMK = itemView.findViewById(R.id.bobotMK);
        }
    }
}
