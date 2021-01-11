package com.example.weibo_app2.pojo;

import java.io.Serializable;

public class News implements Serializable {
    private Integer id;
    private String content;
    private String time;
    private Integer userId;
    private String newswriter;
    private Integer commentsNum;

    public News() {
    }

    public News(Integer id, String content, String time, Integer userId, String newswriter, Integer commentsNum) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.userId = userId;
        this.newswriter = newswriter;
        this.commentsNum = commentsNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getNewswriter() {
        return newswriter;
    }

    public void setNewswriter(String newswriter) {
        this.newswriter = newswriter;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", userId=" + userId +
                ", commentsNum=" + commentsNum +
                '}';
    }
}
