package com.example.storesummary;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;

import model.balance;

public class moredetailsrecycler extends RecyclerView.Adapter<moredetailsrecycler.holderdetail> {

private List<balance> balancelist;

    public moredetailsrecycler(List<balance> balancelist) {
        this.balancelist = balancelist;
    }

    @NonNull
    @Override
    public holderdetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.moredetails,parent,false);

        return new moredetailsrecycler.holderdetail(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holderdetail holder, int position) {
        String date=balancelist.get(position).getDate();
        Double drbalaane=balancelist.get(position).getDrbalance();
        Double crbalance=balancelist.get(position).getCrbalance();
        String remmarks=balancelist.get(position).getRemarks();
        String name=balancelist.get(position).getName();

holder.cname.setText(name);
holder.date.setText("Billing Date: "+date);
holder.remarks.setText("Remarks: "+remmarks);
if(drbalaane!=0){

    holder.crdr.setTextColor(Color.RED);
    DecimalFormat format=new DecimalFormat("#.##");
    holder.crdr.setText("Nrs "+format.format(drbalaane));




}


else if(crbalance!=0){
    holder.crdr.setTextColor(Color.parseColor("#FF086D0B"));
    DecimalFormat format=new DecimalFormat("##,##,##,##,###.##");
    holder.crdr.setText("Nrs "+format.format(crbalance));

}
else{
    holder.crdr.setTextColor(Color.GRAY);
    DecimalFormat format=new DecimalFormat("##,##,##,##,###.##");
    holder.crdr.setText("Nrs "+format.format(crbalance));
}
    }

    @Override
    public int getItemCount() {
        return balancelist.size();
    }



    class holderdetail extends RecyclerView.ViewHolder{
private TextView date,cname,crdr,remarks;
        public holderdetail(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            cname=itemView.findViewById(R.id.detailname);
            crdr=itemView.findViewById(R.id.balancedetail);
            remarks=itemView.findViewById(R.id.narration);




        }
    }
}
