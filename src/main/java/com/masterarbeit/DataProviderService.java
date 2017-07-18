package com.masterarbeit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by jan-philippheinrich on 02.07.17.
 */

public class DataProviderService {


    private HttpResponse getResponse(String url) throws IOException {

        HttpClient client = new DefaultHttpClient();
        HttpGet req = new HttpGet(url);
        HttpResponse res = client.execute(req);
        return res;
    }


    public ArrayList<String> getFirst_Names(int amount) throws IOException {

        String url = "https://randomuser.me/api/?results="+ amount + "&inc=name&nat=de&noinfo";
        ArrayList<String> names = new ArrayList<String>();
        HttpResponse res = getResponse(url);
        BufferedReader reader = new BufferedReader (new InputStreamReader(res.getEntity().getContent()));
        StringBuilder builder = new StringBuilder();
        String line="";
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }

        JSONObject json = new JSONObject(builder.toString());
        JSONArray results = json.getJSONArray("results");
        for(int i=0; i<results.length(); i++) {
            JSONObject person = results.getJSONObject(i);
            JSONObject name = person.getJSONObject("name");
            String first = name.getString("first");
            names.add(first);
        }
        return names;
    }

    public ArrayList<String> getLast_Names(int amount) throws IOException {

        ArrayList<String> names = new ArrayList<String>();
        String url = "https://randomuser.me/api/?results="+ amount + "&inc=name&nat=de&noinfo";
        HttpResponse res = getResponse(url);
        BufferedReader reader = new BufferedReader (new InputStreamReader(res.getEntity().getContent()));
        StringBuilder builder = new StringBuilder();
        String line="";
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }

        JSONObject json = new JSONObject(builder.toString());
        JSONArray results = json.getJSONArray("results");
        for(int i=0; i<results.length(); i++) {
            JSONObject person = results.getJSONObject(i);
            JSONObject name = person.getJSONObject("name");
            String last = name.getString("last");
            names.add(last);
        }
        return names;
    }

    public ArrayList<String> getMailAddress(int amount) throws IOException {

        ArrayList<String> mailAdress = new ArrayList<String>();
        String url = "https://randomuser.me/api/?results="+ amount + "&inc=email&nat=de&noinfo";
        HttpResponse res = getResponse(url);
        BufferedReader reader = new BufferedReader (new InputStreamReader(res.getEntity().getContent()));
        StringBuilder builder = new StringBuilder();
        String line="";
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }

        JSONObject json = new JSONObject(builder.toString());
        JSONArray results = json.getJSONArray("results");
        for(int i=0; i<results.length(); i++) {
            JSONObject person = results.getJSONObject(i);

            String email = person.getString("email");
            System.out.println(email);
            mailAdress.add(email);
        }
        return mailAdress;
    }

    public ArrayList<Long> getPhonenumber(int amount) throws IOException {

        ArrayList<Long> phonenumber = new ArrayList<Long>();
        String url = "https://randomuser.me/api/?results="+ amount + "&inc=phone&nat=de&noinfo";
        HttpResponse res = getResponse(url);
        BufferedReader reader = new BufferedReader (new InputStreamReader(res.getEntity().getContent()));
        StringBuilder builder = new StringBuilder();
        String line="";
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }

        JSONObject json = new JSONObject(builder.toString());
        JSONArray results = json.getJSONArray("results");
        for(int i=0; i<results.length(); i++) {
            JSONObject person = results.getJSONObject(i);
            String temp = person.getString("phone").replaceAll("[^1-9]", "");
            System.out.println(temp);
            Long phone = Long.parseLong(temp);
            phonenumber.add(phone);
        }
        return phonenumber;
    }





}
