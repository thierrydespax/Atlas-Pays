package com.example.administrateur.webservicedemo;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrateur on 31/08/2017.
 */

public class HTTPClient extends Thread  {
    private String url;
    private String response;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
    public String getResponse() {
        return response;
    }

public void run() {


        URL url;
        HttpURLConnection cnt = null;
    try {
        url=new URL(this.url);
        cnt=(HttpURLConnection) url.openConnection();
        InputStream stream = cnt.getInputStream();
        BufferedReader reader =new BufferedReader(new InputStreamReader(stream));
        response= "";
        String line ="";
        while ((line = reader.readLine()) != null) {
            response += line;

        }

        stream.close();
        reader.close();

    }catch (Exception ex) {
        response += "\nErreur : " + ex.getMessage();
    }

finally {
        cnt.disconnect();
    }
    }
}
