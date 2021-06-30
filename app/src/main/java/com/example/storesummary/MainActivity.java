package com.example.storesummary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {




  private ViewPager viewPager;
  private TextView all,credit,debit;
  private pageAdapter pageAdapter;



private  ProgressDialog progressDialog;
    private Connection connection = null;
public static String datname=null;
    public static String use=null,ms;
    public static String pas=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      all=findViewById(R.id.all);
      credit=findViewById(R.id.credit);
      debit=findViewById(R.id.debit);
viewPager=findViewById(R.id.fragmentcontainer);
Intent intent=getIntent();

setDatname(intent.getStringExtra("dataname"));
setUse(intent.getStringExtra("username"));
setPas(intent.getStringExtra("password"));Log.d("ere","jfkjdfjkdkdfk");
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Connecting");
        progressDialog.show();
         ms=intent.getStringExtra("username");
        Log.d("fromf",ms);
connection=Connectionclass.connect("192.168.254.95","1433",getUse(),getPas(),getDatname());

if(isOnline()) {
    if (connection != null) {
        progressDialog.dismiss();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select gname1 as GroupName,sum(abs(bal)) as Balance,DRORCR from LedgerBalance group by gname1,DRORCR order by GNAME1");
            while (resultSet.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
else {
    progressDialog.setCancelable(false);
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
        public void run() {
            progressDialog.dismiss();

            try {
                new AlertDialog.Builder(MainActivity.this)
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
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);

            }
        });
        debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
        credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(2);
            }
        });
pageAdapter=new pageAdapter(getSupportFragmentManager());
viewPager.setAdapter(pageAdapter);

viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
onchangetab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
});
    }

    private boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            return false;
        }
        return true;
    }

    public static String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public static String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    private void onchangetab(int position) {
        if(position==0){
            all.setTextSize(25);
            credit.setTextSize(20);
            debit.setTextSize(20);
        }
        if(position==1){
            all.setTextSize(20);
            credit.setTextSize(20);
            debit.setTextSize(25);
        }
        if(position==2){
            all.setTextSize(20);
            credit.setTextSize(25);
            debit.setTextSize(20);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        connection=null;
    }

    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();


    }

    @Override
    protected void onResume() {
        super.onResume();
        connection=null;
    }

    public static String getDatname() {
        return datname;
    }

    public void setDatname(String datname) {
        this.datname = datname;
    }
}
