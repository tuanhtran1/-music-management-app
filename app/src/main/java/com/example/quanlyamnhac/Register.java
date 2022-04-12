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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {

    SQLite sqLite;

    EditText txtEmail, txtPhone, txtUsername, txtPass, txtConfirmPass, txtAvatar;
    Button btnDangKy;
    FloatingActionButton fbBack;

    UserModel user;

    private final static String TAG = Register.class.getName();
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setControl();
        mAuth = FirebaseAuth.getInstance();
        setEvent();
    }

    private void setControl() {

        user = new UserModel();
        sqLite = new SQLite(this, "music-managerment.sqlite", null, 1);

        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        txtUsername = findViewById(R.id.txtUsername);
        txtPass = findViewById(R.id.txtPass);
        txtConfirmPass = findViewById(R.id.txtConfirmPass);
        txtAvatar = findViewById(R.id.txtAvatar);
        btnDangKy = findViewById(R.id.btnDangKy);
        fbBack = findViewById(R.id.fbBack);
    }

    private void setEvent() {
        fbBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LogIn.class);
                startActivity(intent);
                Toast.makeText(Register.this, "BACK", Toast.LENGTH_SHORT).show();
            }
        });

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString().trim();
                String phone = txtPhone.getText().toString().trim();
                String userName = txtUsername.getText().toString().trim();
                String password = txtPass.getText().toString().trim();
                String confirmPass = txtConfirmPass.getText().toString().trim();
                String avatar = txtAvatar.getText().toString().trim();

                if (userName.equals("") || password.equals("") || confirmPass.equals("")) {
                    Toast.makeText(Register.this, "Phải có đầy đủ thông tin user name và password", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPass)) {
                    Toast.makeText(Register.this, "Password không khớp", Toast.LENGTH_SHORT).show();
                } else {
                    user.setEmail(email);
                    user.setPhone(phone);
                    user.setUsername(userName);
                    user.setPassword(password);
                    user.setAvatar(avatar);

                    String phoneNumber = "+84" + txtPhone.getText().toString();
                    startVerifyPhoneNumber(phoneNumber);

                }
            }
        });
    }

    private void startVerifyPhoneNumber(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(Register.this, "Verification Fail!\nPlease check your phone num again!", Toast.LENGTH_SHORT).show();
                            }

                            @Override // dt ko tu verify dc ma phai nhap
                            public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationID, forceResendingToken);
//                                goToEnterOTP(phoneNumber,verificationID);
                                Intent intent = new Intent(Register.this, EnterOTP.class);
                                intent.putExtra("phone_number", phoneNumber);
                                intent.putExtra("verification_id", verificationID);
                                intent.putExtra("user", user);
                                startActivity(intent);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            Intent intent = new Intent(Register.this, MainTabActivity.class);
                            startActivity(intent);
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(Register.this,
                                        "The verification code was invalid", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }


}





