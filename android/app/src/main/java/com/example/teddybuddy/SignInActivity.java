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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignInActivity extends AppCompatActivity {
    private EditText userId, userPw;
    private Button signup_btn, signin_btn;
    private String nickname;

    public final String TAG = "SignInActivity";
    public final static String base = "http://192.168.0.105:8080/";


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

            Gson gson = new Gson();

            LoginInformation loginInformation = new LoginInformation(user_id, user_pw);

            Retrofit retrofit = new Retrofit.Builder()
//                    ?????? url ?????????
                    .baseUrl(base)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            LoginInterface loginInterface = retrofit.create(LoginInterface.class);
            Call<LoginInformation> call = loginInterface.getSignin(loginInformation);
            call.enqueue(new Callback<LoginInformation>() {
                @Override
                public void onResponse( Call<LoginInformation> call, Response<LoginInformation> response) {
                    try{
                        Log.d(TAG, "?????????: " + user_id + " ??????: " + user_pw);
                        //???????????? body?????? getter??? ???????????? ?????? ?????? ??????
                        LoginInformation body = response.body();
                        Object object = response.body().getDetail();
                        String json = gson.toJson(object);
                        System.out.println(json);

                        JsonParser parser = new JsonParser();
                        JsonObject obj = null;
                        obj = (JsonObject)parser.parse(json);
                        System.out.println(obj.get("nickname"));
                        System.out.println(obj.get("id"));

                        String nickname = String.valueOf(obj.get("nickname"));
                        String interests1st = String.valueOf(obj.get("interests1st"));
                        String interests2nd = String.valueOf(obj.get("interests2nd"));
                        String interests3rd = String.valueOf(obj.get("interests3rd"));

                        //reponse.isSuccessful??? ????????? ????????? ????????????????????? ???????????? ?????? ????????? ????????? ?????? ????????????????????? ???????????? success?????? ????????? ?????? ??????
                        if (response.isSuccessful()&&body.isSuccess()) {// ???????????? ????????? ??????
                            Log.d(TAG, "??????: " + gson.toJson(response.body())); //.toString??? ???????????? ???????????? ???????????? ???????????? ???????????? ?????? Gson??????
                            Toast.makeText(getApplicationContext(), "???????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            intent.putExtra("user_id",user_id);
                            intent.putExtra("user_pw",user_pw);
                            intent.putExtra("nickname", nickname);
                            intent.putExtra("interests1st", interests1st);
                            intent.putExtra("interests2nd", interests2nd);
                            intent.putExtra("interests3rd", interests3rd);
                            startActivity(intent);
                        } else { // ???????????? ????????? ??????
                            Log.e(TAG, "??????: " + response.code() + body.getMsg());
                            Toast.makeText(getApplicationContext(), "???????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
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
