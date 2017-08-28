package com.masterarbeit.dbOperations;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jan-philippheinrich on 22.08.17.
 */
public class DatabaseOperations {

    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public List<String> readDBTables() throws Exception{
        List tablenames = new LinkedList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zahnklinik?user=root&password=Boliga12.");

            DatabaseMetaData md = conn.getMetaData();

            resultSet = md.getTables(null,null,"%",null);

            while (resultSet.next()){
                System.out.println(resultSet.getString(3));
                tablenames.add(resultSet.getString(3));
            }

        } catch (Exception e){
            throw e;
        }
        finally {
            conn.close();
        }
        return tablenames;

    }


    public List<ArrayList> getTable(String tablename) throws Exception{

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zahnklinik?user=root&password=Boliga12.");
        Statement stat = conn.createStatement();
        resultSet = stat.executeQuery("SELECT * FROM " + tablename);
        ResultSetMetaData md = resultSet.getMetaData();
        int length = md.getColumnCount();
        System.out.println(length);
        HashMap<String, String> date = null;
        ArrayList<HashMap> entry = new ArrayList<>();
        List<ArrayList> table = new ArrayList<>();

        while (resultSet.next()){
            date = new HashMap<>();
            for (int i=1; i<=length;i++) {
                System.out.println(md.getColumnName(i) + " - " + resultSet.getString(i) + " - " + md.getColumnClassName(i));
                date.put(md.getColumnName(i), resultSet.getString(i));
            }
            entry.add(date);
            System.out.println("-----");
        }
        table.add(entry);
        conn.close();
        return table;
    }




}
