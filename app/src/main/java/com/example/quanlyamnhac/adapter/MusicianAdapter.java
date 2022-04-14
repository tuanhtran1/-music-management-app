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
import com.example.quanlyamnhac.SongDetail;
import com.example.quanlyamnhac.entity.SongEntity;
import com.example.quanlyamnhac.mapper.SongMapper;
import com.example.quanlyamnhac.model.reponse.MusicianReponse;
import com.example.quanlyamnhac.sqlite.SQLite;

import java.util.List;

public class MusicianAdapter extends RecyclerView.Adapter<MusicianAdapter.ViewHolder> {

    SQLite sqLite;
    Context context;
    List<MusicianReponse> musicianModels;


    public MusicianAdapter(Context context, List<MusicianReponse> musicianModels) {
        this.context = context;
        this.musicianModels = musicianModels;
    }

    @NonNull
    @Override
    public MusicianAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_musician_detail_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicianAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (musicianModels != null && musicianModels.size() > 0) {
            MusicianReponse musicianReponse = musicianModels.get(position);
            holder.et_stt.setText(String.valueOf(position + 1));
            holder.et_songName.setText(musicianReponse.getSongName());
            holder.et_yearOfCreation.setText(musicianReponse.getYearOfCreation());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, SongDetail.class);

                    sqLite = new SQLite(v.getContext(), "music-managerment.sqlite", null, 1);
                    Cursor cursor = sqLite.getData(" SELECT * " +
                            " FROM song WHERE song.id_musician = " + MusicianDetail.idMusician +
                            " LIMIT 1 OFFSET " + position);
                    System.out.println("Vi tri: " + position);
                    SongEntity songEntity = null;
                    if (cursor.moveToNext()) {
                        songEntity = SongMapper.toSongEntity(cursor);
                        System.out.println(songEntity.getId());
                        SongDetail.idSong = songEntity.getId();
                    }
                    intent.putExtra("songEntity", songEntity);
                    context.startActivity(intent);
                    Toast.makeText(context, "vao SONG Detail", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            return;
        }
    }

    @Override
    public int getItemCount() {
        return musicianModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView et_stt, et_songName, et_yearOfCreation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            et_stt = itemView.findViewById(R.id.et_stt);
            et_songName = itemView.findViewById(R.id.et_songName);
            et_yearOfCreation = itemView.findViewById(R.id.et_yearOfCreation);
        }
    }
}
