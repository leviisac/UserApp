package com.example.userapp.DAL;

import com.example.userapp.Entities.Member;
import com.example.userapp.Entities.Parcel;
import com.example.userapp.Entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

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


    public void login(String username, String password, OnCompleteListener listener){
        firebase.login(username,password,listener);
    }

    public Task<AuthResult> addLogin(String fulllName,String email, String password,NotifyDataChange<User> onComplete) {
        if(fulllName == null){
            if(onComplete !=null)
                onComplete.onFailure(new Exception("Invalid display name"));
        }
        return firebase.addLogin(fulllName,email,password,onComplete);
    }
    public void addMembers(Member member,NotifyDataChange<Member> onComplete){
        try {
            if(member == null)
                throw new Exception("Invalid member");

            if(member.getName() == null || member.getName().isEmpty())
                throw new Exception("Invalid name");

            member.setId(firebase.getMembers().add(member));
            if(onComplete !=null)
                onComplete.OnDataChanged(member);
        } catch (Exception e) {
            if(onComplete !=null)
                onComplete.onFailure(new Exception(e.getMessage()));
        }

    }
}
