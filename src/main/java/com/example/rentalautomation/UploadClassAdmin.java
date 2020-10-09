package com.example.rentalautomation;

public class UploadClassAdmin {

    public String Price;
    public String Description;
    public String Room;


    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public UploadClassAdmin(String price, String description, String room) {
        Price = price;
        Description = description;
        Room = room;
    }




    public UploadClassAdmin(){

    }





}
