package com.example.userapp.DAL;

import androidx.annotation.NonNull;

import com.example.userapp.Entities.Members;
import com.example.userapp.Entities.Parcel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import Interfaces.NotifyDataChange;

public class FirebaseDB {
    public interface Action<T> {
        void onSuccess(T obj);

        void onFailure(Exception exception);

        void onProgress(String status, double percent);
    }

    private final FirebaseListener<Parcel> parcelListener;
    private final FirebaseListener<Members> membersListener;

    FirebaseDB() {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://senderapp-85057.firebaseio.com/");
        parcelListener = new FirebaseListener<>(Parcel.class,database.getReference("Parcels"));
        membersListener = new FirebaseListener<>(Members.class,database.getReference("Members"));
    }

    public FirebaseListener<Parcel> getParcel() {
        return parcelListener;
    }

    public FirebaseListener<Members> getMembers() {
        return membersListener;
    }
}
