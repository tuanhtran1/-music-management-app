package com.example.quanlyamnhac.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.adapter.SingerAdapter;
import com.example.quanlyamnhac.entity.SingerEntity;
import com.example.quanlyamnhac.model.reponse.ItemSingerReponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class SingerFragment extends Fragment {

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
                        Toast.makeText(getContext(),"Da them ca si",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
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
        SingerAdapter singerAdapter = new SingerAdapter(getContext(), R.layout.item_singer_layout, singerModels);
        gvListSinger.setAdapter(singerAdapter);
    }

    private ArrayList<ItemSingerReponse> khoitao() {
        ArrayList<ItemSingerReponse> data = new ArrayList<>();
        ItemSingerReponse musician1 = new ItemSingerReponse( "Trịnh Công Sơn", "https://upload.wikimedia.org/wikipedia/vi/5/5b/Trinhcongson.jpg");
        ItemSingerReponse musician2 = new ItemSingerReponse( "Văn Cao", "https://upload.wikimedia.org/wikipedia/vi/thumb/1/1a/Vancao.jpg/175px-Vancao.jpg");
        ItemSingerReponse musician3 = new ItemSingerReponse( "Trần Tiến", "http://baokhanhhoa.vn/dataimages/201507/original/images1088188_nhac_sy_Tran_tien.jpg");
        ItemSingerReponse musician4 = new ItemSingerReponse( "Phương Uyên", "http://image.vtc.vn/files/f1/2012/09/17/avapu1jpg.jpg");
        ItemSingerReponse musician5 = new ItemSingerReponse( "Ngọc Châu", "https://vtv1.mediacdn.vn/thumb_w/650/2022/3/17/104311693297023005203625988508962618143060o-16474902650871024895088-crop-1647490273721790446797.jpg");
        data.add(musician1);
        data.add(musician2);
        data.add(musician3);
        data.add(musician4);
        data.add(musician5);
        return data;
    }

    private void setControl() {
        fbThem = view.findViewById(R.id.fbThem);
        gvListSinger = view.findViewById(R.id.gvDanhSachCasi);

    }
}