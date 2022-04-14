package com.example.quanlyamnhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.quanlyamnhac.entity.MusicianEntity;
import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemMusicianAdapter extends ArrayAdapter<ItemMusicianReponse> {

    SQLite sqLite;
    Context context;
    int resource;
    ArrayList<ItemMusicianReponse> musicianModels;
    MusicianEntity musicianEntity = new MusicianEntity();


    public ItemMusicianAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemMusicianReponse> musicianModels) {
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
        convertView = LayoutInflater.from(context).inflate(resource, null);
        ImageView ivImage = convertView.findViewById(R.id.ivImage);
        TextView tvName = convertView.findViewById(R.id.tvName);
        System.out.println(position);

        ItemMusicianReponse itemMusician = musicianModels.get(position); // lấy vị trí hiện tại để đẩy lên tv và iv

        tvName.setText(itemMusician.getNameMusician());
        Picasso.get().load(itemMusician.getImageMusician()).into(ivImage);
        //set Event tại image để vào thông tin đối tượng
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MusicianDetail.class);
                intent.putExtra("item", itemMusician); // gửi 1 đối tượng qua intent

                // tìm ra row trong table musician có vi tri = positon = entiy
                sqLite = new SQLite(getContext(), "music-managerment.sqlite", null, 1);
                Cursor cursor = sqLite.getData("SELECT musician.id FROM musician LIMIT 1 OFFSET " + position);
                if (cursor.moveToNext()) {
                    MusicianDetail.idMusician = cursor.getInt(0);
                }
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
