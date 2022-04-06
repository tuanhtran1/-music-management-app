package com.example.quanlyamnhac.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyamnhac.MusicianDetail;
import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.Song;
import com.example.quanlyamnhac.entity.MusicianEntity;
import com.example.quanlyamnhac.mapper.MusicianMapper;
import com.example.quanlyamnhac.model.reponse.HomeReponse;
import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.model.reponse.MusicianReponse;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MusicianAdapter extends RecyclerView.Adapter<MusicianAdapter.ViewHolder> {

    SQLite sqLite;

    Context context;
    List<MusicianReponse> musicianModels;

    MusicianEntity musicianEntity;

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
            holder.et_stt.setText(String.valueOf(position+1));
            holder.et_songName.setText(musicianReponse.getSongName());
            holder.et_singerName.setText(musicianReponse.getSingerName());
            holder.et_yearOfCreation.setText(musicianReponse.getYearOfCreation());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, Song.class);
                    intent.putExtra("musicianReponse", musicianReponse);

                    sqLite = new SQLite(v.getContext(), "music-managerment.sqlite", null, 1);
                    Cursor cursor = sqLite.getData(" SELECT song.id, singer.id " +
                            " FROM song, singer, performance_info " +
                            " WHERE performance_info.singer_id = singer.id AND performance_info.song_id = song.id" +
                            " AND song.id_musician = " + MusicianDetail.idMusician + " LIMIT 1 OFFSET " + position);
                    if(cursor.moveToNext()){
                        System.out.println(cursor.getInt(0) + " "+ cursor.getInt(1));
                        intent.putExtra("idSong", cursor.getInt(0));
                        intent.putExtra("idSinger", cursor.getInt(1));
                    }

                    cursor = sqLite.getData("SELECT * FROM musician WHERE id = " + MusicianDetail.idMusician);
                    if(cursor.moveToNext()){
                        musicianEntity = MusicianMapper.toMusicianEntity(cursor);
                    }
                    intent.putExtra("musicianEntity", musicianEntity);

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
        return musicianModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView et_stt, et_songName, et_singerName, et_yearOfCreation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            et_stt =  itemView.findViewById(R.id.et_stt);
            et_songName = itemView.findViewById(R.id.et_songName);
            et_singerName = itemView.findViewById(R.id.et_singerName);
            et_yearOfCreation = itemView.findViewById(R.id.et_yearOfCreation);
        }
    }
}
