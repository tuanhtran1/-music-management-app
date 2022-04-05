package com.example.quanlyamnhac.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.quanlyamnhac.model.reponse.HomeReponse;
import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.model.reponse.MusicianReponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MusicianAdapter extends RecyclerView.Adapter<MusicianAdapter.ViewHolder> {

    Context context;
    List<MusicianReponse> homeModels;

    public MusicianAdapter(Context context, List<MusicianReponse> homeModels) {
        this.context = context;
        this.homeModels = homeModels;
    }

    @NonNull
    @Override
    public MusicianAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_musician_detail_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicianAdapter.ViewHolder holder, int position) {
        if (homeModels != null && homeModels.size() > 0) {
            MusicianReponse model = homeModels.get(position);
            holder.et_stt.setText(String.valueOf(position));
            holder.et_songName.setText(model.getSongName());
            holder.et_singerName.setText(model.getSingerName());
            holder.et_yearOfCreation.setText(model.getYearOfCreation());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, Song.class);
                    intent.putExtra("songModel", model);
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
        return homeModels.size();
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
