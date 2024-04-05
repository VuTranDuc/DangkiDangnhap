package com.example.signin_login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DangKyActivity extends AppCompatActivity {
    EditText username, password, passwordag, phone;
    Button dangky, toidangnhap;

    DBHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangki);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        passwordag = (EditText) findViewById(R.id.passwordag);
        phone = (EditText) findViewById(R.id.phone);
        dangky = (Button) findViewById(R.id.btndangky);
        toidangnhap = (Button) findViewById(R.id.btntoidangnhap);
        db = new DBHelper(this);

        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String passag = passwordag.getText().toString();
                String numberphone = phone.getText().toString();

                if(user.equals("")||pass.equals("")||passag.equals("")||numberphone.equals(""))
                    Toast.makeText(DangKyActivity.this, "Yeu cau nhap day du thong tin", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(passag)){
                        Boolean checkuser = db.checkusername(user);
                        if(checkuser == false){
                            Boolean insert = db.insertData(user, pass, numberphone);
                            if(insert == true){
                                Toast.makeText(DangKyActivity.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent); //ấn zô là tới home class
                            }
                            else{
                                Toast.makeText(DangKyActivity.this, "Dang ky that bai", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(DangKyActivity.this, "Da co nguoi dung nay. Vui long dang nhap", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(DangKyActivity.this, "Mat khau nhap lai khong trung khop", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        toidangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                startActivity(intent); //ấn zô là tới đăng nhập class
            }
        });
    }
}
