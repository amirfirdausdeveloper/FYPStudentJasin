package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pensyarah.Class;

public class PensyarahClass {
    private String email;
    private String jawatan;
    private String katalaluan;
    private String name;
    private String phone;

    public PensyarahClass(){
        //this constructor is required
    }

    public PensyarahClass(String email, String jawatan, String katalaluan, String name, String phone) {
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
