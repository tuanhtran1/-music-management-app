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

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.SingerDetail;
import com.example.quanlyamnhac.entity.SingerEntity;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SingerAdapter extends ArrayAdapter<ItemSingerReponse> {
    Context context;
    int resource;
    ArrayList<ItemSingerReponse> singerModels;

    public SingerAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemSingerReponse> singerModels) {
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

        ItemSingerReponse singerModel = singerModels.get(position); // lấy vị trí hiện tại để đẩy lên tv và iv
        tvName.setText(singerModel.getNameSinger());
        Picasso.get().load(singerModel.getImageSinger()).into(ivImage);
        //set Event tại image để vào thông tin đối tượng
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SingerDetail.class);
                intent.putExtra("item", singerModel); // gửi 1 đối tượng qua intent
                (context).startActivity(intent);
            }
        });

        return convertView;
    }
}
