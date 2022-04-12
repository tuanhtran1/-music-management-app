package com.example.quanlyamnhac.fragment;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.adapter.ItemSingerAdapter;
import com.example.quanlyamnhac.mapper.SingerMapper;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class SingerFragment extends Fragment {

    SQLite sqLite;

    //them menu de logout

    View view;
    FloatingActionButton fbThem;
    GridView gvListSinger;

    public SingerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_singer, container, false);
        setControl();
        setEvent();

        fbThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.add_singer_dialog);

                //Initializing the views of the dialog.
                final EditText singerName = dialog.findViewById(R.id.et_singerName);
                final EditText linkImageSinger = dialog.findViewById(R.id.et_linkImageSinger);
                Button submitButton = dialog.findViewById(R.id.btn_submit);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sqLite.queryData("INSERT INTO singer VALUES(null, '" + singerName.getText() + "','" + linkImageSinger.getText() + "')");
                        dialog.dismiss();
                        setEvent();
                    }
                });
                dialog.show();
            }
        });

        return view;
    }

    private void setEvent() {
        ArrayList<ItemSingerReponse> singerModels = khoitao(); // doc du lieu tu SQL
        //list Nhac si
        ItemSingerAdapter singerAdapter = new ItemSingerAdapter(getContext(), R.layout.item_singer_layout, singerModels);
        gvListSinger.setAdapter(singerAdapter);
    }

    private ArrayList<ItemSingerReponse> khoitao() {
        Cursor cursor = sqLite.getData("SELECT * FROM singer");
        ArrayList<ItemSingerReponse> itemSingerReponses = new ArrayList<>();
        while (cursor.moveToNext()) {
            itemSingerReponses.add(SingerMapper.toItemSingerReponse(cursor));
        }
        return itemSingerReponses;
    }

    private void setControl() {
        sqLite = new SQLite(getContext(), "music-managerment.sqlite", null, 1);
        fbThem = view.findViewById(R.id.fbThem);
        gvListSinger = view.findViewById(R.id.gvDanhSachCasi);

    }
}