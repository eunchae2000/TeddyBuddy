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
import com.google.gson.Gson;

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
    static final String base = "http://192.168.0.105:8080/";


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
            LoginInformation loginInformation = new LoginInformation(user_id, user_pw);

            Retrofit retrofit = new Retrofit.Builder()
//                    서버 url 맞추기
                    .baseUrl(base)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            LoginInterface loginInterface = retrofit.create(LoginInterface.class);
            Call<LoginInformation> call = loginInterface.getSignin(loginInformation);
            call.enqueue(new Callback<LoginInformation>() {
                @Override
                public void onResponse( Call<LoginInformation> call, Response<LoginInformation> response) {
                    try{
                        Log.d(TAG, "아이디: " + user_id + " 비번: " + user_pw);
                        //응답받은 body값을 getter로 가져오기 위해 객체 생성
                        LoginInformation body = response.body();
                        //reponse.isSuccessful는 요청과 응답이 이루어졌는지만 체크해서 아무 값이나 넣어도 참을 반환하기때문에 응답받은 success값도 참인지 같이 확인
                        if (response.isSuccessful()&&body.isSuccess()) { // 로그인에 성공한 경우
                            Log.d(TAG, "성공: " + new Gson().toJson(response.body())); //.toString은 객체값을 반환해서 응답받은 데이터를 확인하기 위해 Gson사용
                            Toast.makeText(getApplicationContext(), "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            intent.putExtra("user_id",user_id);
                            intent.putExtra("user_pw",user_pw);

                            startActivity(intent);
                        } else { // 로그인에 실패한 경우
                            Log.e(TAG, "실패: " + response.code() + body.getMsg());
                            Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onFailure(Call<LoginInformation> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });

        }

    }
