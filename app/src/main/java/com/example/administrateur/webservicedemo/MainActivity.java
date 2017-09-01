package com.example.administrateur.webservicedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText txtCode;
    private TextView txtResult;
    private TextView editText2;
    private ListView lstresult;
    private String url;


    private final String URL_WS1 = "http://services.groupkt.com/country/get/iso2code/";
        private final String URL_WS2 = "http://services.groupkt.com/country/get/all";
    ArrayList<String> pays = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private boolean trouve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCode = (EditText) findViewById(R.id.txtCode);
        txtResult = (TextView) findViewById(R.id.txtResult);
        editText2 = (TextView) findViewById(R.id.editText2);
        lstresult = (ListView) findViewById(R.id.lstresult);

        adapter = new ArrayAdapter<String>(this, R.layout.liste_item, pays);

    }

    public void search(View v) throws Exception {

        String code = txtCode.getText().toString();
        String adr = URL_WS1 + code;

        HTTPClient hc = new HTTPClient();
        hc.setUrl(adr);
        hc.start();
        hc.join();

        JSONObject jo = new JSONObject(hc.getResponse());
        JSONObject jo2 = jo.getJSONObject("RestResponse");
        JSONObject jo3 = jo2.getJSONObject("result");

        String nom = jo3.getString("name");
        String alpha2 = jo3.getString("alpha2_code");
        String alpha3 = jo3.getString("alpha3_code");

        editText2.setText(nom);

    }

    public void tout(View v)   {

        try {
            String adr = URL_WS2;
            int i;

            HTTPClient hc = new HTTPClient();
            hc.setUrl(adr);
            hc.start();
            hc.join();

            JSONObject jo = new JSONObject(hc.getResponse());
            JSONObject jo2 = jo.getJSONObject("RestResponse");
            JSONArray  jo3 = jo2.getJSONArray("result");


            for (i = 0; i < jo3.length(); i++) {

                String name =jo3.getJSONObject(i).getString("name");
                 pays.add(name);

            }

            lstresult.setAdapter(adapter);
        }
        catch (Exception e){

        }
    }
}


