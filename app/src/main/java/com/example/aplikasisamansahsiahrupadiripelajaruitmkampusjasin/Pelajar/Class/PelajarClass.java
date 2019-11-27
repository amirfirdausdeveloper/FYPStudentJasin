package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pelajar.Class;

public class PelajarClass {
    private String email;
    private String id;
    private String jantina;
    private String katalaluan;
    private String kodprogram;
    private String name;
    private String nokp;
    private String notel;

    public PelajarClass(){
        //this constructor is required
    }

    public PelajarClass(String email, String id, String jantina, String katalaluan, String kodprogram,
                        String name, String nokp, String notel) {
        this.email = email;
        this.id = id;
        this.jantina = jantina;
        this.katalaluan = katalaluan;
        this.kodprogram = kodprogram;
        this.name = name;
        this.nokp = nokp;
        this.notel = notel;
    }

    public String getKatalaluan() {
        return katalaluan;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getJantina() {
        return jantina;
    }

    public String getKodprogram() {
        return kodprogram;
    }

    public String getNokp() {
        return nokp;
    }

    public String getNotel() {
        return notel;
    }
}
