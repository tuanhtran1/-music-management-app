package com.example.quanlyamnhac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlyamnhac.mapper.HomeMapper;
import com.example.quanlyamnhac.mapper.UserMapper;
import com.example.quanlyamnhac.model.UserModel;
import com.example.quanlyamnhac.model.reponse.HomeReponse;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class LogIn extends AppCompatActivity {

    SQLite sqLite;

    EditText txtUser,txtPass;
    Button btnXacNhan;
    FloatingActionButton fbDangKy, fbBack, fbQuenMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setDatabase();
        setControl();
        setEvent();
    }

    private void setDatabase() {
//        //user
//        sqLite.queryData("CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "email VARCHAR(50), phone VARCHAR(50), username VARCHAR(50), password VARCHAR(50), " +
//                "avatar VARCHAR(500))");
//        // musician
//        sqLite.queryData("CREATE TABLE IF NOT EXISTS musician(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "name VARCHAR(50), image VARCHAR(500))");
//        // song
//        sqLite.queryData("CREATE TABLE IF NOT EXISTS song(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "name VARCHAR(50), yearofcreation VARCHAR(50), id_musician INTEGER," +
//                "FOREIGN KEY (id_musician) REFERENCES musician(id))");
//        // singer
//        sqLite.queryData("CREATE TABLE IF NOT EXISTS singer(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "name VARCHAR(50), image VARCHAR(500))");
//        // performance_info
//        sqLite.queryData("CREATE TABLE IF NOT EXISTS performance_info(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "singer_id INTEGER , song_id INTEGER, performance_day VARCHAR(50), " +
//                "place VARCHAR(50)," +
//                "FOREIGN KEY (singer_id) REFERENCES singer(id), FOREIGN KEY (song_id) REFERENCES song(id))");
//
//        // add data for musician
//        sqLite.queryData("INSERT INTO musician VALUES(null,'Tu Tran', 'https://scontent.fsgn5-11.fna.fbcdn.net/v/t39.30808-6/276141901_2066437443551833_3554981700837470288_n.jpg?_nc_cat=110&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=i7SadWFsptAAX9FR_pQ&_nc_ht=scontent.fsgn5-11.fna&oh=00_AT85qMXcGLtQVJEopK4iRTiaDyDjHIVezcqixncEzAXj9w&oe=62544A22')");
//        sqLite.queryData("INSERT INTO musician VALUES(null,'Long Le', 'https://scontent.fsgn5-11.fna.fbcdn.net/v/t1.6435-9/40971417_1099084503581285_4490043924605304832_n.jpg?_nc_cat=110&ccb=1-5&_nc_sid=8bfeb9&_nc_ohc=lxykUsj-HB0AX8iVmMg&tn=mgMk5d9cE_sd-XQ0&_nc_ht=scontent.fsgn5-11.fna&oh=00_AT-0yJsFUo3aLjDv3YStt5okM1J2K_cdVO34nBTlpyrCSw&oe=62767E25')");
//        // add data for song
//        sqLite.queryData("INSERT INTO song VALUES(null,'Lac troi', '1990', 1)");
//        sqLite.queryData("INSERT INTO song VALUES(null,'Nang am xa dan', '1995', 1)");
//        sqLite.queryData("INSERT INTO song VALUES(null,'Cong chua bong bong', '2000', 2)");
//        // add data for user
//        sqLite.queryData("INSERT INTO user VALUES(null,'admin@gmail','0987654321','admin','123','https://haycafe.vn/wp-content/uploads/2021/11/Anh-avatar-dep-chat-lam-hinh-dai-dien.jpg')");
//        sqLite.queryData("INSERT INTO user VALUES(null,'hoang@gmail','0123456789','hoang','321','https://thuthuatnhanh.com/wp-content/uploads/2021/02/Anh-avatar-bua-cute-dep-390x390.jpg')");
//        // add data for singer
//        sqLite.queryData("INSERT INTO singer VALUES(null,'Son Tung', 'https://kenh14cdn.com/thumb_w/660/203336854389633024/2021/5/28/13285652342587086741428035430233108735653736n-1622165532863738421439.jpg')");
//        sqLite.queryData("INSERT INTO singer VALUES(null,'Bao Thy', 'https://vnn-imgs-f.vgcloud.vn/2019/11/03/17/bao-thy-moi-thong-tin-ket-hon-khong-phai-tu-toi-deu-la-tin-don.jpg')");
//        // add data for performance_info
//        sqLite.queryData("INSERT INTO performance_info VALUES(null,1, 1, '2022/09/10', 'PTIT')");
//        sqLite.queryData("INSERT INTO performance_info VALUES(null,1, 2, '2022/03/12', 'Dong Nai')");
//        sqLite.queryData("INSERT INTO performance_info VALUES(null,2, 3, '2022/07/08', 'Ha Noi')");

    }

    private void setControl() {
        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        fbDangKy = findViewById(R.id.fbDangKy);
        fbBack = findViewById(R.id.fbBack);
        fbQuenMK = findViewById(R.id.fbQuenMK);
    }

    private void setEvent() {
        khoiTao();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = txtUser.getText().toString();
                String password = txtPass.getText().toString();
                if(userName.equals("") || password.equals("")){
                    Toast.makeText(LogIn.this, "Vui lòng nhập đủ username và password", Toast.LENGTH_SHORT).show();
                }
                else{
                    Cursor cursor = sqLite.getData("SELECT * FROM user");
//                    List<UserModel> userModels = new ArrayList<>();
                    while(cursor.moveToNext()){
                        if(cursor.getString(3).equals(userName) && cursor.getString(4).equals(password)) {
                            Intent intent = new Intent(LogIn.this, MainTabActivity.class);
                            startActivity(intent);
                            Toast.makeText(LogIn.this, "MAIN TAB", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    Toast.makeText(LogIn.this, "Thông tin đăng nhập sai!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        fbDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogIn.this, Register.class);
                startActivity(intent);
            }
        });

        fbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        fbQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = txtUser.getText().toString();
                Cursor cursor = sqLite.getData("SELECT * FROM user");
                while(cursor.moveToNext()){
                    if(!userName.equals("") && cursor.getString(3).equals(userName)) {
                        final String username = "johnnyhoang482@gmail.com";
                        final String password = "ahtxuatpvsbecehk";
                        Random random = new Random();
                        int randomNumber = random.nextInt(999999);
                        sqLite.queryData("UPDATE user SET password = '"+randomNumber+"' WHERE id = '"+cursor.getString(0)+"'");
                        String messageToSend = String.valueOf(randomNumber);
                        Properties props = new Properties();
                        props.put("mail.smtp.auth","true");
                        props.put("mail.smtp.starttls.enable","true");
                        props.put("mail.smtp.host","smtp.gmail.com");
                        props.put("mail.smtp.port","587");
                        Session session = Session.getInstance(props,
                                new javax.mail.Authenticator(){
                                    @Override
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(username, password);
                                    }
                                });
                        try{

                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(username));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cursor.getString(1)));
                            message.setSubject("Thay đổi mật khẩu!");
                            message.setText(messageToSend);
                            Transport.send(message);
                            Toast.makeText(getApplicationContext(),"Password mới đã được gửi vào email",Toast.LENGTH_LONG).show();
                        }catch (MessagingException e){
                            throw new RuntimeException();
                        }
                    }
                    else{
                        Toast.makeText(LogIn.this, "Vui lòng nhập tài khoản!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    private void khoiTao() {
        txtUser.setText(getIntent().getStringExtra("username"));
        txtPass.setText(getIntent().getStringExtra("password"));
    }
}