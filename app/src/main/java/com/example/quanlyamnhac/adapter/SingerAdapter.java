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

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.SingerDetail;
import com.example.quanlyamnhac.Song;
import com.example.quanlyamnhac.entity.SingerEntity;
import com.example.quanlyamnhac.model.reponse.HomeReponse;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
import com.example.quanlyamnhac.model.reponse.MusicianReponse;
import com.example.quanlyamnhac.model.reponse.SingerReponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.ViewHolder> {

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
        return new SingerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingerAdapter.ViewHolder holder, int position) {
        if (singerModels != null && singerModels.size() > 0) {
            SingerReponse model = singerModels.get(position);
            holder.et_stt.setText(String.valueOf(position));
            holder.et_music.setText(model.getSongName());
            holder.et_musician.setText(model.getMusicianName());
            holder.et_year_of_creation.setText(model.getYearOfCreation());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context, Song.class);
                    intent.putExtra("songModel", model);
                    context.startActivity(intent);
                    Toast.makeText(context,"vao SONG",Toast.LENGTH_LONG).show();
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