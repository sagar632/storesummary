package com.example.storesummary;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import model.balance;

public class recycleradapter extends RecyclerView.Adapter<recycleradapter.holder> {
    private List<model.balance> balanaces;
    private OnItemClickListener listener;

    public recycleradapter(List<balance> balanaces, OnItemClickListener listener) {
        this.balanaces = balanaces;
        this.listener = listener;
    }
    public interface OnItemClickListener {
        void OnItemClick(model.balance balance);
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);

        return new holder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        String name=balanaces.get(position).getName();
        Double balance=balanaces.get(position).getBalance();
        String doc=balanaces.get(position).getDorc();


        holder.bind(balanaces.get(position),listener);
        holder.name.setText(name);
        if(doc.equals("DR")){
            holder.balance.setTextColor(Color.BLACK);
            DecimalFormat form =new DecimalFormat("##,##,##,##,###.##");

            holder.balance.setText("Dr. "+form.format(balance));

        }
        else{
            holder.balance.setTextColor(Color.BLACK); DecimalFormat form =new DecimalFormat("##,##,##,##,###.##");
            holder.balance.setText( "Cr. "+form.format(balance));
        }
    }

    @Override
    public int getItemCount() {
        return balanaces.size();
    }

    static class holder extends RecyclerView.ViewHolder{
private TextView name,balance;
        public holder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            name=itemView.findViewById(R.id.date);
            balance=itemView.findViewById(R.id.balancedetail);

        }

        public void bind(final model.balance balance, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick(balance);

                }
            });
        }



    }
}
