package com.example.weibo_app2.request;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestApi {

    private final String BaseUrl="http://10.0.2.2:8080/api";

    /**
     * 登录接口
     * POST请求
     * @param username 用户名
     * @param password 密码
     */
    public void LoginApi(String username, String password,Callback callback) {

        String url=BaseUrl+"/login";

        // 1.拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //2.创建 FormBody 添加需要的键值对
        FormBody formBody = new FormBody.Builder()
                .add("username",username)
                .add("password",password)
                .build();

        // 3.构造Request
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)//键值对
                .build();

        //4.创建一个Call对象
        Call call = okHttpClient.newCall(request);
        //5.异步请求enqueue(Callback)
        call.enqueue(callback);
    }

    /**
     * 获取微博信息列表
     * @return
     * @throws IOException
     */
    public Response getListNewsApi() throws IOException {
        String url=BaseUrl+"/news/getlist";

        // 1.拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();


        // 3.构造Request

        Request request = new Request.Builder()
                .url(url)
                .build();

        //4.创建一个Call对象
        Call call = okHttpClient.newCall(request);
        Response res = call.execute();
        return res;
    }

    /**
     * 获取新闻的详细内容
     * @param id
     */
    public Response getDetailApi(int id) throws IOException {

        String url=BaseUrl+"/news/getdetail";

        // 1.拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //2.创建 FormBody 添加需要的键值对
        FormBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(id))
                .build();

        // 3.构造Request
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)//键值对
                .build();

        Call call = okHttpClient.newCall(request);
        Response res = call.execute();
        return res;
    }

    /**
     * 发布微博动态
     * @param content
     * @param userId
     * @param callback
     */
    public void saveNewsApi(String content, Integer userId,Callback callback) {

        String url=BaseUrl+"/news/save";

        // 1.拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //2.创建 FormBody 添加需要的键值对
        FormBody formBody = new FormBody.Builder()
                .add("content",content)
                .add("userId",userId.toString())
                .build();

        // 3.构造Request
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)//键值对
                .build();

        //4.创建一个Call对象
        Call call = okHttpClient.newCall(request);
        //5.异步请求enqueue(Callback)
        call.enqueue(callback);
    }

    /**
     * 发布评论
     * @param content
     * @param newsId
     * @param userId
     * @param callback
     */
    public void saveCommentApi(String content,Integer newsId, Integer userId,Callback callback) {

        String url=BaseUrl+"/comment/save";

        // 1.拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();

        //2.创建 FormBody 添加需要的键值对
        FormBody formBody = new FormBody.Builder()
                .add("content",content)
                .add("userId",userId.toString())
                .add("newsId",newsId.toString())
                .build();

        // 3.构造Request
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)//键值对
                .build();

        //4.创建一个Call对象
        Call call = okHttpClient.newCall(request);
        //5.异步请求enqueue(Callback)
        call.enqueue(callback);
    }

    /**
     *获取微博动态下的全部动态
     * @param id 微博动态id
     * @return
     * @throws IOException
     */
    public Response getCommentsApi(Integer id) throws IOException {
        String url=BaseUrl+"/comment/getlist";

        // 1.拿到okhttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();


        //2.创建 FormBody 添加需要的键值对
        FormBody formBody = new FormBody.Builder()
                .add("id", String.valueOf(id))
                .build();

        // 3.构造Request
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)//键值对
                .build();


        //4.创建一个Call对象
        Call call = okHttpClient.newCall(request);
        Response res = call.execute();
        return res;
    }

}
