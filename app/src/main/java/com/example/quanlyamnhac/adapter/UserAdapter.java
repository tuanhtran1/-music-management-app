package com.example.quanlyamnhac.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.example.quanlyamnhac.EditUser;
import com.example.quanlyamnhac.R;
import com.example.quanlyamnhac.User;
import com.example.quanlyamnhac.model.UserModel;
import com.squareup.picasso.Picasso;

public class UserAdapter extends BaseAdapter {
    // nạp dữ liệu cho listview của ds user
    Context context;
    int layout;
    List<UserModel> userModels;

    public UserAdapter(Context context, int layout, List<UserModel> data) {
        this.context = context;
        this.layout = layout;
        this.userModels = data;
    }

    @Override
    public int getCount() {             // trả về số phần tử mà ListView hiện thị
        return userModels.size(); }

    @Override
    public Object getItem(int i) {      // trả về đối tượng dữ liệu phần tử ở vị trí position
        return null; }

    @Override
    public long getItemId(int i) {      //Trả về một ID liên quan đến phần tử ở vị trí position
        return 0;
    }

    private class ViewHolder{
        ImageView ivAvatar;
        TextView tvEmail,tvPhone,tvUsername;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        // viewholder giup tranh viec findViewById nhieu lan
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //chuyen layout thanh view de sd trong getView
            view = inflater.inflate(layout,null);
            holder.ivAvatar = (ImageView) view.findViewById(R.id.ivAvatar);
            holder.tvEmail = (TextView) view.findViewById(R.id.tvEmail);
            holder.tvPhone = (TextView) view.findViewById(R.id.tvPhone);
            holder.tvUsername = (TextView) view.findViewById(R.id.tvUsername);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        UserModel user = userModels.get(i);
        holder.tvEmail.setText("Mail: " + user.getEmail());
        holder.tvPhone.setText("Phone: " + user.getPhone());
        holder.tvUsername.setText("Username: " + user.getUsername());
        Picasso.get().load(user.getAvatar()).into(holder.ivAvatar);

        holder.ivAvatar.setOnClickListener(new View.OnClickListener() {     // bắt sự kiện khi bấm vào hình
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditUser.class);
                intent.putExtra("item",user);    // gửi nguyên cả 1 đối tượng 'user'
                ((User)context).startActivityForResult(intent,2);
            }
        });

        return view;

    }
}
