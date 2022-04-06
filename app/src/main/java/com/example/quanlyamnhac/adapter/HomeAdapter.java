package com.example.quanlyamnhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.Song;
import com.example.quanlyamnhac.model.reponse.HomeReponse;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    Context context;
    List<HomeReponse> homeModels;

    public HomeAdapter(Context context, List<HomeReponse> homeModels) {
        this.context = context;
        this.homeModels = homeModels;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        if (homeModels != null && homeModels.size() > 0) {
            HomeReponse model = homeModels.get(position);
            holder.tv_stt.setText(String.valueOf(position+1));
            holder.tv_music.setText(model.getSongName());
            holder.tv_musical.setText(model.getMusicianName());
            holder.tv_year_of_creation.setText(model.getYearOfCreation());

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
        TextView tv_stt, tv_music, tv_musical, tv_year_of_creation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_stt = itemView.findViewById(R.id.tv_stt);
            tv_music = itemView.findViewById(R.id.tv_music);
            tv_musical = itemView.findViewById(R.id.tv_musical);
            tv_year_of_creation = itemView.findViewById(R.id.tv_year_of_creation);
        }
    }
}
