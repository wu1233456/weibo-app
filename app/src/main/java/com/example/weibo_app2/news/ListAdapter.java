package com.example.weibo_app2.news;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weibo_app2.R;
import com.example.weibo_app2.pojo.News;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<News> newslist;
    //页面跳转时需要
    private Context context;
    //定义一个内部类
    static class ViewHolder extends RecyclerView.ViewHolder{
        View newsView;
        TextView content;
        TextView time;
        TextView newswriter;
        TextView commentsnum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsView=itemView;
            content = itemView.findViewById(R.id.content);
            time=itemView.findViewById(R.id.time);
            newswriter=itemView.findViewById(R.id.newswriter);
            commentsnum=itemView.findViewById(R.id.commentsnum);

        }
    }


    public ListAdapter(List<News> news,Context context) {
        this.newslist = news;
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);

        final  ViewHolder holder=new ViewHolder(view);
        holder.newsView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                News news = newslist.get(position);

                System.out.println("点我啦");
                System.out.println(news);

                //向详细页面传递该新闻的id
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("id",news.getId());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        News news = newslist.get(position);
        holder.content.setText(news.getContent());
        holder.time.setText(news.getTime());
        holder.newswriter.setText(news.getNewswriter());
        holder.commentsnum.setText(news.getCommentsNum().toString());
    }

    @Override
    public int getItemCount() {
        return newslist.size();
    }
}
