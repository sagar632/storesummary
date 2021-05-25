package com.example.storesummary;

import android.app.admin.DevicePolicyManager;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectionclass {


    private  static String ip,port,database,username,password,url;
    private static String classes="net.sourceforge.jtds.jdbc.Driver";
    private static Connection conn;
public static Connection connect(){

    ip="";
    port="1433";
    username= "testc";
    password="testc";
    database="Ac_DristiAuto7778";

    StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy);
    try {
        Class.forName(classes);
        url="jdbc:jtds:sqlserver://" + ip +":"+port+";"
                + "databaseName=" + database + ";user=" + username + ";password="
                + password + ";";
        conn= DriverManager.getConnection(url);
        Log.d("Message","success");

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        Log.d("Message","classnotfound");
    } catch (SQLException e) {
        e.printStackTrace();
        Log.d("Message","sql error");
        Log.d("hfdk",e.toString());
    }

    return conn;
}

}
