package com.example.storesummary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.balance;

public class moredetails extends AppCompatActivity {
    private ImageView left;private TextView text;private Connection conn;private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moredetails);
        Intent intent=getIntent();
        String s=intent.getStringExtra("name");
        left=findViewById(R.id.lefticon);
        text=findViewById(R.id.actname);

        text.setText(s);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
        progressDialog=new ProgressDialog(getApplicationContext());
        progressDialog.setMessage("Connecting");

        Connectionclass connectionclass=new Connectionclass();
        conn=connectionclass.connect("192.168.254.95","1433",MainActivity.getUse(),MainActivity.getPas(),MainActivity.getDatname()); final List<balance> balncelist  =new ArrayList<>();
        if(isonline()) {
            if (conn != null) {
                progressDialog.dismiss();
                Statement statement = null;

                try {
                    statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery("select VMITI AS Date,Particular,Dr,Cr,Narration  from ledger where LNAME='" + s + "' order by VMITI desc;");
                    while (resultSet.next()) {
                        balncelist.add(new model.balance(resultSet.getString(2), Double.parseDouble(resultSet.getString(4)), Double.parseDouble(resultSet.getString(3)),
                                resultSet.getString(5), resultSet.getString(1)));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        
        else {
            progressDialog.show();
            progressDialog.setCancelable(false);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    progressDialog.dismiss();

                    try {
                        new AlertDialog.Builder(getApplicationContext())
                                .setTitle("Error")
                                .setMessage("Internet not available, Cross check your internet connectivity and try again later...")
                                .setCancelable(false)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();

                                    }
                                }).show();
                    } catch (Exception e) {

                    }
                }
            }, 15000);
        }
        recyclerView=findViewById(R.id.moredetailsrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
      recyclerView.setAdapter(new moredetailsrecycler(balncelist));










    }

    private boolean isonline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            return false;
        }
        return true;
    }
}
