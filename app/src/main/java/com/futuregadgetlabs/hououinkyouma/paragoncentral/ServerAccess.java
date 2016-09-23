package com.futuregadgetlabs.hououinkyouma.paragoncentral;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Establishes connection to server.
 */
public class ServerAccess extends Activity
{
    public void accessServer() throws IOException
    {
        URL url = new URL(getString(R.string.cardsURL));

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setReadTimeout(10000);
        conn.setConnectTimeout(15000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("X-Epic-ApiKey", "ec645e76023146329ce4319bd6ac9a2b");
        //conn.connect();

        //String response = conn.getResponseMessage();

        //Toast.makeText(this, response, Toast.LENGTH_LONG).show();

    }

    private String urlBuilder()
    {
        String baseURL = "https://developer-paragon.epicgames.com/v1/";

        return getFullURL(baseURL);
    }

    private String[] headerBuilder()
    {
        String[] header = {"Accept", "application/json"};

        return header;
    }

    private String[] authorizationBuilder()
    {
        String[] auth = {"X-Epic-ApiKey", "ec645e76023146329ce4319bd6ac9a2b"};

        return auth;
    }

    private String getFullURL(String bURL)
    {
        return bURL + "cards/complete";
    }
}
