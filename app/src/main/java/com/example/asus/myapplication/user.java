package com.example.asus.myapplication;

public class user {
    private String nama;
    private String id;
    private String username;
    private String pass1;
    private String pass2;
    private String status;
    private String jk;

    public user(){

    }

    public user(String nama,String id,String user,String pass1,String pass2,String status,String jk){
        this.nama=nama;
        this.id=id;
        this.username=user;
        this.pass1=pass1;
        this.pass2=pass2;
        this.status=status;
        this.jk=jk;

    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public String getUser() {
        return username;
    }

    public void setPass1(String pass1) {
        this.pass1 = pass1;
    }

    public String getPass1() {
        return pass1;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getPass2() {
        return pass2;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getJk() {
        return jk;
    }
}
