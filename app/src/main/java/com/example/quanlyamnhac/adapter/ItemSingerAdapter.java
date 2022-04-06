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
import com.example.quanlyamnhac.SingerDetail;
import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemSingerAdapter extends ArrayAdapter<ItemSingerReponse> {

    SQLite sqLite;

    Context context;
    int resource;
    ArrayList<ItemSingerReponse> singerModels;

    public ItemSingerAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemSingerReponse> singerModels) {
        super(context, resource, singerModels);
        this.context = context;
        this.resource = resource;
        this.singerModels = singerModels;
    }

    @Override
    public int getCount() {

        return singerModels.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        ImageView ivImage = convertView.findViewById(R.id.ivImage);
        TextView tvName = convertView.findViewById(R.id.tvName);

        ItemSingerReponse itemSinger = singerModels.get(position); // lấy vị trí hiện tại để đẩy lên tv và iv
        tvName.setText(itemSinger.getNameSinger());
        Picasso.get().load(itemSinger.getImageSinger()).into(ivImage);
        //set Event tại image để vào thông tin đối tượng
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SingerDetail.class);
                intent.putExtra("item", itemSinger); // gửi 1 đối tượng qua intent

                // tìm ra row trong table musician có vi tri = positon = entiy
                sqLite = new SQLite(getContext(),"music-managerment.sqlite", null, 1);
                Cursor cursor = sqLite.getData("SELECT singer.id FROM singer LIMIT 1 OFFSET " + position);
                if(cursor.moveToNext()){
                    intent.putExtra("idSinger", cursor.getInt(0));
                }
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
