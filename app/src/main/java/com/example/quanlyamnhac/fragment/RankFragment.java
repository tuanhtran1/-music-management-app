package com.example.quanlyamnhac.fragment;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.model.reponse.RankReponse;
import com.example.quanlyamnhac.entity.MusicianEntity;
import com.example.quanlyamnhac.entity.PerformanceInfoEntity;
import com.example.quanlyamnhac.entity.SingerEntity;
import com.example.quanlyamnhac.entity.SongEntity;
import com.example.quanlyamnhac.sqlite.SQLite;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;



public class RankFragment extends Fragment {

    private ListView lvSchool;
    private List<RankReponse> ranklist = new ArrayList<>();
    private List<PerformanceInfoEntity> thongtinbieudiens = new ArrayList<>();
    private List<SingerEntity> casis = new ArrayList<>();
    private List<MusicianEntity> nhacsis = new ArrayList<>();
    private List<SongEntity> baihats = new ArrayList<>();


    private ArrayAdapter<RankReponse> adapter;
    private String monthOfShow="02-2000";
    private String valuesl="";
    private int month=2;
    private Date dt=null;
    SQLite sqLite;

    //them menu de logout
    View view = null;

    public RankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_rank, container, false);
        setControl();
        setEvent();
        loadall();
        super.onCreate(savedInstanceState);

        lvSchool = view.findViewById(R.id.ranking);


        TextView x= view.findViewById(R.id.title);



        Spinner spinnerdanhsach;
        spinnerdanhsach =   view.findViewById(R.id.spinner1);

        ArrayList<String> arrayspiner= new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM");

        for(int i=0;i<thongtinbieudiens.size();i++)
        {
            arrayspiner.add(dateFormat.format(thongtinbieudiens.get(i).getPerformanceDay()));
        }


        for(int i=0;i<arrayspiner.size();i++)
        {
            for(int j=i+1;j< arrayspiner.size();j++)
            {
                if(arrayspiner.get(i).equals(arrayspiner.get(j)))
                {
                    arrayspiner.remove(j);
                }
            }
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
        for(int i=0;i<arrayspiner.size();i++)
        {
            for(int j=i+1;j< arrayspiner.size();j++)
            {

                try {
                    Date date1 = formatter.parse(arrayspiner.get(i));
                    try {
                        Date date2 = formatter.parse(arrayspiner.get(j));
                        if (date1.compareTo(date2)<0) {
                            String tmp=arrayspiner.get(i);
                            arrayspiner.set(i,arrayspiner.get(j));
                            arrayspiner.set(j,tmp);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }


        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item,arrayspiner);

        spinnerdanhsach.setAdapter(arrayAdapter);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerdanhsach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                loadData(arrayspiner.get(i));
                adapter=null;
                adapter = new ArrayAdapter<RankReponse>(getActivity(),0,ranklist)
                {

                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                        LayoutInflater inflater= (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
                        convertView=inflater.inflate(R.layout.item_song_of_rank,null);
                        TextView name = convertView.findViewById(R.id.ten);
                        TextView singer = convertView.findViewById(R.id.casi);
                        TextView nhacsi= convertView.findViewById(R.id.nhacsi);
                        TextView hang= convertView.findViewById(R.id.hang);
                        ImageView img    = convertView.findViewById(R.id.imgsong);
                        RankReponse s= ranklist.get(position);
                        img.setImageResource(s.getImg());
                        hang.setText(String.valueOf(s.getHang()));
                        switch (s.getHang())
                        {
                            case 1:
                            {
                                hang.setTextColor(Color.parseColor("#ff2f2f"));
                                break;
                            }
                            case 2:
                            {
                                hang.setTextColor(Color.parseColor("#48a868"));
                                break;
                            }
                            case 3:
                            {
                                hang.setTextColor(Color.parseColor("#0056ff"));
                                break;
                            }
                            default: hang.setTextColor(Color.parseColor("#3b4045"));
                        }
                        name.setText(s.getName());
                        nhacsi.setText(s.getNhacsi());
                        singer.setText(s.getDecription());
                        return convertView;
                    }
                };
                lvSchool.setAdapter(adapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }



    private void loadData(String monthOfShow )
    {
        List<PerformanceInfoEntity> BdOnMoth = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM");


        for(int i=0;i<thongtinbieudiens.size();i++)
        {
            if(dateFormat.format(thongtinbieudiens.get(i).getPerformanceDay()).compareTo(monthOfShow)==0)
            {
                Integer mabd=thongtinbieudiens.get(i).getId();
                Integer macs=thongtinbieudiens.get(i).getSingerId();
                Integer mabh=thongtinbieudiens.get(i).getSongId();
                Date ngaybd=thongtinbieudiens.get(i).getPerformanceDay();
                String diadiembd=thongtinbieudiens.get(i).getPlace();
                PerformanceInfoEntity x =new PerformanceInfoEntity(mabd,macs,mabh,ngaybd,diadiembd);
                BdOnMoth.add(x);
            }
        }


        ArrayList<String> Listcs= new ArrayList<String>();
//        get casi

        for(int i=0;i<BdOnMoth.size();i++)
        {
            for(int j=0;j<casis.size();j++)
            {
                if(BdOnMoth.get(i).getSingerId()==casis.get(j).getId()) {

                    Listcs.add(casis.get(j).getName());
                }
            }
        }


        ArrayList<String> Listbaihat= new ArrayList<String>();
        ArrayList<Integer> Listns= new ArrayList<Integer>();
//        get mans
        for(int i=0;i<BdOnMoth.size();i++)
        {
            for(int j=0;j<baihats.size();j++)
            {
                if(BdOnMoth.get(i).getSongId()==baihats.get(j).getId())
                {
                    Listbaihat.add(baihats.get(j).getName());
                    Listns.add(baihats.get(j).getMusicianId());
                }
            }
        }

        //get nhac si
        ArrayList<String> Listtenns= new ArrayList<String>();
        for(int i=0;i<Listns.size();i++)
        {
            for(int j=0;j<nhacsis.size();j++)
            {
                if(Listns.get(i)==nhacsis.get(j).getId())
                {
                    Listtenns.add(nhacsis.get(j).getName());
                }
            }
        }

        ranklist.clear();


        for (int i=0;i<BdOnMoth.size();i++)
        {
            int k=i+1;
            RankReponse s= new RankReponse();
            s.setName(Listbaihat.get(i));
            s.setDecription( "- "+Listcs.get(i));
            s.setNhacsi("Sáng tác: " + Listtenns.get(i));
            s.setImg(R.drawable.img);
            s.setHang(k);
            ranklist.add(s);
        }
    }
    public void loadall()
    {

        Cursor sqlbaihat = sqLite.getData("SELECT * FROM song");
        while(sqlbaihat.moveToNext()) {
            SongEntity baihat= new SongEntity();
            baihat.setId(sqlbaihat.getInt(0));
            baihat.setName(sqlbaihat.getString(1));
            baihat.setYearCreation(sqlbaihat.getString(2));
            baihat.setMusicianId(sqlbaihat.getInt(3));
            baihats.add(baihat);
        }



        Cursor sqlnhacsi = sqLite.getData("SELECT * FROM musician");

        while(sqlnhacsi.moveToNext()) {
            MusicianEntity nhacsi= new MusicianEntity();
            nhacsi.setId(sqlnhacsi.getInt(0));
            nhacsi.setName(sqlnhacsi.getString(1));
            nhacsi.setLinkImg(sqlnhacsi.getString(2));
            nhacsis.add(nhacsi);
        }



        Cursor sqlcasi = sqLite.getData("SELECT * FROM singer");
        while(sqlcasi.moveToNext()) {
            SingerEntity casi= new SingerEntity();
            casi.setId(sqlcasi.getInt(0));
            casi.setName(sqlcasi.getString(1));
            casi.setLinkImg(sqlcasi.getString(2));
            casis.add(casi);
        }

        Cursor sqlttbieudien = sqLite.getData("SELECT * FROM performance_info");

        while(sqlttbieudien.moveToNext()) {
            PerformanceInfoEntity thongtinbieudien=new PerformanceInfoEntity();
            thongtinbieudien.setId(sqlttbieudien.getInt(0));
            thongtinbieudien.setSingerId(sqlttbieudien.getInt(1));
            thongtinbieudien.setSongId(sqlttbieudien.getInt(2));
            thongtinbieudien.setPlace("");
            thongtinbieudien.setPerformanceDay(convertdate(sqlttbieudien.getString(3)));
            thongtinbieudiens.add(thongtinbieudien);

        }


    }private Date convertdate(String str)
    {
        SimpleDateFormat df= new SimpleDateFormat("yyyy/MM/dd");
        Date dt= new Date();
        try {
            dt=df.parse(str);
            return dt;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    private void setEvent() {

    }
    private void setControl() {
        sqLite = new SQLite(getContext(),"music-managerment.sqlite", null, 1);

    }


}