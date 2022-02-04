package com.example.tinderv1.Status;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tinderv1.R;

import java.util.List;

public class StatusAdapter extends RecyclerView.Adapter<StatusViewHolders>{
    private List<StatusObject> acceptedList;
    private Context context;

    public StatusAdapter(List<StatusObject> acceptedList, Context context){
        this.acceptedList = acceptedList;
        this.context = context;
    }

    @Override
    public StatusViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        StatusViewHolders rcv = new StatusViewHolders(layoutView);

        return rcv;
    }

    @Override
    public void onBindViewHolder(StatusViewHolders holder, int position) {
        holder.mAcceptedUserId.setText(acceptedList.get(position).getUserId());
        holder.mAcceptedUserName.setText(acceptedList.get(position).getName());
       holder.mAcceptedUserStatus.setText(acceptedList.get(position).getStatus());

        if(!acceptedList.get(position).getProfileImageUrl().equals("default")){
            Glide.with(context).load(acceptedList.get(position).getProfileImageUrl()).into(holder.mAcceptedUserImage);
        }
    }

    @Override
    public int getItemCount() {
        return this.acceptedList.size();
    }
}
