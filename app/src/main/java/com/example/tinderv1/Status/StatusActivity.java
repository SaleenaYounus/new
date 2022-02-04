package com.example.tinderv1.Status;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tinderv1.Matches.MatchesObject;
import com.example.tinderv1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatusActivity extends AppCompatActivity {
    private Button mBack;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mStatusAdapter;
    private RecyclerView.LayoutManager mStatusLayoutManager;

    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mStatusLayoutManager = new LinearLayoutManager(StatusActivity.this);
        mRecyclerView.setLayoutManager(mStatusLayoutManager);
        mStatusAdapter = new StatusAdapter(getDataSetStatus(), StatusActivity.this);
        mRecyclerView.setAdapter(mStatusAdapter);
        //mBack = (Button) findViewById(R.id.back);
        /*mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                return;
            }
        });*/

        getAcceptedUserStatusId();
        getRejectedUserStatusId();

    }
    private String Accepted;
    private void getAcceptedUserStatusId() {
        //DatabaseReference acceptedDb = FirebaseDatabase.getInstance().getReference().child("Status").child(currentUserID).child("Accepted");
       DatabaseReference acceptedDb = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("connections").child("yeps");
        acceptedDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot accepted : dataSnapshot.getChildren()){
                        FetchAcceptedStatus(accepted.getKey());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private String Rejected;
    private void getRejectedUserStatusId() {
        //DatabaseReference rejectedDb = FirebaseDatabase.getInstance().getReference().child("Status").child(currentUserID).child("Rejected");
        DatabaseReference rejectedDb = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserID).child("connections").child("nope");

        rejectedDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for(DataSnapshot rejected : dataSnapshot.getChildren()){
                        FetchRejectedStatus(rejected.getKey());
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
   // private void FetchUserStatus(String key) {
   private void FetchRejectedStatus(String key) {

        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Users").child(key);
        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String userId = dataSnapshot.getKey();
                    String name = "";
                    String profileImageUrl = "";
                    String  Status="";

                    if(dataSnapshot.child("name").getValue()!=null){
                        name = dataSnapshot.child("name").getValue().toString();
                    }
                    if(dataSnapshot.child("profileImageUrl").getValue()!=null){
                        profileImageUrl = dataSnapshot.child("profileImageUrl").getValue().toString();
                    }

                    if(dataSnapshot.child("Status").getValue()==null){
                      Status= "Rejected";
                    }
                    StatusObject obj = new StatusObject(userId, name, profileImageUrl, Status);
                    resultsStatus.add(obj);
                    mStatusAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    private void FetchAcceptedStatus(String key) {

        DatabaseReference userDb = FirebaseDatabase.getInstance().getReference().child("Users").child(key);
        userDb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String userId = dataSnapshot.getKey();
                    String name = "";
                    String profileImageUrl = "";
                    String  Status="";

                    if(dataSnapshot.child("name").getValue()!=null){
                        name = dataSnapshot.child("name").getValue().toString();
                    }
                    if(dataSnapshot.child("profileImageUrl").getValue()!=null){
                        profileImageUrl = dataSnapshot.child("profileImageUrl").getValue().toString();
                    }

                    if(dataSnapshot.child("Status").getValue()==null){
                        Status= "Accepted";
                    }
                    StatusObject obj = new StatusObject(userId, name, profileImageUrl, Status);
                    resultsStatus.add(obj);
                    mStatusAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private ArrayList<StatusObject> resultsStatus = new ArrayList<StatusObject>();
    private List<StatusObject> getDataSetStatus() {
        return resultsStatus;
    }

}
