package com.example.chatlingg;

public class Users {

    public String dogum,egitim,isim,meslek,resim;

    public Users(){

    }

    public Users(String dogum, String egitim, String isim, String meslek, String resim) {
        this.dogum = dogum;
        this.egitim = egitim;
        this.isim = isim;
        this.meslek = meslek;
        this.resim = resim;
    }



    public String getdogum() {
        return dogum;
    }

    public void setdogum(String dogum) {
        this.dogum = dogum;
    }

    public String getEgitim() {
        return egitim;
    }

    public void setEgitim(String egitim) {
        this.egitim = egitim;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getMeslek() {
        return meslek;
    }

    public void setMeslek(String meslek) {
        this.meslek = meslek;
    }

    public String getResim() {
        return resim;
    }

    public void setResim(String resim) {
        this.resim = resim;
    }
    @Override
    public String toString() {
        return "Users{" +
                "dogum='" + dogum + '\'' +
                ", egitim='" + egitim + '\'' +
                ", isim='" + isim + '\'' +
                ", meslek='" + meslek + '\'' +
                ", resim='" + resim + '\'' +
                '}';
    }
}
