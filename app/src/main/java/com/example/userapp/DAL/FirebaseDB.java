package com.example.userapp.DAL;

import com.example.userapp.Entities.Member;
import com.example.userapp.Entities.Parcel;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDB {
    public interface Action<T> {
        void onSuccess(T obj);

        void onFailure(Exception exception);

        void onProgress(String status, double percent);
    }

    private final FirebaseListener<Parcel> parcelListener;
    private final FirebaseListener<Member> membersListener;

    FirebaseDB() {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://senderapp-85057.firebaseio.com/");
        parcelListener = new FirebaseListener<>(Parcel.class,database.getReference("Parcels"));
        membersListener = new FirebaseListener<>(Member.class,database.getReference("Members"));
    }

    public FirebaseListener<Parcel> getParcel() {
        return parcelListener;
    }

    public FirebaseListener<Member> getMembers() {
        return membersListener;
    }
}
