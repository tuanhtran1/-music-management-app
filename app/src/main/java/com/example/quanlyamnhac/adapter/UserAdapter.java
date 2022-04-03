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
    public int getCount() {
        return userModels.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView ivAvatar;
        TextView tvEmail,tvPhone,tvUsername;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        holder.tvPhone.setText("SDT: " + user.getPhone());
        holder.tvUsername.setText("Username: " + user.getUsername());
        Picasso.get().load(user.getAvatar()).into(holder.ivAvatar);

        holder.ivAvatar.setOnClickListener(new View.OnClickListener() {     // bắt sự kiện khi bấm vào hình
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, EditUser.class);
//                // ((MainActivity)context).start activity(intent) - ép kiểu context về activity
//                intent.putExtra("item",user);    // gửi nguyên cả 1 đối tượng 'country'
//                ((DatabaseUserAdapter)context).startActivityForResult(intent,2);
            }
        });

        return view;

    }
}
