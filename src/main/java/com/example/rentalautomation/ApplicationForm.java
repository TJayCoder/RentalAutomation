package com.example.rentalautomation;

public class ApplicationForm {
    public String Title, Surname, Initial, Name, Gender, Id, Country, Mobile, Email, Raddress, Raddresscode, Paddress, Paddresscode, Building, Unit, Month;

    public String getTitle() {
        return Title;
    }

    public String getSurname() {
        return Surname;
    }

    public String getInitial() {
        return Initial;
    }

    public String getName() {
        return Name;
    }

    public String getGender() {
        return Gender;
    }

    public String getId() {
        return Id;
    }

    public String getCountry() {
        return Country;
    }

    public String getMobile() {
        return Mobile;
    }

    public String getEmail() {
        return Email;
    }

    public String getRaddress() {
        return Raddress;
    }

    public String getRaddresscode() {
        return Raddresscode;
    }

    public String getPaddress() {
        return Paddress;
    }

    public String getPaddresscode() {
        return Paddresscode;
    }

    public String getBuilding() {
        return Building;
    }

    public String getUnit() {
        return Unit;
    }

    public String getMonth() {
        return Month;
    }

    public ApplicationForm(String title, String surname, String initial, String name, String gender, String id, String country, String mobile, String email, String raddress, String raddresscode, String paddress, String paddresscode, String building, String unit, String month) {
        Title = title;
        Surname = surname;
        Initial = initial;
        Name = name;
        Gender = gender;
        Id = id;
        Country = country;
        Mobile = mobile;
        Email = email;
        Raddress = raddress;
        Raddresscode = raddresscode;
        Paddress = paddress;
        Paddresscode = paddresscode;
        Building = building;
        Unit = unit;
        Month = month;
    }
}
