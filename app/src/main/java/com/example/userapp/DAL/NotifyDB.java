package com.example.userapp.DAL;

import com.example.userapp.Entities.Parcel;

import java.util.ArrayList;
import java.util.List;

import Interfaces.NotifyDataChange;

public class NotifyDB<T> {

    //Data
    private final List<T> data = new ArrayList<>();
    //Observer
    private final List<NotifyDataChange<List<T>>> listeners = new ArrayList<>();
    //Firebase listener
    private final NotifyDataChange<List<T>> listener = new NotifyDataChange<List<T>>() {
        @Override
        public void OnDataChanged(List<T> obj) {
            setData(obj);
        }

        @Override
        public void onFailure(Exception exception) {
            setError(exception);
        }
    };

    private void broadCastData() {
        for (NotifyDataChange<List<T>> listener : listeners)
            listener.OnDataChanged(this.data);
    }

    private void broadCastError(Exception exception) {
        for (NotifyDataChange<List<T>> listener : listeners)
            listener.onFailure(exception);
    }

    public void addListener(NotifyDataChange<List<T>> listener) {
        listeners.add(listener);
    }

    public void removeListener(NotifyDataChange<List<T>> listener) {
        listeners.remove(listener);
    }

    void addData(List<T> data) {
        this.data.addAll(data);
        broadCastData();
    }

    void setData(List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        broadCastData();
    }

    void setError(Exception exception) {
        broadCastError(exception);
    }

    List<T> getData() {
        return data;
    }

    public NotifyDataChange<List<T>> getListener() {
        return listener;
    }
}
