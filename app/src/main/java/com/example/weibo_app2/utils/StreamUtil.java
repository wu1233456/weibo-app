package com.example.weibo_app2.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtil {
    public static String readInputStream(InputStream is){
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while((len=is.read(buffer))!=-1){
                stream.write(buffer, 0, len);
            }
            is.close();
            stream.close();
            byte[] result = stream.toByteArray();
            //试着解析 result 里面的字符串
            String temp = new String(result);
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败";
        }

    }
}
