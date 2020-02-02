package com.example.userapp.DAL;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Interfaces.MyEntity;
import Interfaces.NotifyDataChange;

public class FirebaseListener<T extends MyEntity> {
    private final DatabaseReference ref;
    private final Class<T> type;
    private final List<T> list;
    private ChildEventListener RefChildEventListener;

    public FirebaseListener(Class<T> type,DatabaseReference ref) {
        this.ref = ref;
        this.type = type;
        list = new ArrayList<>();
    }

    private void addToFirebase(final T object, final FirebaseDB.Action<Long> action) {
        String key = String.valueOf(object.getId());
        ref.child(key).setValue(object).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // action.onSuccess((Long).getId());
                action.onProgress("upload  data", 100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
                action.onProgress("error upload  data", 100);

            }
        });
    }

    public long add(final T obj) {
//
        obj.setId(System.currentTimeMillis());
        DatabaseReference idRef = ref.child(String.valueOf(obj.getId()));
        idRef.setValue(obj);
        return obj.getId();
    }


    public void remove(long id) {
        DatabaseReference idRef = ref.child(String.valueOf(id));
        idRef.removeValue();
    }


    /*public void update(final T toUpdate, final FirebaseDB.Action<Long> action) {
        final String key = String.valueOf(toUpdate.getId());

        remove(toUpdate.getId(), new FirebaseDB.Action<Long>() {
            @Override
            public void onSuccess(Long obj) {
                add(toUpdate, action);
            }

            @Override
            public void onFailure(Exception exception) {
                action.onFailure(exception);
            }

            @Override
            public void onProgress(String status, double percent) {
                action.onProgress(status, percent);
            }
        });
    }*/

    public void notifyToList(final NotifyDataChange<List<T>> notifyDataChange) {
        if (notifyDataChange == null)
            return;

        if (RefChildEventListener != null) {
            notifyDataChange.onFailure(new Exception("first unNotify  list"));
            return;
        }
        list.clear();

        RefChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Parcel obj = dataSnapshot.getValue(Parcel.class);
                T obj = dataSnapshot.getValue(type);
                String id = dataSnapshot.getKey();
                obj.setId(Long.parseLong(id));
                list.add(0, obj);
                //  list.add();


                notifyDataChange.OnDataChanged(list);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                T obj = dataSnapshot.getValue(type);
                String id = (dataSnapshot.getKey());
                obj.setId(Long.parseLong(id));


                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId() == obj.getId()) {
                        list.set(i, obj);
                        break;
                    }
                }
                notifyDataChange.OnDataChanged(list);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                T obj = dataSnapshot.getValue(type);
                String id = (dataSnapshot.getKey());
                obj.setId(Long.parseLong(id));

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId() == obj.getId()) {
                        list.remove(i);
                        break;
                    }
                }
                notifyDataChange.OnDataChanged(list);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                notifyDataChange.onFailure(databaseError.toException());
            }
        };
        ref.addChildEventListener(RefChildEventListener);

    }
    public void stopNotifyToList() {
        if (RefChildEventListener != null) {
            ref.removeEventListener(RefChildEventListener);
            RefChildEventListener = null;
        }
    }
}
