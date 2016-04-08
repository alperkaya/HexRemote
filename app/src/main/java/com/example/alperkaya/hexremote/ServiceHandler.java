package com.example.alperkaya.hexremote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by alper.kaya on 08.04.2016.
 */
public class ServiceHandler {
    static InputStream is = null;
    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    public ServiceHandler(Context context) {
        ConnectivityManager check = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = check.getActiveNetworkInfo();

            if (!info.isConnected()){
                Toast.makeText(context, "Internet is not connected",
                        Toast.LENGTH_SHORT).show();
            }

    }



    /*
     * Making service call
     * @url - url to make request
     * @method - http request method
     * @params - http request params
     * */
    public String makeServiceCall(String url, int method) {
        URL mUrl = null;
        String webPage = "";

        if(method == GET){
            try {
                mUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
                conn.connect();

                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                String data = "";

                while ((data = reader.readLine()) != null) {
                    webPage += data + "\n";
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return webPage;
    }

}
