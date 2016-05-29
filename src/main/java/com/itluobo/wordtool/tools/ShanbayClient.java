package com.itluobo.wordtool.tools;


import com.google.gson.Gson;
import com.itluobo.wordtool.dto.ShanbayRespDTO;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by kenvi on 16/5/3.
 */
public class ShanbayClient {
    public static final String LOGIN_PAGE ="https://www.shanbay.com/accounts/login/";
    public static final String LOGIN_URL = "https://www.shanbay.com/accounts/login/";
    public static final String ADDWORD_URL = "https://www.shanbay.com/api/v1/wordlist/vocabulary/";

    CloseableHttpClient httpClient = null;



    public void login(String username, String password) throws Exception{
        BasicCookieStore cookieStore = new BasicCookieStore();
        httpClient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();

        HttpGet loginPage = new HttpGet(LOGIN_PAGE);
        CloseableHttpResponse loginResp = httpClient.execute(loginPage);
        String token = "";
        try{
            System.out.println(loginResp.getStatusLine());
            String content = EntityUtils.toString(loginResp.getEntity(), "UTF-8");
            Document document = Jsoup.parse(content, "UTF-8");
            Elements formText = document.select("#login-form-tmpl");
            if(formText.size() > 0) {
                Document formDocument = Jsoup.parse(formText.get(0).childNode(0).toString());
                Elements tokenEle = formDocument.select("[name=csrfmiddlewaretoken]");
                token = tokenEle.get(0).val();
            }

        }finally {
            loginResp.close();
        }



        HttpPost httpPost = new HttpPost(LOGIN_URL);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("csrfmiddlewaretoken", token));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(httpPost);

        try{
            System.out.println(response.getStatusLine());
            EntityUtils.consume(response.getEntity());
        }finally {
            response.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.load(ShanbayClient.class.getResourceAsStream("/config/app.properties"));
        ShanbayClient shanbayClient = new ShanbayClient();
        shanbayClient.login(props.getProperty("username"), props.getProperty("password"));
        boolean result = shanbayClient.addWord("test", 307015);
        System.out.println("result is :" + result);



    }

    public boolean addWord(String word, int chapterId) throws Exception{
        HttpPost addWordPost = new HttpPost(ADDWORD_URL);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("id", chapterId+""));
        params.add(new BasicNameValuePair("word", word));
        addWordPost.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(addWordPost);

        try {
            if(response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity());
                System.out.println("get result :[" +  word + "]" + result);

                ShanbayRespDTO respDTO = new Gson().fromJson(result, ShanbayRespDTO.class);

                if(respDTO.getStatus_code() == 0) {
                    return true;
                }else {
                    return false;
                }
            }else {
                throw new RuntimeException("invalid status:" + response.getStatusLine());
            }

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }



    }

}
