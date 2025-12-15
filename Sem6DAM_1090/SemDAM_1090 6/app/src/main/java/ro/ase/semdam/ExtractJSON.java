package ro.ase.semdam;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ExtractJSON extends AsyncTask<URL, Void, String> {

    public List<BiletAvion> biletAvionListJSON = new ArrayList<>();

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
                rezultat+= linie;

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
                JSONArray bilete = jsonObject.getJSONArray("bilete");
                for(int i=0;i<bilete.length();i++)
                {
                    JSONObject obj = bilete.getJSONObject(i);

                    String destinatie = obj.getString("Destinatie");
                    Date dataZbor = new Date(obj.getString("DataZbor"));
                    float pret = Float.parseFloat(obj.getString("Pret"));
                    String companie = obj.getString("Companie");
                    String clasaBilet = obj.getString("ClasaBilet");

                    BiletAvion biletAvion = new BiletAvion(destinatie, dataZbor, pret, companie, clasaBilet);
                    biletAvionListJSON.add(biletAvion);
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        else
            Log.e("parsareJSON", "JSON este null!");
    }
}
