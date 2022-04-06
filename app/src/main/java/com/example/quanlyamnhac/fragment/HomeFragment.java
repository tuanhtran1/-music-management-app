package com.example.quanlyamnhac.fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.adapter.HomeAdapter;
import com.example.quanlyamnhac.mapper.HomeMapper;
import com.example.quanlyamnhac.model.reponse.HomeReponse;
import com.example.quanlyamnhac.sqlite.SQLite;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    SQLite sqLite;
    RecyclerView recycler_home;
    HomeAdapter homeAdapter;
    View view;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mapping();
        setRecyclerHome();
        //https://www.tutorialspoint.com/how-to-call-an-activity-method-from-a-fragment-in-android-app
        return view;
    }

    private void mapping() {
        sqLite = new SQLite(getContext(),"music-managerment.sqlite", null, 1);;
        recycler_home = view.findViewById(R.id.recycler_home);
    }

    private void setRecyclerHome() {
        recycler_home.setHasFixedSize(true);
        recycler_home.setLayoutManager(new LinearLayoutManager((getContext())));
        List<HomeReponse> homeReponseList = getList();
        homeAdapter = new HomeAdapter(getContext(),homeReponseList);
        recycler_home.setAdapter(homeAdapter);
    }

    private List<HomeReponse> getList() {

//        sqLite.queryData("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "email VARCHAR(50), phone VARCHAR(50), username VARCHAR(50), password VARCHAR(50), " +
//                "avatar VARCHAR(500))");
//
//        // musician
//        sqLite.queryData("CREATE TABLE IF NOT EXISTS musician(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "name VARCHAR(50), image VARCHAR(500))");
//        // song
//        sqLite.queryData("CREATE TABLE IF NOT EXISTS song(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "name VARCHAR(50), yearofcreation VARCHAR(50), id_musician INTEGER," +
//                "FOREIGN KEY (id_musician) REFERENCES musician(id))");
//
//        // singer
//        sqLite.queryData("CREATE TABLE IF NOT EXISTS singer(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "name VARCHAR(50), image VARCHAR(500))");
//
//        // performance_info
//        sqLite.queryData("CREATE TABLE IF NOT EXISTS performance_info(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "singer_id INTEGER , song_id INTEGER, username VARCHAR(50), performance_day VARCHAR(50), " +
//                "place VARCHAR(50)," +
//                "FOREIGN KEY (singer_id) REFERENCES singer(id), FOREIGN KEY (song_id) REFERENCES song(id))");
//
//
//        sqLite.queryData("INSERT INTO musician VALUES(null,'Tu Tran', 'https://upload.wikimedia.org/wikipedia/vi/5/5b/Trinhcongson.jpg')");
//        sqLite.queryData("INSERT INTO musician VALUES(null,'Long Le', 'https://upload.wikimedia.org/wikipedia/vi/thumb/1/1a/Vancao.jpg/175px-Vancao.jpg')");
//
//
//        sqLite.queryData("INSERT INTO song VALUES(null,'Yeu duoi', '1990', 1)");
//        sqLite.queryData("INSERT INTO song VALUES(null,'Hoa no khong mau', '1995', 1)");
//
//        sqLite.queryData("INSERT INTO song VALUES(null,'La lung', '2000', 2)");
//
//        System.out.println("canh hahaha");
//
//        Cursor cursor = sqLite.getData("SELECT song.name, musician.name, song.yearofcreation FROM song, musician"
//        + " WHERE song.id_musician = musician.id");
//        List<HomeReponse> homeModels = new ArrayList<>();
//        while(cursor.moveToNext()){
//            homeModels.add(HomeMapper.toHomeReponse(cursor));
//        }


        List<HomeReponse> homeModels = new ArrayList<>();
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        homeModels.add(new HomeReponse("Đi và suy", "C-ANH", "2021"));
        return homeModels;
    }
}