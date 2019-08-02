package io.github.whyareyousoseriously.czcommonutils.util;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;


/**
 * 模拟发送get和post请求
 *
 * @author chenzhen
 * Created by chenzhen on 2018/12/18.
 */
@Slf4j
public class HttpUtil {

    @Deprecated
    public static String sendPost(String url, String params) throws IOException {
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        String response = "";
        try {
            //HTTP URL类 用这个类来创建连接
            URL httpUrl = null;
            //创建URL
            httpUrl = new URL(url);
            //建立连接
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("connection", "keep-alive");
            //设置不要缓存
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(10000);
            conn.connect();
            //POST请求
            out = new OutputStreamWriter(
                    conn.getOutputStream());
            //防止发送中文乱码
            String sendContent = new String(params.getBytes(),Charset.forName("UTF-8"));
            out.write(sendContent);
            out.flush();
            //读取响应
            reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                response += lines;
            }
            reader.close();
            // 断开连接
            conn.disconnect();

            //log.info(response.toString());
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return response;
    }

    @Deprecated
    public static String sendPostV2(String postUrl,String params,String headers){
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpResponse httpResponse = null;
        //建立一个连接
        try {
            HttpPost httpPost = new HttpPost(postUrl);
            //这个防止中文???，很重要，很重要，很重要
            httpPost.setEntity(new StringEntity(params, Charset.forName("UTF-8")));
            //设置头信息
            JSONObject jsonObject = JSONObject.parseObject(headers);
            Set<String> keys = jsonObject.keySet();
            for (String key:keys){
                httpPost.setHeader(key,jsonObject.getString(key));
            }
            //发送
            httpResponse = closeableHttpClient.execute(httpPost);
            int returnCode = httpResponse.getStatusLine().getStatusCode();
            if (returnCode== HttpStatus.SC_OK){
                InputStream inputStream = httpResponse.getEntity().getContent();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    stringBuffer.append(line);
                }
                return stringBuffer.toString();
            }
        }catch (IOException e){
            e.printStackTrace();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code","404");
            jsonObject.put("result","no result");
            return jsonObject.toJSONString();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code","404");
        jsonObject.put("result",httpResponse);
        return jsonObject.toJSONString();
    }

    @Deprecated
    public static String sendGet(String getUrl, Map<String,String> headers) {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        //建立一个连接
        try {
            //创建一个httpGet
            HttpGet httpGet = new HttpGet(getUrl);
            //设置头
            for (String key : headers.keySet()){
                httpGet.setHeader(key,headers.get(key));
            }
            HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
            InputStream content = httpResponse.getEntity().getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            httpGet.releaseConnection();
            return stringBuffer.toString();
        } catch (IOException e) {
            log.error(e.getMessage());
            return "";
        }finally {
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }
}
