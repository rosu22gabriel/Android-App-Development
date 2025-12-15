package com.example.rosugabriel;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExtractJSON extends AsyncTask<URL, Void, String> {

        public List<Membership> membershipListJSON = new ArrayList<>();

        @Override
        protected String doInBackground(URL... urls) {

            HttpURLConnection connection = null;
            try {
                connection = (HttpURLConnection) urls[0].openConnection();
                connection.setRequestMethod("GET");
                InputStream ist = connection.getInputStream();

                //var 2 - conversie String
                InputStreamReader isr = new InputStreamReader(ist);
                BufferedReader br = new BufferedReader(isr);
                String linie = null;
                String rezultat = "";
                while((linie = br.readLine())!=null)
                    rezultat += linie;

                //parsare JSON
                parsareJSON(rezultat);

                return rezultat;

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void parsareJSON(String jsonStr)
        {
            if(jsonStr!=null)
            {
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);
                    JSONArray abonamente = jsonObject.getJSONArray("abonamente");
                    for(int i=0;i<abonamente.length();i++)
                    {
                        JSONObject obj = abonamente.getJSONObject(i);
                        String nume = obj.getString("nume");
                        String tip = obj.getString("tip");

                        Membership abonament = new Membership(nume, 100.2, 12);
                        membershipListJSON.add(abonament);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            else
                Log.e("parsareJSON", "JSON este null!");
        }
}
