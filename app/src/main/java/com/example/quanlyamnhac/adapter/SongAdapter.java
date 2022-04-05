package com.example.quanlyamnhac.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.model.SongModel;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{

    Context context;
    List<SongModel> songModels;

    public SongAdapter(Context context, List<SongModel> songModels){
        this.context = context;
        this.songModels = songModels;
    }

    @NonNull
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.ViewHolder holder, int position) {
        if(songModels != null && songModels.size()>0){
            SongModel model = songModels.get(position);
            holder.tv_stt.setText(model.getStt());
            holder.tv_singer_name.setText(model.getSingerName());
            holder.tv_performance_day.setText(model.getPerformanceDay());
            holder.tv_place.setText(model.getPlace());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,model.getStt(),Toast.LENGTH_LONG).show();
                }
            });
        }else{
            return;
        }
    }

    @Override
    public int getItemCount() {
        return songModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_stt, tv_singer_name, tv_performance_day, tv_place;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_stt = itemView.findViewById(R.id.tv_stt);
            tv_singer_name = itemView.findViewById(R.id.tv_singer_name);
            tv_performance_day = itemView.findViewById(R.id.tv_performance_day);
            tv_place = itemView.findViewById(R.id.tv_place);
        }
    }

}
