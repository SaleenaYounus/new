package com.example.tinderv1.Status;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tinderv1.Chat.ChatActivity;
import com.example.tinderv1.R;

import java.text.BreakIterator;


public class StatusViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView mAcceptedUserId, mAcceptedUserName, mAcceptedUserStatus;
    public ImageView mAcceptedUserImage;


    public StatusViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);

        mAcceptedUserId = (TextView) itemView.findViewById(R.id.AccepteduserId);
        mAcceptedUserName= (TextView) itemView.findViewById(R.id.AccepteduserName);
        mAcceptedUserImage= (ImageView) itemView.findViewById(R.id.AccepteduserImage);

       mAcceptedUserStatus= (TextView) itemView.findViewById(R.id.AcceptedUserStatus);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), ChatActivity.class);
        Bundle b = new Bundle();
        b.putString("accepted userid", mAcceptedUserId.getText().toString());
        intent.putExtras(b);
        view.getContext().startActivity(intent);
    }
}
