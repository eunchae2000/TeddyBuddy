package com.example.teddybuddy;

import static com.example.teddybuddy.SignInActivity.base;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.AtomicFile;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
                connectFriend2();
            }
        });

       friend3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               connectFriend3();
           }
       });
    }


        private void connectFriend1(){
            Intent intent = getIntent();
            String id = intent.getStringExtra("user_id");
            String interests1st = intent.getStringExtra("interests1st");
            String interests2nd = intent.getStringExtra("interests2nd");
            String interests3rd = intent.getStringExtra("interests3rd");

            Gson gson = new Gson();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(base)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            FriendInterface friendInterface = retrofit.create(FriendInterface.class);
            Call<FriendInformation> call1 = friendInterface.getFriends(id, interests1st, interests2nd, interests3rd);
            Call<FriendInformation> call2 = friendInterface.getFriendInfo(id);

            call1.enqueue(new Callback<FriendInformation>() {
                @Override
                public void onResponse(Call<FriendInformation> call, Response<FriendInformation> response) {
                    System.out.println(response.isSuccessful());
                    if(response.isSuccessful()){
                        Log.d("매칭 ", new Gson().toJson(response.body()));
                        Toast.makeText(getApplicationContext(), "친구와 연결되었습니다!", Toast.LENGTH_SHORT);

                        call2.enqueue(new Callback<FriendInformation>() {
                            @Override
                            public void onResponse(Call<FriendInformation> call, Response<FriendInformation> response) {
                                try{
                                    Gson gson = new Gson();
                                    Object object = response.body().getDetail();
                                    String json = gson.toJson(object);
                                    JsonParser parser = new JsonParser();
                                    Object obj = (Object) parser.parse(json);
                                    JsonArray jsonArr = (JsonArray) obj;
                                    ArrayList<String> list = new ArrayList<String>();

                                    if(jsonArr.size() > 0) {
                                        for (int i = 0; i < jsonArr.size(); i++) {
                                            JsonObject jsonObject = (JsonObject) jsonArr.get(i);
                                            System.out.println(jsonObject);
                                            String nickname = jsonObject.get("nickname").getAsString();
                                            list.add(nickname);
                                        }
                                        String[] nickname = list.toArray(new String[list.size()]);
                                        if(nickname[0] != null){
                                            info1.setText(nickname[0]);
                                            friend1.setImageResource(R.drawable.profile1);
                                            Toast.makeText(getApplicationContext(), "친구와 연결되었습니다!", Toast.LENGTH_SHORT);
                                        }else{
                                            Toast.makeText(getApplicationContext(), "매칭가능한 친구가 없습니다!", Toast.LENGTH_SHORT);
                                        }
                                    }
                                }catch (Exception e){
                                    System.out.println(response.code());
                                }
                            }

                            @Override
                            public void onFailure(Call<FriendInformation> call, Throwable t) {
                                Log.d("Main", "onFailure: " + t.getMessage());
                            }
                        });

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
    private void connectFriend2(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("user_id");
        String interests1st = intent.getStringExtra("interests1st");
        String interests2nd = intent.getStringExtra("interests2nd");
        String interests3rd = intent.getStringExtra("interests3rd");

        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FriendInterface friendInterface = retrofit.create(FriendInterface.class);
        Call<FriendInformation> call1 = friendInterface.getFriends(id, interests1st, interests2nd, interests3rd);
        Call<FriendInformation> call2 = friendInterface.getFriendInfo(id);

        call1.enqueue(new Callback<FriendInformation>() {
            @Override
            public void onResponse(Call<FriendInformation> call, Response<FriendInformation> response) {
                System.out.println(response.isSuccessful());
                if(response.isSuccessful()){
                    Log.d("매칭 ", new Gson().toJson(response.body()));
                    call2.enqueue(new Callback<FriendInformation>() {
                        @Override
                        public void onResponse(Call<FriendInformation> call, Response<FriendInformation> response) {
                            try{
                                Gson gson = new Gson();
                                Object object = response.body().getDetail();
                                String json = gson.toJson(object);
                                JsonParser parser = new JsonParser();
                                Object obj = (Object) parser.parse(json);
                                JsonArray jsonArr = (JsonArray) obj;
                                ArrayList<String> list = new ArrayList<String>();

                                if(jsonArr.size() > 0) {
                                    for (int i = 0; i < jsonArr.size(); i++) {
                                        JsonObject jsonObject = (JsonObject) jsonArr.get(i);
                                        System.out.println(jsonObject);
                                        String nickname = jsonObject.get("nickname").getAsString();
                                        list.add(nickname);
                                    }
                                    String[] nickname = list.toArray(new String[list.size()]);
                                    if(nickname[1] != null){
                                        info2.setText(nickname[1]);
                                        friend2.setImageResource(R.drawable.profile2);
                                        Toast.makeText(getApplicationContext(), "친구와 연결되었습니다!", Toast.LENGTH_SHORT);
                                    }else{
                                        Toast.makeText(getApplicationContext(), "매칭가능한 친구가 없습니다!", Toast.LENGTH_SHORT);
                                    }
                                }
                            }catch (Exception e){
                                System.out.println(response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<FriendInformation> call, Throwable t) {
                            Log.d("Main", "onFailure: " + t.getMessage());
                        }
                    });

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
    private void connectFriend3(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("user_id");
        String interests1st = intent.getStringExtra("interests1st");
        String interests2nd = intent.getStringExtra("interests2nd");
        String interests3rd = intent.getStringExtra("interests3rd");

        Gson gson = new Gson();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FriendInterface friendInterface = retrofit.create(FriendInterface.class);
        Call<FriendInformation> call1 = friendInterface.getFriends(id, interests1st, interests2nd, interests3rd);
        Call<FriendInformation> call2 = friendInterface.getFriendInfo(id);

        call1.enqueue(new Callback<FriendInformation>() {
            @Override
            public void onResponse(Call<FriendInformation> call, Response<FriendInformation> response) {
                System.out.println(response.isSuccessful());
                if(response.isSuccessful()){
                    Log.d("매칭 ", new Gson().toJson(response.body()));
                    call2.enqueue(new Callback<FriendInformation>() {
                        @Override
                        public void onResponse(Call<FriendInformation> call, Response<FriendInformation> response) {
                            try{
                                Gson gson = new Gson();
                                Object object = response.body().getDetail();
                                String json = gson.toJson(object);
                                JsonParser parser = new JsonParser();
                                Object obj = (Object) parser.parse(json);
                                JsonArray jsonArr = (JsonArray) obj;
                                ArrayList<String> list = new ArrayList<String>();

                                if(jsonArr.size() > 0) {
                                    for (int i = 0; i < jsonArr.size(); i++) {
                                        JsonObject jsonObject = (JsonObject) jsonArr.get(i);
                                        System.out.println(jsonObject);
                                        String nickname = jsonObject.get("nickname").getAsString();
                                        list.add(nickname);
                                    }
                                    String[] nickname = list.toArray(new String[list.size()]);
                                    if (nickname[2] != null){
                                        info3.setText(nickname[2]);
                                        friend3.setImageResource(R.drawable.profile3);
                                        Toast.makeText(getApplicationContext(), "친구와 연결되었습니다!", Toast.LENGTH_SHORT);
                                    }else{
                                        Toast.makeText(getApplicationContext(), "매칭가능한 친구가 없습니다!", Toast.LENGTH_SHORT);
                                    }
                                }
                            }catch (Exception e){
                                System.out.println(response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<FriendInformation> call, Throwable t) {
                            Log.d("Main", "onFailure: " + t.getMessage());
                        }
                    });

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
