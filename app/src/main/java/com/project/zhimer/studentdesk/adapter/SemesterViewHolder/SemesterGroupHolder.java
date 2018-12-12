package com.project.zhimer.studentdesk.adapter.SemesterViewHolder;

import android.view.View;
import android.widget.TextView;

import com.project.zhimer.studentdesk.R;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class SemesterGroupHolder extends GroupViewHolder {

    TextView hTahunAjaran, hSemester;

    public SemesterGroupHolder(View itemView) {
        super(itemView);
       /* hTahunAjaran = itemView.findViewById(R.id.headerTahunAjaran);
        hSemester = itemView.findViewById(R.id.headerSemester);*/
    }

    public void setHeader(String data) {
        hTahunAjaran.setText(data);
        hSemester.setText(data);
    }
}
