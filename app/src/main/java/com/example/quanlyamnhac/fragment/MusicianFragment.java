package com.example.quanlyamnhac.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.adapter.MusicianAdapter;
import com.example.quanlyamnhac.model.MusicianModel;

import java.util.ArrayList;


public class MusicianFragment extends Fragment {

    View view;

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
        return view;
    }

    private void setEvent() {
        ArrayList<MusicianModel> musicianModels = khoitao(); // doc du lieu tu SQL
        //list Nhac si
        MusicianAdapter musicianAdapter = new MusicianAdapter(getContext(), R.layout.item_musician_layout, musicianModels);
        gvListMusician.setAdapter(musicianAdapter);
    }

    private ArrayList<MusicianModel> khoitao() {
        ArrayList<MusicianModel> data = new ArrayList<>();
        MusicianModel musician1 = new MusicianModel("1", "Trịnh Công Sơn", "https://upload.wikimedia.org/wikipedia/vi/5/5b/Trinhcongson.jpg");
        MusicianModel musician2 = new MusicianModel("2", "Văn Cao", "https://upload.wikimedia.org/wikipedia/vi/thumb/1/1a/Vancao.jpg/175px-Vancao.jpg");
        MusicianModel musician3 = new MusicianModel("3", "Trần Tiến", "http://baokhanhhoa.vn/dataimages/201507/original/images1088188_nhac_sy_Tran_tien.jpg");
        MusicianModel musician4 = new MusicianModel("4", "Phương Uyên", "http://image.vtc.vn/files/f1/2012/09/17/avapu1jpg.jpg");
        MusicianModel musician5 = new MusicianModel("5", "Ngọc Châu", "https://vtv1.mediacdn.vn/thumb_w/650/2022/3/17/104311693297023005203625988508962618143060o-16474902650871024895088-crop-1647490273721790446797.jpg");
        data.add(musician1);
        data.add(musician2);
        data.add(musician3);
        data.add(musician4);
        data.add(musician5);
        return data;
    }

    private void setControl() {
        gvListMusician = view.findViewById(R.id.gvDanhSachMusician);
    }
}