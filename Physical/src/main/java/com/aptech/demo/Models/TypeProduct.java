package com.aptech.demo.Models;


public class TypeProduct {

    private int Stt;

    private Long ID;

    private String Name;

    public TypeProduct() {

    }

    public TypeProduct(int Stt, Long ID, String Name){
        this.Stt = Stt;
        this.ID = ID;
        this.Name = Name;
    }

    public int getStt() {
        return Stt;
    }

    public void setStt(int Stt) {
        this.Stt = Stt;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
