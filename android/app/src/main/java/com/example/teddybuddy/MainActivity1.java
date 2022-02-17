package com.example.teddybuddy;

import static com.example.teddybuddy.SignInActivity.base;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity1 extends AppCompatActivity {

    ImageButton friend1, friend2, friend3;
    ImageView imageView;
    TextView info1, info2, info3;
    boolean i = true;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        friend1 = findViewById(R.id.friend1);
        friend2 = findViewById(R.id.friend2);
        friend3 = findViewById(R.id.friend3);
        info1 = findViewById(R.id.friendinfo1);
        info2 = findViewById(R.id.friendinfo2);
        info3 = findViewById(R.id.friendinfo3);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        GlideDrawableImageViewTarget gifImage = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.drawable.connectheart).into(gifImage);

        friend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectFriend1();
            }
        });

        friend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connectFriend1();
            }
        });

       friend3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               connectFriend1();
           }
       });
    }


        private void connectFriend1(){
            Intent intent = getIntent();
            String id = intent.getStringExtra("user_id");
            String interests1st = intent.getStringExtra("interests1st");
            String interests2nd = intent.getStringExtra("interests2nd");
            String interests3rd = intent.getStringExtra("interests3rd");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(base)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            FriendInterface friendInterface = retrofit.create(FriendInterface.class);
            Call<FriendInformation> call = friendInterface.getFriends(id, interests1st, interests2nd, interests3rd);
            call.enqueue(new Callback<FriendInformation>() {
                @Override
                public void onResponse(Call<FriendInformation> call, Response<FriendInformation> response) {
                    System.out.println(response.isSuccessful());
                    if(response.isSuccessful()){
                        Log.d("매칭 ", new Gson().toJson(response.body()));
                        Toast.makeText(getApplicationContext(), "친구와 연결되었습니다!", Toast.LENGTH_SHORT);
                        info1.setText("친구 1의 이름");
                    }else{
                        Log.d("실패1: ", "실패");
                        System.out.println(response.code());
                    }
                }
                @Override
                public void onFailure(Call<FriendInformation> call, Throwable t) {
                    Log.d("실패2: ", "onFailure: " + t.getMessage());
                }
            });

        }
    }
