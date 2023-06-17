package CQ;

import CQ.TestNg;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetHttpResponse{
    public int code(String url) throws MalformedURLException, IOException {
        TestNg T = new TestNg();
        HttpURLConnection cn = (HttpURLConnection)new URL(url).openConnection();
        int res = cn.getResponseCode();
       return res;

    }
}