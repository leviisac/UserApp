package com.example.userapp.DAL;

import androidx.annotation.NonNull;

import com.example.userapp.Entities.Member;
import com.example.userapp.Entities.Parcel;
import com.example.userapp.Entities.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;

import Interfaces.NotifyDataChange;

public class FirebaseDB {
    public interface Action<T> {
        void onSuccess(T obj);

        void onFailure(Exception exception);

        void onProgress(String status, double percent);
    }

    private FirebaseAuth mAuth;
    private final FirebaseListener<Parcel> parcelListener;
    private final FirebaseListener<Member> membersListener;

    FirebaseDB() {

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://senderapp-85057.firebaseio.com/");
        parcelListener = new FirebaseListener<>(Parcel.class, database.getReference("Parcels"));
        membersListener = new FirebaseListener<>(Member.class, database.getReference("Members"));
    }

    public FirebaseListener<Parcel> getParcel() {
        return parcelListener;
    }

    public FirebaseListener<Member> getMembers() {
        return membersListener;
    }

    public void login(String username, String password, OnCompleteListener listener) {
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(listener);
    }

    public Task<AuthResult> addLogin(String displayName, String username, String password, NotifyDataChange<User> notifyDataChange) {
        return mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = mAuth.getCurrentUser();
                    if(user == null)
                    {
                        if(notifyDataChange != null)
                            notifyDataChange.onFailure(new Exception("User not found"));
                        return;
                    }
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(displayName).build();
                    user.updateProfile(profileUpdates);
                    user.sendEmailVerification();
                    if(notifyDataChange != null)
                            notifyDataChange.OnDataChanged(new User(user.getEmail(), displayName));
                } else {
                    // If sign in fails, display a message to the user.
                    if (notifyDataChange != null)
                        notifyDataChange.onFailure(new Exception("Authentication failed."));
                }
            }
        });
    }
}
