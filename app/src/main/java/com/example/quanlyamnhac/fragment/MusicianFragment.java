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
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.adapter.ItemMusicianAdapter;
import com.example.quanlyamnhac.mapper.MusicianMapper;
import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MusicianFragment extends Fragment {

    SQLite sqLite;

    View view;
    FloatingActionButton fbThem;
    GridView gvListMusician;

    public MusicianFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_musician, container, false);
        setControl();
        setEvent();

        fbThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.add_musician_dialog);

                //Initializing the views of the dialog.
                final EditText musicianName = dialog.findViewById(R.id.et_musicianName);
                final EditText linkImageMusician = dialog.findViewById(R.id.et_linkImageMusician);
                Button submitButton = dialog.findViewById(R.id.btn_submit);

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Da them nhac si", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        return view;
    }

    private void setEvent() {
        ArrayList<ItemMusicianReponse> musicianModels = khoitao(); // doc du lieu tu SQL
        //list Nhac si
        ItemMusicianAdapter musicianAdapter = new ItemMusicianAdapter(getContext(), R.layout.item_musician_layout, musicianModels);
        gvListMusician.setAdapter(musicianAdapter);
    }

    private ArrayList<ItemMusicianReponse> khoitao() {

        Cursor cursor = sqLite.getData("SELECT * FROM musician");
        ArrayList<ItemMusicianReponse> itemMusicianReponses = new ArrayList<>();
        while (cursor.moveToNext()) {
            itemMusicianReponses.add(MusicianMapper.toItemMusicianReponse(cursor));
        }

        cursor.close();
        return itemMusicianReponses;
    }

    private void setControl() {
        sqLite = new SQLite(getContext(), "music-managerment.sqlite", null, 1);
        fbThem = view.findViewById(R.id.fbThem);
        gvListMusician = view.findViewById(R.id.gvDanhSachMusician);
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        SingerModel singerModel = (SingerModel) data.getSerializableExtra("item");
//        if(requestCode ==2)
//        {
//            if(resultCode ==2){
//
//            }else{
//
//            }
//        }
//        if(requestCode ==1)
//        {
//            //Toast.makeText(this,"Them",Toast.LENGTH_SHORT).show();
//
//        }
//    }


}