package com.example.mobilehealthclinic.model;


public class Patient {
    private String name;
    private String address;
    private String cell;
    private String email;

    private String idNumber;

    public Patient() {
        //needed for firebase
    }

    public Patient(String name, String address, String cell, String email) {
        this.name = name;
        this.address = address;
        this.cell = cell;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getcell() {
        return cell;
    }

    public void setTel(String tel) {
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}