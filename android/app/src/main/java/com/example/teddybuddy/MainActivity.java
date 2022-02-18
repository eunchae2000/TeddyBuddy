package com.example.teddybuddy;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageButton speech_btn;
    Intent intent;
    TextView result_text;
    SpeechRecognizer mRecognizer;
    final int PERMISSION = 1;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 음성인식 할 수 있는 버전인지 체크
        if ( Build.VERSION.SDK_INT >= 23 ){
            // 퍼미션 체크
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET,
                    Manifest.permission.RECORD_AUDIO},PERMISSION);
        }

        speech_btn = findViewById(R.id.btn1);
        result_text = findViewById(R.id.result);

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");

        // 이미지 버튼을 누르면 음성인식 활성화
        speech_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecognizer = SpeechRecognizer.createSpeechRecognizer(MainActivity.this);
                mRecognizer.setRecognitionListener(listener);
                mRecognizer.startListening(intent);
            }
        });
    }
    private RecognitionListener listener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {
            Toast.makeText(getApplicationContext(),"음성인식 시작",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float v) {

        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        // 음성인식 도중에 에러가 발생할 경우 토스트 메세지로 출력
        @Override
        public void onError(int error) {
            String message;

            switch (error) {
                case SpeechRecognizer.ERROR_AUDIO:
                    message = "오디오 에러";
                    break;
                case SpeechRecognizer.ERROR_CLIENT:
                    message = "클라이언트 에러";
                    break;
                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    message = "퍼미션 없을때 에러";
                    break;
                case SpeechRecognizer.ERROR_NETWORK:
                    message = "네트워크 에러";
                    break;
                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    message = "네트워크 타임아웃";
                    break;
                case SpeechRecognizer.ERROR_NO_MATCH:
                    message = "음성인식을 할 수 있는 환경이 아닐때";
                    break;
                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    message = "RECOGNIZER 가 바쁨";
                    break;
                case SpeechRecognizer.ERROR_SERVER:
                    message = "서버가 이상함";
                    break;
                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    message = "말하는 시간초과";
                    break;
                default:
                    message = "알 수 없는 오류임";
                    break;

            }
            Toast.makeText(getApplicationContext(), "에러 발생 : " + message,Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onResults(Bundle results) {
            ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            for(int i = 0; i < matches.size() ; i++){
                result_text.setText(matches.get(i));
            }

            String nick1 = "\"" + matches.get(0) + "\"";
            System.out.println(nick1);

            Intent mainintent = getIntent();
            String nickname = mainintent.getStringExtra("nickname");
            String id = mainintent.getStringExtra("user_id");
            String interests1st = mainintent.getStringExtra("interests1st");
            String interests2nd = mainintent.getStringExtra("interests2nd");
            String interests3rd = mainintent.getStringExtra("interests3rd");

            System.out.println("nickname: "+nickname);
            System.out.println("user_id: "+id);
            System.out.println("interests1:" + interests1st);
            System.out.println("interests2:" + interests2nd);
            System.out.println("interests3:" + interests3rd);

            if (nickname.equals(nick1)){
                // 메인화면 1로 화면 넘김
                System.out.println("true");
                Intent intent = new Intent(MainActivity.this, MainActivity1.class);
                intent.putExtra("nickname", nickname);
                intent.putExtra("user_id", id);
                intent.putExtra("interests1st", interests1st);
                intent.putExtra("interests2nd", interests2nd);
                intent.putExtra("interests3rd", interests3rd);
                startActivity(intent);
            }else{
                System.out.println("false");
            }
        }

        @Override
        public void onPartialResults(Bundle bundle) {

        }

        @Override
        public void onEvent(int i, Bundle bundle) {

        }

        // 음성인식을 그만할 때
        public void stopSTT(){
            if(mRecognizer != null){
                mRecognizer.destroy();
                mRecognizer.cancel();
                mRecognizer = null;
            }
        }
    };
}