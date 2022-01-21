package com.example.teddybuddy;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SignUpRequest extends StringRequest {
    final static private String URL = "http://localhost:8080/";
    private Map<String, String> map;

    public SignUpRequest(String userId, String userPw, String childName, int childAge, String nickName, String parentName, String parentTel, String rb, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",userId);
        map.put("userPassword", userPw);
        map.put("userName", childName);
        map.put("userAge", childAge + "");
        map.put("nickName", nickName);
        map.put("parentName", parentName);
        map.put("parentTel", parentTel);
        map.put("childGender", rb);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
