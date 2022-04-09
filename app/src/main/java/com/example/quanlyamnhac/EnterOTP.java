package com.example.quanlyamnhac;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlyamnhac.model.UserModel;
import com.example.quanlyamnhac.sqlite.SQLite;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class EnterOTP extends AppCompatActivity {
    SQLite sqLite;

    EditText txtOTP;
    Button btnSend;
    FloatingActionButton fbBack;

    UserModel user;

    FirebaseAuth mAuth;
    String mPhoneNumber;
    String mVerificationID;
    private PhoneAuthProvider.ForceResendingToken mForceResendingToken;

    private final static String TAG = EnterOTP.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);

        user = new UserModel();
        sqLite = new SQLite(this,"music-managerment.sqlite", null, 1);

        setControl();
        getDataIntent();

        mAuth = FirebaseAuth.getInstance();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp = txtOTP.getText().toString().trim();
                startSendOTPCode(otp);
            }

        });

        fbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getDataIntent(){
        mPhoneNumber = getIntent().getStringExtra("phone_number");
        mVerificationID = getIntent().getStringExtra("verification_id");
        user = (UserModel) getIntent().getSerializableExtra("user");

    }

    private void setControl() {
        txtOTP = findViewById(R.id.txtOTP);
        btnSend = findViewById(R.id.btnSend);
        fbBack = findViewById(R.id.fbBack);
    }

    private void startSendOTPCode(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationID,otp);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");


                            String sql= "INSERT INTO user VALUES(null,'"+user.getEmail()+"','"+user.getPhone()+"','"+user.getUsername()+"','"+user.getPassword()+"','"+user.getAvatar()+"')";
                            sqLite.queryData(sql);

                            Intent intent = new Intent(EnterOTP.this,MainTabActivity.class);
                            startActivity(intent);
                            Toast.makeText(EnterOTP.this, "OTP verification success", Toast.LENGTH_SHORT).show();

//                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(EnterOTP.this,
                                        "The verification code was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


}
