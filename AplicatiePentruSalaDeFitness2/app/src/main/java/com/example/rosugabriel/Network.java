package com.example.rosugabriel;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Network extends AsyncTask<URL, Void, InputStream> {

    InputStream ist = null;
    static String rezultat = "";

    @Override
    protected InputStream doInBackground(URL... urls) {

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) urls[0].openConnection();
            connection.setRequestMethod("GET");
            ist = connection.getInputStream();

            //var 1 - parsare XML
            Parsing(ist);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ist;
    }

    public static Node getNodeByName(String nodeName, Node parentNode)
            throws Exception {
        if (parentNode.getNodeName().equals(nodeName))
            return parentNode;
        NodeList list = parentNode.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = getNodeByName(nodeName, list.item(i));
            if (node != null)
                return node;
        }
        return null;
    }

    public static String getAttributeValue(Node node, String attrName) {
        try {
            return ((Element) node).getAttribute(attrName);
        } catch (Exception ex) {
            return "";
        }
    }

    public void Parsing(InputStream ist) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document domDoc = db.parse(ist);
            domDoc.getDocumentElement().normalize();


            Node cube = getNodeByName("Cube", domDoc.getDocumentElement());
//            if(cube!=null)
//            {
//            }
        } catch (
                ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
