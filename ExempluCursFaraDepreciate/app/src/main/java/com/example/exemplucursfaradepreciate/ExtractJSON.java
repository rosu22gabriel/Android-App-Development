package com.example.exemplucursfaradepreciate;

import android.os.AsyncTask;

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
import java.util.List;

public abstract class ExtractJSON extends AsyncTask<URL, Void, String> {
    public List<Comanda> listaComenziJSON = new ArrayList<>();

    @Override
    protected String doInBackground(URL... urls) {
        HttpURLConnection connection = null;
        try {
            // 1. Deschidere conexiune
            connection = (HttpURLConnection) urls[0].openConnection();
            connection.setRequestMethod("GET");
            InputStream ist = connection.getInputStream();

            // 2. Citire InputStream si conversie in String (StringBuilder este mai eficient)
            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);
            String linie = "";
            StringBuilder rezultat = new StringBuilder();

            while ((linie = br.readLine()) != null) {
                rezultat.append(linie);
            }

            // 3. Apelam metoda de parsare
            parsareJSON(rezultat.toString());
            return rezultat.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) connection.disconnect();
        }
    }

    private void parsareJSON(String jsonStr) {
        if (jsonStr != null && !jsonStr.isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                // "comenzi" este cheia radacina din fisierul JSON
                JSONArray array = jsonObject.getJSONArray("comenzi");

                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);

                    // Extragem valorile folosind cheile din JSON
                    String client = obj.getString("client");
                    String adresa = obj.getString("adresa");
                    float pret = (float) obj.getDouble("pret");
                    String metodaPlata = obj.getString("metodaPlata");

                    Comanda comanda = new Comanda(client, adresa, pret, metodaPlata);
                    listaComenziJSON.add(comanda);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
