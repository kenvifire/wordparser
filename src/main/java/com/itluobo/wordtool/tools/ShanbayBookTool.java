package com.itluobo.wordtool.tools;


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
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenvi on 16/5/3.
 */
public class ShanbayBookTool {
    public static final String LOGIN_PAGE ="https://www.shanbay.com/accounts/login/";
    public static final String LOGIN_URL = "https://www.shanbay.com/accounts/login/";


    public void login(String username, String password) {

    }

    public static void main(String[] args) throws Exception {
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();

        HttpGet loginPage = new HttpGet(LOGIN_PAGE);
        CloseableHttpResponse loginResp = httpclient.execute(loginPage);
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
        params.add(new BasicNameValuePair("username", "*****"));
        params.add(new BasicNameValuePair("password", "*****"));
        params.add(new BasicNameValuePair("csrfmiddlewaretoken", token));
        httpPost.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpclient.execute(httpPost);

        try{
            System.out.println(response.getStatusLine());
            EntityUtils.consume(response.getEntity());
        }finally {
            response.close();
        }



    }
}
