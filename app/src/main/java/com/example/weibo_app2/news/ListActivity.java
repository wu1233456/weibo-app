package com.example.weibo_app2.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import com.alibaba.fastjson.JSON;
import com.example.weibo_app2.R;
import com.example.weibo_app2.UserActivity;
import com.example.weibo_app2.pojo.News;
import com.example.weibo_app2.request.RequestApi;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Response;

public class ListActivity extends AppCompatActivity {
    private RequestApi requestApi=new RequestApi();

    private List<News> listnews=new ArrayList<>();
    ImageButton edit;
    ImageButton user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化数据
        try {
            initData();
        } catch (IOException e) {
            e.printStackTrace();
        }


        setContentView(R.layout.activity_list);
        RecyclerView recyclerView=findViewById(R.id.newlist);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //实现纵向瀑布流
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        ListAdapter listAdapter = new ListAdapter(listnews,ListActivity.this);
        recyclerView.setAdapter(listAdapter);



        //下方导航栏
        edit =findViewById(R.id.add);
        user=findViewById(R.id.user);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ListActivity.this, EditActivity.class);
                startActivity(i);
            }
        });
        user.setOnClickListener(new View.OnClickListener() {
            /**
             * @param view
             */
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ListActivity.this, UserActivity.class);
                startActivity(i);
            }
        });



    }

    private void initData() throws IOException {
        Response response = requestApi.getListNewsApi();
        String res=response.body().string();

        //从json中转换数据成listnews
        Map<String,Object> map = JSON.parseObject(res, Map.class);
        Map<String,Object> data = (Map<String, Object>) map.get("data");
        List<News> list = (List<News>) data.get("list");

        listnews = JSON.parseArray(JSON.toJSONString(list), News.class);
    }
}
