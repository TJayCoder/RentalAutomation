package com.example.rentalautomation;

public class NoticeClass {
    private String Fullname, Surname,Id,CellNo,Email,BuildingName,UnitNo,DatemMving,AccountHolder,Bank,AccNo,BranchCode,AccType;

    public NoticeClass(String fullname, String surname, String id, String cellNo, String email, String buildingName, String unitNo, String datemMving, String accountHolder, String bank, String accNo, String branchCode, String accType) {
        Fullname = fullname;
        Surname = surname;
        Id = id;
        CellNo = cellNo;
        Email = email;
        BuildingName = buildingName;
        UnitNo = unitNo;
        DatemMving = datemMving;
        AccountHolder = accountHolder;
        Bank = bank;
        AccNo = accNo;
        BranchCode = branchCode;
        AccType = accType;
    }

    public String getFullname() {
        return Fullname;
    }

    public String getSurname() {
        return Surname;
    }

    public String getId() {
        return Id;
    }

    public String getCellNo() {
        return CellNo;
    }

    public String getEmail() {
        return Email;
    }

    public String getBuildingName() {
        return BuildingName;
    }

    public String getUnitNo() {
        return UnitNo;
    }

    public String getDatemMving() {
        return DatemMving;
    }

    public String getAccountHolder() {
        return AccountHolder;
    }

    public String getBank() {
        return Bank;
    }

    public String getAccNo() {
        return AccNo;
    }

    public String getBranchCode() {
        return BranchCode;
    }

    public String getAccType() {
        return AccType;
    }
}
