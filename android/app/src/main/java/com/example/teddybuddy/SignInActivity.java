package com.example.teddybuddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {
    private EditText userId, userPw;
    private Button signup_btn, signin_btn;

    public final String TAG = "SignInActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        userId = findViewById(R.id.userId);
        userPw = findViewById(R.id.userPw);
        signup_btn = (Button) findViewById(R.id.signup_btn);
        signin_btn = (Button) findViewById(R.id.signin_btn);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignin();
            }
        });
    }
        private void startSignin(){
            final String user_id = userId.getText().toString();
            final String user_pw = userPw.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
//                    서버 url 맞추기
                    .baseUrl("http://192.168.96.22:8081/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            LoginInterface loginInterface = retrofit.create(LoginInterface.class);
            Call<LoginInfomation> call = loginInterface.getSignin(user_id, user_pw);
            call.enqueue(new Callback<LoginInfomation>() {
                @Override
                public void onResponse( Call<LoginInfomation> call, Response<LoginInfomation> response) {
                    try{
                        Log.d(TAG, "아이디: " + user_id + " 비번: " + user_pw);
                        if (response.isSuccessful()) { // 회원등록에 성공한 경우
                            Log.e(TAG, "성공: " + response.body().toString());
                            Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            intent.putExtra("user_id",user_id);
                            intent.putExtra("user_pw",user_pw);
                            startActivity(intent);
                        } else { // 로그인에 실패한 경우
                            Log.e(TAG, "실패: " + response.code());
                            Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onFailure(Call<LoginInfomation> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });

        }

    }
