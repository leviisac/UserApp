package com.example.userapp.DAL;

import com.example.userapp.Entities.Member;
import com.example.userapp.Entities.Parcel;

import java.util.List;

import Interfaces.NotifyDataChange;

public class DB {
    private static DB instance = new DB();

    private FirebaseDB firebase = new FirebaseDB();

    //Observers
    private NotifyDB<Parcel> parcelNotify = new NotifyDB<>();
    private NotifyDB<Member> membersNotify = new NotifyDB<>();

    public static DB getInstance() {
        return instance;
    }



    private DB(){
        firebase.getParcel().notifyToList(parcelNotify.getListener());
        firebase.getMembers().notifyToList(membersNotify.getListener());

    }





    public NotifyDataChange<List<Parcel>> notifyParcelChange(NotifyDataChange<List<Parcel>> parcelListener){
        parcelNotify.addListener(parcelListener);
        //Auto initialize
        if(parcelNotify.getData().size() > 0)
            parcelListener.OnDataChanged(parcelNotify.getData());

        return parcelListener;
    }


    public void unNotifyParcelChange(NotifyDataChange<List<Parcel>> parcelListener){
        parcelNotify.removeListener(parcelListener);
    }



    public NotifyDataChange<List<Member>> notifyMembersChange(NotifyDataChange<List<Member>> membersListener){
        membersNotify.addListener(membersListener);
        //Auto initialize
        if(membersNotify.getData().size() > 0)
            membersListener.OnDataChanged(membersNotify.getData());

        return membersListener;
    }
    public void unNotifyMembersChange(NotifyDataChange<List<Member>> membersListener){
        membersNotify.removeListener(membersListener);
    }
}
