package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Admin.Class;

public class AdminClass {
    private String email;
    private String jawatan;
    private String katalaluan;
    private String name;
    private String phone;

    public AdminClass(){
        //this constructor is required
    }

    public AdminClass(String email, String jawatan, String katalaluan, String name, String phone) {
        this.email = email;
        this.jawatan = jawatan;
        this.katalaluan = katalaluan;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getJawatan() {
        return jawatan;
    }

    public String getKatalaluan() {
        return katalaluan;
    }

    public String getPhone() {
        return phone;
    }
}