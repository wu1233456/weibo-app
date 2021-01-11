package com.example.weibo_app2.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.weibo_app2.R;
import com.example.weibo_app2.pojo.News;
import com.example.weibo_app2.request.RequestApi;

import java.io.IOException;
import java.util.Map;

import okhttp3.Response;

public class DetailActivity extends AppCompatActivity {

    private RequestApi requestApi=new RequestApi();

    TextView content;
    TextView time;
    TextView newswriter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        System.out.println("我来到详情页面了");
        System.out.println(id);

        newswriter=findViewById(R.id.detailname);
        content = findViewById(R.id.detailcontent);

        //初始化页面数据
        try {
            initData(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initData(int id) throws IOException {
        Response response = requestApi.getDetailApi(id);
        String res=response.body().string();

        //从json中转换数据成listnews
        Map<String,Object> map = JSON.parseObject(res, Map.class);
        Map<String,Object> data = (Map<String, Object>) map.get("data");
        System.out.println(data);

        Object news = data.get("news");
        News news1 = JSON.parseObject(JSON.toJSONString(news), News.class);
        System.out.println(news1);

        newswriter.setText(news1.getNewswriter());
        content.setText(news1.getContent());
    }
}
