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

public class NilaiSemesterAdapter extends RecyclerView.Adapter<NilaiSemesterAdapter.ViewHolder> {

    private List<Nilai> listNilaiSemester;
    private Context context;
    private LayoutInflater inflater = null;

    public NilaiSemesterAdapter(List<Nilai> listNilaiSemester, Context context) {
        this.listNilaiSemester = listNilaiSemester;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_nilai_semester, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Nilai nilai = listNilaiSemester.get(position);

        holder.nomor.setText(String.valueOf(position + 1));
        holder.kodeMK.setText(nilai.getKodeMK());
        holder.namaMK.setText(nilai.getNamaMK());
        holder.sksMK.setText(nilai.getSks().toString());

        if (nilai.getHuruf().equals("D") || nilai.getHuruf().equals("E")) {
            holder.hurufMK.setText(nilai.getHuruf());
            holder.hurufMK.setTextColor(Color.RED);
        } else {
            holder.hurufMK.setText(nilai.getHuruf());
        }

        holder.bobotMK.setText(nilai.getBobot().toString());
    }

    @Override
    public int getItemCount() {
        return listNilaiSemester.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomor, kodeMK, namaMK, sksMK, hurufMK, bobotMK;

        public ViewHolder(View itemView) {
            super(itemView);

            nomor = itemView.findViewById(R.id.no);
            kodeMK = itemView.findViewById(R.id.kodeMK);
            namaMK = itemView.findViewById(R.id.namaMK);
            sksMK = itemView.findViewById(R.id.sksMK);
            hurufMK = itemView.findViewById(R.id.hurufMK);
            bobotMK = itemView.findViewById(R.id.bobotMK);
        }
    }
}
