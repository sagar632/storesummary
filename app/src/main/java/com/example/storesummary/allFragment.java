package com.example.storesummary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.balance;

public class allFragment extends Fragment {
private Connection conn=null;

    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.all,null);
        final List<model.balance> balncelist  =new ArrayList<>();
        Connectionclass connectionclass=new Connectionclass();
        conn=connectionclass.connect();
        if(conn!=null){
            Statement statement=null;

            try {
                statement=conn.createStatement();
                ResultSet resultSet = statement.executeQuery("select gname1 as GroupName,sum(abs(bal)) as Balance,DRORCR from LedgerBalance group by gname1,DRORCR order by GNAME1;");
                while (resultSet.next()){
                    balncelist.add(new model.balance(resultSet.getString(1),resultSet.getString(3),Double.parseDouble(resultSet.getString(2))));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

Log.d("fjdkfdjfkdf",balncelist.toString());

       recyclerView=v.findViewById(R.id.allrecycler);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new recycleradapter(balncelist, new recycleradapter.OnItemClickListener() {
            @Override
            public void OnItemClick(final balance balance) {

                Toast.makeText(getContext(),balance.getName().toString(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),details.class);

                intent.putExtra("name",balance.getName());
                intent.putExtra("balancce",balance.getBalance().toString());
                intent.putExtra("doc",balance.getDorc());

                startActivity(intent);

            }
        }));

        return v;

    }
}
