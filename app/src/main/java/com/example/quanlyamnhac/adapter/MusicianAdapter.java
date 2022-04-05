package com.example.quanlyamnhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanlyamnhac.MusicianDetail;
import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.model.MusicianModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MusicianAdapter extends ArrayAdapter<MusicianModel> {
    Context context;
    int resource;
    ArrayList<MusicianModel> musicianModels;

    public MusicianAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MusicianModel> musicianModels) {
        super(context, resource, musicianModels);
        this.context = context;
        this.resource = resource;
        this.musicianModels = musicianModels;
    }

    @Override
    public int getCount() {
        return musicianModels.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        ImageView ivImage = convertView.findViewById(R.id.ivImage);
        TextView tvName = convertView.findViewById(R.id.tvName);

        MusicianModel musician = musicianModels.get(position); // lấy vị trí hiện tại để đẩy lên tv và iv
        tvName.setText(musician.getName());
        Picasso.get().load(musician.getLinkImg()).into(ivImage);
        //set Event tại image để vào thông tin đối tượng
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MusicianDetail.class);
                intent.putExtra("item", musician); // gửi 1 đối tượng qua intent
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
