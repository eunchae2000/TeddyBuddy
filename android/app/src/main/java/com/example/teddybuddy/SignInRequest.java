package com.example.teddybuddy;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SignInRequest extends StringRequest{
    // 서버 URL 설정 ( PHP 파일 연동 )
    final static private String URL = "http://localhost:8080/";
    private Map<String, String> map;

    public SignInRequest(String userId, String userPw, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userId",userId);
        map.put("userPw", userPw);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
