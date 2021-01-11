package com.example.weibo_app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.weibo_app2.news.ListActivity;
import com.example.weibo_app2.request.RequestApi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.provider.Telephony.Mms.Part.FILENAME;


public class MainActivity extends AppCompatActivity {
    Button login;
    TextView showResponse;
    EditText passwd;
    EditText user;
    ImageView image1;

    private RequestApi requestApi=new RequestApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=findViewById(R.id.button);
        showResponse=findViewById(R.id.s_response);
        passwd=findViewById(R.id.passwd);
        user=findViewById(R.id.user);
        image1=findViewById(R.id.tubiao);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    connectServer();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        });
    }
    public void connectServer() throws UnsupportedEncodingException {
        final String name = user.getText().toString().trim();
        final String psw = passwd.getText().toString().trim();

        String username=URLEncoder.encode(name,"UTF-8");
        String password= URLEncoder.encode(psw,"UTF-8");

        requestApi.LoginApi(username,password,
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println("请求异常");
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String res=response.body().string();
                        Map<String,Object> objectMap = JSON.parseObject(res,Map.class);
                        int error = (int) objectMap.get("error");
                        String errmsg = (String) objectMap.get("errmsg");
                        //获取用户id
                        Map<String,Object> data = (Map<String, Object>) objectMap.get("data");
                        Integer userId = (Integer) data.get("userId");
                        System.out.println("用户Id是："+userId);
                        if (error<0){
                            //解决在子线程中调用Toast的异常情况处理
                            Looper.prepare();
                            Toast.makeText(MainActivity.this, errmsg, Toast.LENGTH_SHORT).show();
                            Looper.loop();
                        }else {
                            //登录成功，存储返回的用户id
                            SharedPreferences sharedPre = getSharedPreferences(FILENAME, MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPre.edit();
                            editor.putString("userId", String.valueOf(userId));
                            editor.commit();

                            //页面跳转
                            Intent i=new Intent(MainActivity.this, ListActivity.class);
                            startActivity(i);
                        }
                    }
                });
    }

}
