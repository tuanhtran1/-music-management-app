package com.example.quanlyamnhac.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.quanlyamnhac.MusicianAdd;
import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.adapter.MusicianAdapter;
import com.example.quanlyamnhac.model.MusicianModel;
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
//                Toast.makeText(getContext(), "Add Musician", Toast.LENGTH_SHORT).show();
//                openAddDialog(Gravity.CENTER);
                Intent intent = new Intent(getContext(), MusicianAdd.class);
                startActivity(intent);
            }
        });

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
        MusicianModel musician6 = new MusicianModel("6", "Đoàn Chuẩn", "https://image.thanhnien.vn/w1024/Uploaded/2022/wpdhnwejw/2021_03_20/doanchuan_omql.jpg");
        MusicianModel musician7 = new MusicianModel("7", "Phan Mạnh Quỳnh", "https://10hay.com/wp-content/uploads/2016/07/phan-manh-quynh-vo-nguoi-ta.jpg");
        MusicianModel musician8 = new MusicianModel("8", "Khắc Việt", "https://toplist.vn/images/800px/khac-viet-556237.jpg");
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

    o

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        DanhLam danhLam = (DanhLam) data.getSerializableExtra("item");
        if(requestCode ==2)
        {
            if(resultCode ==2){

            }else{

            }
        }
        if(requestCode ==1)
        {
            //Toast.makeText(this,"Them",Toast.LENGTH_SHORT).show();

        }
    }


//    private void openAddDialog(int gravity){
//        final Dialog dialog = new Dialog(getContext());
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.custom_dialog);
//        dialog.setCancelable(true);
//
//        Window window = dialog.getWindow();
//        if(window == null)
//        {
//            return;
//        }
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        WindowManager.LayoutParams windowAttributes = window.getAttributes();
//        windowAttributes.gravity = gravity;
//        window.setAttributes(windowAttributes);
//
//
//
////        EditText txtName = view.findViewById(R.id.txtName);
////        EditText txtLinkImg = view.findViewById(R.id.txtLinkImg);
////        Button btnThem = view.findViewById(R.id.btnThem);
//
////        btnThem.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Toast.makeText(getContext(),"Them nhac si", Toast.LENGTH_SHORT).show();
////
////            }
////        });
//
//        dialog.show();
//    }
}