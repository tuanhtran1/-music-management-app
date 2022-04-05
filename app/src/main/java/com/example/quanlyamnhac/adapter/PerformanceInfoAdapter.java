package com.example.quanlyamnhac.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyamnhac.PerformanceInfo;
import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.model.reponse.PerformanceInfoReponse;

import java.util.List;

public class PerformanceInfoAdapter extends RecyclerView.Adapter<PerformanceInfoAdapter.ViewHolder> {
    Context context;
    List<PerformanceInfoReponse> performanceModels;

    public PerformanceInfoAdapter(Context context, List<PerformanceInfoReponse> performanceModels){
        this.context = context;
        this.performanceModels = performanceModels;
    }

    @NonNull
    @Override
    public PerformanceInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_performance_detail,parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PerformanceInfoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(performanceModels != null && performanceModels.size()>0){
            PerformanceInfoReponse model = performanceModels.get(position);
            holder.tv_stt.setText(String.valueOf(position));
            holder.tv_singer_name.setText(model.getSingerName());
            holder.tv_song_name.setText(model.getSongName());
            holder.tv_performance_day.setText(model.getDate().toString());
            holder.tv_place.setText(model.getPlace());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,position +"",Toast.LENGTH_LONG).show();
                }
            });
        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return performanceModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_stt,tv_singer_name, tv_song_name, tv_performance_day, tv_place;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_stt = itemView.findViewById(R.id.tv_stt);
            tv_singer_name = itemView.findViewById(R.id.tv_singer_name);
            tv_song_name = itemView.findViewById(R.id.tv_song_name);
            tv_performance_day = itemView.findViewById(R.id.tv_performance_day);
            tv_place = itemView.findViewById(R.id.tv_place);
        }
    }

}
