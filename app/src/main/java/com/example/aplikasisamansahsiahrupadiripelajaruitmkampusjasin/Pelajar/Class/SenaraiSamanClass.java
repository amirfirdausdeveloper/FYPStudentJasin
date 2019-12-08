package com.example.aplikasisamansahsiahrupadiripelajaruitmkampusjasin.Pelajar.Class;

public class SenaraiSamanClass {
    private String dateSaman;
    private String imageSaman;
    private String KesalahanBaju;
    private String KesalahanSeluar;
    private String KesalahanKasut;
    private String KesalahanRambut;
    private String pensyarahID;
    private String pensyarahName;
    private String statusBayaran;
    private String statusDiscount;
    private String studentID;
    private String studentTel;
    private String studentName;
    private String studentKP;
    private String studentKodProgram;


    public SenaraiSamanClass(String dateSaman, String imageSaman, String KesalahanBaju, String KesalahanSeluar, String KesalahanKasut
            , String KesalahanRambut, String pensyarahID, String pensyarahName, String statusBayaran, String statusDiscount, String studentID
            , String studentTel, String studentName, String studentKP, String studentKodProgram) {
        this.dateSaman = dateSaman;
        this.imageSaman = imageSaman;
        this.KesalahanBaju = KesalahanBaju;
        this.KesalahanSeluar = KesalahanSeluar;
        this.KesalahanKasut = KesalahanKasut;
        this.KesalahanRambut = KesalahanRambut;
        this.pensyarahID = pensyarahID;
        this.pensyarahName = pensyarahName;
        this.statusBayaran = statusBayaran;
        this.statusDiscount = statusDiscount;
        this.studentID = studentID;
        this.studentTel = studentTel;
        this.studentName = studentName;
        this.studentKP = studentKP;
        this.studentKodProgram = studentKodProgram;
    }

    public String getPensyarahName() {
        return pensyarahName;
    }

    public String getStudentKodProgram() {
        return studentKodProgram;
    }

    public String getStudentKP() {
        return studentKP;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentTel() {
        return studentTel;
    }

    public String getDateSaman() {
        return dateSaman;
    }

    public String getImageSaman() {
        return imageSaman;
    }

    public String getKesalahanBaju() {
        return KesalahanBaju;
    }

    public String getKesalahanKasut() {
        return KesalahanKasut;
    }

    public String getKesalahanRambut() {
        return KesalahanRambut;
    }

    public String getKesalahanSeluar() {
        return KesalahanSeluar;
    }

    public String getPensyarahID() {
        return pensyarahID;
    }

    public String getStatusBayaran() {
        return statusBayaran;
    }

    public String getStatusDiscount() {
        return statusDiscount;
    }

    public String getStudentID() {
        return studentID;
    }
}
