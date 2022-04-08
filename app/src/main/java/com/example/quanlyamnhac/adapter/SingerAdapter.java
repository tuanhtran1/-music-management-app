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
import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.SingerDetail;
import com.example.quanlyamnhac.SongOfPerformanceInfo;
import com.example.quanlyamnhac.model.reponse.SingerReponse;
import com.example.quanlyamnhac.sqlite.SQLite;

import java.util.List;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder> {

    SQLite sqLite;

    Context context;
    List<SingerReponse> singerModels;

    public SingerAdapter(Context context, List<SingerReponse> singerModels) {
        this.context = context;
        this.singerModels = singerModels;
    }

    @NonNull
    @Override
    public SingerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_singer_detail_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (singerModels != null && singerModels.size() > 0) {
            SingerReponse singerReponse = singerModels.get(position);
            holder.et_stt.setText(String.valueOf(position+1));
            holder.et_music.setText(singerReponse.getSongName());
            holder.et_musician.setText(singerReponse.getMusicianName());
            holder.et_year_of_creation.setText(singerReponse.getYearOfCreation());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, SongOfPerformanceInfo.class);

                    sqLite = new SQLite(v.getContext(), "music-managerment.sqlite", null, 1);
                    Cursor cursor = sqLite.getData(" SELECT DISTINCT song.id, musician.id, song.name, musician.name, song.yearofcreation" +
                            " FROM song, musician, singer, performance_info " +
                            " WHERE performance_info.singer_id = singer.id AND performance_info.song_id = song.id" +
                            " AND song.id_musician = musician.id AND singer.id = " + SingerDetail.idSinger + " LIMIT 1 OFFSET " + position);
                    if(cursor.moveToNext()){
                        System.out.println(SingerDetail.idSinger + " " + cursor.getInt(0) + " "+ cursor.getInt(1));
                        SongOfPerformanceInfo.idSong = cursor.getInt(0);
                        MusicianDetail.idMusician = cursor.getInt(1);
                    }
                    context.startActivity(intent);
                    Toast.makeText(context, "vao SONG", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return singerModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView et_stt, et_music, et_musician, et_year_of_creation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            et_stt = itemView.findViewById(R.id.et_stt);
            et_music = itemView.findViewById(R.id.et_music);
            et_musician = itemView.findViewById(R.id.et_musician);
            et_year_of_creation = itemView.findViewById(R.id.et_year_of_creation);
        }
    }
}