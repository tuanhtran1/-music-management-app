package com.example.quanlyamnhac.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyamnhac.MusicianDetail;
import com.example.quanlyamnhac.PerformanceInfo;
import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.SingerDetail;
import com.example.quanlyamnhac.SongOfPerformanceInfo;
import com.example.quanlyamnhac.model.reponse.SongReponse;
import com.example.quanlyamnhac.sqlite.SQLite;

import java.util.List;

public class SongOfPerformanceInfoAdapter extends RecyclerView.Adapter<SongOfPerformanceInfoAdapter.ViewHolder>{

    SQLite sqLite;

    Context context;
    List<SongReponse> songModels;

    public SongOfPerformanceInfoAdapter(Context context, List<SongReponse> songModels){
        this.context = context;
        this.songModels = songModels;
    }

    @NonNull
    @Override
    public SongOfPerformanceInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongOfPerformanceInfoAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if(songModels != null && songModels.size()>0){
            SongReponse model = songModels.get(position);
            holder.tv_stt.setText(String.valueOf(position+1));
            holder.tv_singer_name.setText(model.getSingerName());
            holder.tv_performance_day.setText(model.getPerformanceDay());
            holder.tv_place.setText(model.getPlace());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sqLite = new SQLite(v.getContext(),"music-managerment.sqlite", null, 1);
                    Intent intent = new Intent(context, PerformanceInfo.class);
                    Cursor cursor = sqLite.getData(" SELECT performance_info.id " +
                            " FROM performance_info" +
                            " WHERE performance_info.singer_id =  "+ SingerDetail.idSinger +
                                    " AND performance_info.song_id ="+ SongOfPerformanceInfo.idSong+
                            " LIMIT 1 OFFSET " + position);
                    if(cursor.moveToNext()){
                        intent.putExtra("idPerformanceInfo", cursor.getInt(0));
                    }
                    Toast.makeText(context,"vao ber phone minh",Toast.LENGTH_LONG).show();
                    context.startActivity(intent);
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
