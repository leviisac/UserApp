package com.example.userapp.Entities;

import Interfaces.Entitie;

public class Members implements Entitie {


    private long id;
    private String Name;
    private String Last_name;
    private String Phone;
    private String Adress;

    public Members(String name, String last_name, String phone, String adress) {
        Name = name;
        Last_name = last_name;
        Phone = phone;
        Adress = adress;
    }

    public Members() {
    }

    public String getcompleteName() {
        return (Name+" "+Last_name);
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    @Override
    public String toString() {
        return "Members{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Last_name='" + Last_name + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Adress='" + Adress + '\'' +
                '}';
    }
}
