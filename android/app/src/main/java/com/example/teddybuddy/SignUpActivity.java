package com.example.teddybuddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private EditText userId, userPw, childName, childAge, parentName, parentTel, nickName;
    private RadioGroup rg;
    private Button signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {// 액티비티 시작시 처음으로 실행되는 생명주기!
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        String TAG = "MainActivity";

        // 아이디 값 찾아주기
        userId = findViewById(R.id.userId);
        userPw = findViewById(R.id.userPw);
        nickName = findViewById(R.id.nickName);
        childName = findViewById(R.id.childName);
        parentName = findViewById(R.id.parentName);
        parentTel = findViewById(R.id.parentTel);
        signup_btn = findViewById(R.id.signup_btn);
        childAge = findViewById(R.id.childAge);
        rg = findViewById(R.id.rg);

        // 회원가입 버튼 클릭 시 수행
        signup_btn = findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에 현재 입력되어있는 값을 get(가져온다)해온다.
                String userID = userId.getText().toString();
                String userPW = userPw.getText().toString();
                String childname = childName.getText().toString();
                int childage = Integer.parseInt(childAge.getText().toString());
                String nickname = nickName.getText().toString();
                String parenttel = parentTel.getText().toString();
                String parentname = parentName.getText().toString();
                int id = rg.getCheckedRadioButtonId();
                RadioButton childGender = (RadioButton)findViewById(id);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://localhost:8080/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitService service = retrofit.create(RetrofitService.class);
                Call<PostResult> call = service.getSignup("1");

                call.enqueue(new Callback<PostResult>() {
                    @Override
                    public void onResponse(Call<PostResult> call, retrofit2.Response<PostResult> response) {
                        try {
                            JSONObject jsonObject = new JSONObject();
                            boolean success = jsonObject.getBoolean("success");
                            if (success) { // 회원등록에 성공한 경우
                                Toast.makeText(getApplicationContext(), "회원 등록에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                startActivity(intent);
                            } else { // 회원등록에 실패한 경우
                                Toast.makeText(getApplicationContext(), "회원 등록에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<PostResult> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });
            }
        });
    }

}
