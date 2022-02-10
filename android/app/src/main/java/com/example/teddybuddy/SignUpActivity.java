package com.example.teddybuddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

public class SignUpActivity extends AppCompatActivity {
    public EditText userId, userPw, childName, childAge, parentName, parentTel, nickName;
    private RadioGroup rg;
    private Button signup_btn;
    private Spinner spinner1, spinner2, spinner3;
    public final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {// 액티비티 시작시 처음으로 실행되는 생명주기!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        ArrayAdapter adapter1 = ArrayAdapter.createFromResource(this, R.array.spinner1, android.R.layout.simple_spinner_item);
        ArrayAdapter adapter2 = ArrayAdapter.createFromResource(this, R.array.spinner2, android.R.layout.simple_spinner_item);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.spinner3, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner3.setAdapter(adapter3);

        userId = findViewById(R.id.userId);
        userPw = findViewById(R.id.userPw);
        childName = findViewById(R.id.childName);
        childAge = findViewById(R.id.childAge);
        parentName = findViewById(R.id.parentName);
        parentTel = findViewById(R.id.parentTel);
        nickName = findViewById(R.id.nickName);
        rg = findViewById(R.id.rg);

        signup_btn = findViewById(R.id.signup_btn);

        // 회원가입 버튼 클릭 시 수행
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                startSignup();
            }
        });
    }
        private void startSignup(){
            String id = userId.getText().toString();
            String password = userPw.getText().toString();
            String name = childName.getText().toString();
            String age = childAge.getText().toString();
            String nickname = nickName.getText().toString();
            String companionName = parentTel.getText().toString();
            String companionNum = parentName.getText().toString();
            int rgId = rg.getCheckedRadioButtonId();
            RadioButton rb = (RadioButton) findViewById(rgId);
            String gender = rb.getText().toString();
            String interests1st = spinner1.getSelectedItem().toString();
            String interests2nd = spinner2.getSelectedItem().toString();
            String interests3rd = spinner3.getSelectedItem().toString();


            RegisterInformation registerInformation = new RegisterInformation(id, password, name, nickname, age, gender, companionName, companionNum,  interests1st, interests2nd, interests3rd);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.105:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RegisterInterface registerInterface = retrofit.create(RegisterInterface.class);
            Call<RegisterInformation> call = registerInterface.getSignup(registerInformation);
            call.enqueue(new Callback<RegisterInformation>() {
                @Override
                public void onResponse( Call<RegisterInformation> call, Response<RegisterInformation> response) {
                    RegisterInformation body = response.body();
                    Log.d(TAG, "is: " + response.body().toString());
                    if (response.isSuccessful() && body.isSuccess()) { // 회원등록에 성공했을 경우
                        Log.d(TAG, "성공: " + new Gson().toJson(response.body()));
                        Log.d(TAG, "아이디: " + id + "비밀번호: " + password + "아동 이름: " + name + "아동 나이: " + age + "닉네임: " + nickname + "부모 번호: " + companionName + "부모 이름: " + companionNum + "선호 1: " + interests1st + "선호 2: " + interests2nd + "선호3: " + interests3rd + "성별: " + gender);
                        Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(intent);
                    } else { // 회원등록에 실패한 경우
                        Log.d(TAG, "실패");
                        Toast.makeText(getApplicationContext(), "회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                @Override
                public void onFailure(Call<RegisterInformation> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });
        }
    }
