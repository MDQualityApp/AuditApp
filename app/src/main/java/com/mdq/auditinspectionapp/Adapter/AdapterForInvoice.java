package com.mdq.auditinspectionapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mdq.auditinspectionapp.Interfaces.Interface_FinalInvoice;
import com.mdq.auditinspectionapp.R;

public class AdapterForInvoice extends RecyclerView.Adapter<AdapterForInvoice.mine> {
    Context context;
    String[] invoiceNo;
    int i=0,selected=0;
    Interface_FinalInvoice interface_finalInvoice;
    public AdapterForInvoice(Context context, String[] invoiceNo, Interface_FinalInvoice interface_finalInvoice){
        this.context=context;
        this.invoiceNo=invoiceNo;
        this.interface_finalInvoice=interface_finalInvoice;
    }

    @NonNull
    @Override
    public mine onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_invoice_num,parent,false);
        return new mine(view);
    }

    @Override
    public void onBindViewHolder(@NonNull mine holder, @SuppressLint("RecyclerView") int position) {

        holder.invoiceNum.setText(invoiceNo[position]);
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected == 0) {
                    selected=1;
                    if (i == 0) {
                        holder.tik.setVisibility(View.VISIBLE);
                        holder.cardForTik.setBackground(null);
                        interface_finalInvoice.FinalInvoiceCall(position);
                        i = 1;
                    } else {
                        holder.tik.setVisibility(View.INVISIBLE);
                        i = 0;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return invoiceNo.length;
    }

    public class mine extends RecyclerView.ViewHolder {
        TextView invoiceNum;
        LinearLayout ll;
        ImageView tik;
        CardView cardForTik;
        public mine(@NonNull View itemView) {
            super(itemView);
            invoiceNum=itemView.findViewById(R.id.invoiceNum);
            ll=itemView.findViewById(R.id.ll);
            tik=itemView.findViewById(R.id.tik);
            cardForTik=itemView.findViewById(R.id.cardfortik);

        }
    }
}
