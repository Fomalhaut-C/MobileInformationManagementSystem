package com.example.mims.mobileinformationmanagementsystem.Mobile;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mims.mobileinformationmanagementsystem.R;

import java.util.List;

public class MobileAdapter extends RecyclerView.Adapter<MobileAdapter.ViewHolder> {
    private Context context;
    private List<Mobile> MobileList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView mobileImage;
        TextView mobileName;
        private ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            mobileImage = view.findViewById(R.id.mobile_image);
            mobileName = view.findViewById(R.id.mobile_name);
        }
    }

    public MobileAdapter(List<Mobile> mobileList){
        MobileList = mobileList;
    }

    @NonNull
    @Override
    public MobileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int i) {
        if (context == null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.mobile_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Mobile mobile = MobileList.get(position);
                Intent intent = new Intent(context,MobileInforActivity.class);
                intent.putExtra("mobile_name",mobile.getName());
                intent.putExtra("mobile_image",mobile.getImage());
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MobileAdapter.ViewHolder holder, int position) {
        Mobile mobile = MobileList.get(position);
        Uri uri = Uri.parse(mobile.getImage());
        holder.mobileName.setText(mobile.getName());
        Glide.with(context).load(uri).into(holder.mobileImage);
    }

    @Override
    public int getItemCount() {
        return MobileList.size();
    }
}
