package com.futuregadgetlabs.hououinkyouma.paragoncentral;

import android.util.Log;
import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

/**
 * Parses JSON object into readable Hashmap.
 */
public class ParseJSON
{
    public void parseJson(String inputString)
    {
        try
        {
            JSONArray jsonArray = new JSONArray(inputString);

            Log.d("JSONArray Index 0", jsonArray.get(0).toString());
            Log.d("ID Number", jsonArray.getJSONObject(0).get("id").toString());
            Log.d("Name", jsonArray.getJSONObject(0).get("name").toString());
            Log.d("Slot Type", jsonArray.getJSONObject(0).get("slotType").toString());
            Log.d("Images", jsonArray.getJSONObject(0).get("images").toString());
        }
        catch (JSONException e)
        {
            Log.e("JSONException", "JSON error has occured.");
            e.printStackTrace();
        }
    }
}
