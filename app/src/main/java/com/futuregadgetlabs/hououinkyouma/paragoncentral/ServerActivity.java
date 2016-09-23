package com.futuregadgetlabs.hououinkyouma.paragoncentral;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.ImageReader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerActivity extends Activity
{
    private String jsonText;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Sets layout to activity_main.xml layout file

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    private void setString(String input)
    {
        Log.d("Set String", input);

        TextView myText = (TextView) findViewById(R.id.myText);
        myText.setText(input);

        Log.d("Set TextView String", myText.getText().toString());

        ParseJSON parser = new ParseJSON();
        parser.parseJson(input);
    }

    private class MyAsyncTask extends AsyncTask<String, String, String>
    {
        private ProgressDialog progressDialog = new ProgressDialog(ServerActivity.this);
        InputStream inputStream = null;
        String result = "";
        HttpURLConnection conn;

        /*protected void onPreExecute()
        {
            progressDialog.setMessage("Downloading your data...");
            progressDialog.show();
            progressDialog.setOnCancelListener(new SearchManager.OnCancelListener() {
                public void onCancel(DialogInterface arg0) {
                    MyAsyncTask.this.cancel(true);
                }
            });
        } */

        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                // Set up HTTP post
                URL url = new URL("https://developer-paragon.epicgames.com/v1/cards"); /** Need to set variable URL **/
                conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestProperty("Accept", "application/json");
                conn.setRequestProperty("X-Epic-ApiKey", "ec645e76023146329ce4319bd6ac9a2b");

                int responseCode = conn.getResponseCode();
                String response = Integer.toString(responseCode);

                Log.d("HTTP Response Code", response);

                inputStream = conn.getInputStream();
            }
            catch (IOException e)
            {
                Log.e("IOException", e.toString());
                e.printStackTrace();
            }

            // Convert response to string using String Builder
            try
            {
                BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                StringBuilder sBuilder = new StringBuilder();

                String line = null;
                while ((line = bReader.readLine()) != null)
                {
                    sBuilder.append(line + "\n");
                }

                inputStream.close();
                result = sBuilder.toString();

                Log.d("Result from Stream", result);
            }
            catch (Exception e)
            {
                Log.e("StringBuilding", "Error converting result " + e.toString());
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s)
        {
            Log.d("Post Execute String", s);
            setString(s);
        } // protected void onPostExecute(Void v)
    }
}

