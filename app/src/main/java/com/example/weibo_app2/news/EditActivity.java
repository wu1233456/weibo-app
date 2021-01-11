package com.example.weibo_app2.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.example.weibo_app2.R;
import com.example.weibo_app2.request.RequestApi;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class EditActivity extends AppCompatActivity {
    Button savebutton;
    private Integer userId;
    private RequestApi requestApi=new RequestApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        //取出用户id
        SharedPreferences sharedPre=getSharedPreferences(FILENAME, MODE_PRIVATE);
        userId= Integer.parseInt(sharedPre.getString("userId", ""));
        System.out.println("取出的用户id："+userId);

        savebutton =findViewById(R.id.button2);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText news = findViewById(R.id.editnews);
                String newsContent = news.getText().toString();
                System.out.println(newsContent);
                saveNews(newsContent);
            }
        });

    }


    private void saveNews(String newsContent){
       requestApi.saveNewsApi(newsContent, userId, new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {

           }

           @Override
           public void onResponse(Call call, Response response) throws IOException {
               String res=response.body().string();

               //从json中转换数据成listnews
               Map<String,Object> map = JSON.parseObject(res, Map.class);
               Integer error = (Integer) map.get("error");

               if (error>=0){
                   Intent i=new Intent(EditActivity.this, ListActivity.class);
                   startActivity(i);
               }
           }
       });
    }
}
