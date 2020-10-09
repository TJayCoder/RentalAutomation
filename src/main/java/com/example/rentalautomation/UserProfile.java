package com.example.rentalautomation;

public class UserProfile {
    public  String Name;
    public String Surname;

    public String Email;
    public String CellNumber;

    public UserProfile(){


    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCellNumber() {
        return CellNumber;
    }

    public void setCellNumber(String cellNumber) {
        CellNumber = cellNumber;
    }


    public UserProfile(String name, String surname, String email, String cellNumber) {
        Name = name;
        Surname = surname;
        Email = email;
        CellNumber = cellNumber;
    }




}
