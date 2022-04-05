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
import com.example.quanlyamnhac.adapter.ItemMusicianAdapter;
import com.example.quanlyamnhac.adapter.MusicianAdapter;
import com.example.quanlyamnhac.entity.MusicianEntity;
import com.example.quanlyamnhac.model.reponse.ItemMusicianReponse;
import com.example.quanlyamnhac.model.reponse.MusicianReponse;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MusicianFragment extends Fragment {

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
                                Toast.makeText(getContext(),"Da them nhac si",Toast.LENGTH_LONG).show();
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
        ArrayList<ItemMusicianReponse> data = new ArrayList<>();
        ItemMusicianReponse musician1 = new ItemMusicianReponse( "Trịnh Công Sơn", "https://upload.wikimedia.org/wikipedia/vi/5/5b/Trinhcongson.jpg");
        ItemMusicianReponse musician2 = new ItemMusicianReponse("Văn Cao", "https://upload.wikimedia.org/wikipedia/vi/thumb/1/1a/Vancao.jpg/175px-Vancao.jpg");
        ItemMusicianReponse musician3 = new ItemMusicianReponse("Trần Tiến", "http://baokhanhhoa.vn/dataimages/201507/original/images1088188_nhac_sy_Tran_tien.jpg");
        ItemMusicianReponse musician4 = new ItemMusicianReponse("Phương Uyên", "http://image.vtc.vn/files/f1/2012/09/17/avapu1jpg.jpg");
        ItemMusicianReponse musician5 = new ItemMusicianReponse("Ngọc Châu", "https://vtv1.mediacdn.vn/thumb_w/650/2022/3/17/104311693297023005203625988508962618143060o-16474902650871024895088-crop-1647490273721790446797.jpg");
        ItemMusicianReponse musician6 = new ItemMusicianReponse("Đoàn Chuẩn", "https://image.thanhnien.vn/w1024/Uploaded/2022/wpdhnwejw/2021_03_20/doanchuan_omql.jpg");
        ItemMusicianReponse musician7 = new ItemMusicianReponse("Phan Mạnh Quỳnh", "https://10hay.com/wp-content/uploads/2016/07/phan-manh-quynh-vo-nguoi-ta.jpg");
        ItemMusicianReponse musician8 = new ItemMusicianReponse( "Khắc Việt", "https://toplist.vn/images/800px/khac-viet-556237.jpg");
        data.add(musician1);
        data.add(musician2);
        data.add(musician3);
        data.add(musician4);
        data.add(musician5);
        data.add(musician6);
        data.add(musician7);
        data.add(musician8);
        return data;
    }

    private void setControl() {
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