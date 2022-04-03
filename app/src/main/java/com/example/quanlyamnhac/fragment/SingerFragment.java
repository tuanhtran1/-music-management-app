package com.example.quanlyamnhac.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.adapter.MusicianAdapter;
import com.example.quanlyamnhac.adapter.SingerAdapter;
import com.example.quanlyamnhac.model.MusicianModel;
import com.example.quanlyamnhac.model.SingerModel;

import java.util.ArrayList;


public class SingerFragment extends Fragment {

    View view;

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
        return view;
    }

    private void setEvent() {
        ArrayList<SingerModel> singerModels = khoitao(); // doc du lieu tu SQL
        //list Nhac si
        SingerAdapter singerAdapter = new SingerAdapter(getContext(), R.layout.item_singer_layout, singerModels);
        gvListSinger.setAdapter(singerAdapter);
    }

    private ArrayList<SingerModel> khoitao() {
        ArrayList<SingerModel> data = new ArrayList<>();
        SingerModel musician1 = new SingerModel("1", "Trịnh Công Sơn", "https://upload.wikimedia.org/wikipedia/vi/5/5b/Trinhcongson.jpg");
        SingerModel musician2 = new SingerModel("2", "Văn Cao", "https://upload.wikimedia.org/wikipedia/vi/thumb/1/1a/Vancao.jpg/175px-Vancao.jpg");
        SingerModel musician3 = new SingerModel("3", "Trần Tiến", "http://baokhanhhoa.vn/dataimages/201507/original/images1088188_nhac_sy_Tran_tien.jpg");
        SingerModel musician4 = new SingerModel("4", "Phương Uyên", "http://image.vtc.vn/files/f1/2012/09/17/avapu1jpg.jpg");
        SingerModel musician5 = new SingerModel("5", "Ngọc Châu", "https://vtv1.mediacdn.vn/thumb_w/650/2022/3/17/104311693297023005203625988508962618143060o-16474902650871024895088-crop-1647490273721790446797.jpg");
        data.add(musician1);
        data.add(musician2);
        data.add(musician3);
        data.add(musician4);
        data.add(musician5);
        return data;
    }

    private void setControl() {
        gvListSinger = view.findViewById(R.id.gvDanhSachCasi);
    }
}