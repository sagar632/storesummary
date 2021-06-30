package com.example.storesummary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main2Activity extends AppCompatActivity {
EditText codee,usernamee,passworde;
Button signin; String dab,use,pas; ProgressBar progressBar;
private Connection conn=null;private AlertDialog.Builder alertDialog;
private ProgressDialog progressdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         progressdialog = new ProgressDialog(Main2Activity.this);  progressdialog.setMessage("Please Wait....");progressdialog.setCancelable(false);
        codee=findViewById(R.id.code);
        usernamee=findViewById(R.id.username);
        passworde=findViewById(R.id.password);
        alertDialog=new AlertDialog.Builder(this);
        signin=findViewById(R.id.loginbutton);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                progressdialog.show();
                if(validate()){


                    String codes=codee.getText().toString();
                    String users=usernamee.getText().toString();
                    String passwords=passworde.getText().toString();
                    login(codes,users,passwords);


                }
                else{


                }
            }
        });


















    }

public void login(String code,String username,String passsword){
    progressBar.setVisibility(View.VISIBLE);
Intent intent=null;
        Connectionclass connectionclass=new Connectionclass();
        conn=connectionclass.connect("192.168.254.101","1433","testc","testc","Login");
        if(conn!=null){
            Statement statement=null;

            try {

                statement=conn.createStatement();
                ResultSet resultSet=statement.executeQuery("select * from userdetails where code='"+code+"' and userid='"+username+"' and password='"+passsword+"'");
                while(resultSet.next()){
                    Log.d("databasename",resultSet.getString(2 ));
                    dab=resultSet.getString(4);
                    use=resultSet.getString(2);
                    pas=resultSet.getString(3);

                     intent=new Intent(Main2Activity.this,MainActivity.class);
                    intent.putExtra("dataname",dab);
                    intent.putExtra("username",use);
                    intent.putExtra("password",pas);
                }

                conn.close();

                progressdialog.dismiss();
               if(intent!=null){

                   progressBar.setVisibility(View.GONE);
                   Toast.makeText(getApplicationContext(),"Successfully login",Toast.LENGTH_SHORT).show(); progressBar.setVisibility(View.GONE);
                   startActivity(intent); finish();
               }
               else {
                    codee.setText("");
                    usernamee.setText("");
                    passworde.setText(""
                    );
                   progressBar.setVisibility(View.GONE);
                   alertDialog.setMessage("You are not registered").setTitle("Login Failed").setCancelable(false)
                   .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.cancel();
                       }
                   });
                   AlertDialog alert=alertDialog.create();
                   alert.show();
                 //  Toast.makeText(getApplicationContext(),"You are not registered",Toast.LENGTH_LONG).show();
                 //  Toast.makeText(getApplicationContext(), "oufjkdf", Toast.LENGTH_LONG).show();

               }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

}

    public boolean validate() {
        boolean valid = true;

        String codec=codee.getText().toString();
        String usernammes=usernamee.getText().toString();
        String passwordc =passworde.getText().toString();

        if (codec.isEmpty() ) {
            codee.setError("enter code");
            Toast.makeText(getApplicationContext(),"Enter Code",Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            codee.setError(null);
        }

        if (passwordc.isEmpty() || passwordc.length() < 4 || passwordc.length() > 15) {
            passworde.setError("Enter password between 4 and 15 alphanumeric characters");
            Toast.makeText(getApplicationContext(),"Enter password between 4 and 15 alphanumeric characters",Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            passworde.setError(null);
        }
        if (usernammes.isEmpty() ) {
            passworde.setError("Enter username");
            Toast.makeText(getApplicationContext(),"Enter username",Toast.LENGTH_SHORT).show();
            valid = false;
        } else {
            passworde.setError(null);
        }

        return valid;
    }
}
