package com.sarb.homepractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigator;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.jar.Attributes;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {
    List<CompanyModel> company;
    Context context;

    public CompanyAdapter(List<CompanyModel> company, Context context) {
        this.company = company;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_company,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_name.setText(company.get(position).getCompany());


    }

    @Override
    public int getItemCount() {
        return company.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name;
        public ViewHolder(View view) {
            super(view);
            txt_name= view.findViewById(R.id.txt_companyname);
        }
    }
}
