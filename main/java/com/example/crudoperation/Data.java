package com.example.crudoperation;

public class Data {
    private String id, name, email, phone;
    public Data() {

    }

    public Data(String id, String name, String contact, String address) {
        this.id = id;
        this.name = name;
        this.email = contact;
        this.phone = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
