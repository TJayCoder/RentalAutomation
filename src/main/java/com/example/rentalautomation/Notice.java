package com.example.rentalautomation;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Notice extends AppCompatActivity {

private Button submitNotice;
 private DatabaseReference myref;
 private FirebaseDatabase firebaseDatabase;
 private EditText fullname, surname,id,cellNo,email,buildingName,unitNo,dateMoving,accountHolder,bank,accNo,branchCode,accType;
private String Fullname, Surname,Id,CellNo,Email,BuildingName,UnitNo,DateMoving,AccountHolder,Bank,AccNo,BranchCode,AccType;
 NoticeClass noticeClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);


        fullname=findViewById(R.id.txtFullnameNotice);
        surname=findViewById(R.id.SurnameNotice);
        id=findViewById(R.id.IDPassportNotice);
        cellNo=findViewById(R.id.CellNumberNotice);
        email=findViewById(R.id.EmailNotice);
        buildingName=findViewById(R.id.BuildingnameNotice);
        unitNo=findViewById(R.id.UnitnumberNotice);
        dateMoving=findViewById(R.id.DatemovingoutNotice);
        accountHolder=findViewById(R.id.AccountHoldersNameNotice);
        bank=findViewById(R.id.BankNotice);
        accNo=findViewById(R.id.AccountNumberNotice);
        branchCode=findViewById(R.id.BranchCodeNotice);
        accType=findViewById(R.id.TypeofAccountNotice);

        submitNotice=findViewById(R.id.btnSubmitNotice);



        submitNotice.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                    LoadData();

                }


        });


    }

private void LoadData(){
        Fullname=fullname.getText().toString();
        Surname=surname.getText().toString();
        Id=id.getText().toString();
        CellNo=cellNo.getText().toString();
        Email=email.getText().toString();
        BuildingName = buildingName.getText().toString();
        UnitNo=unitNo.getText().toString();
        DateMoving = dateMoving.getText().toString();
        AccountHolder=accountHolder.getText().toString();
        Bank=bank.getText().toString();
        AccNo=accNo.getText().toString();
        BranchCode=branchCode.getText().toString();
        AccType=accType.getText().toString();

        noticeClass=new NoticeClass(Fullname, Surname,Id,CellNo,Email,BuildingName,UnitNo,DateMoving,AccountHolder,Bank,AccNo,BranchCode,AccType);
    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
    noticeClass=new NoticeClass(Fullname, Surname,Id,CellNo,Email,BuildingName,UnitNo,DateMoving,AccountHolder,Bank,AccNo,BranchCode,AccType);
    DatabaseReference myref=firebaseDatabase.getReference("Notice/"+noticeClass.getId());

    myref.child("Full_Name").setValue(noticeClass.getFullname());
    myref.child("Surname").setValue(noticeClass.getSurname());
    myref.child("ID_Passport").setValue(noticeClass.getId());
    myref.child("Cell").setValue(noticeClass.getCellNo());
    myref.child("Email").setValue(noticeClass.getEmail());
    myref.child("Building_Name").setValue(noticeClass.getBuildingName());
    myref.child("Unit").setValue(noticeClass.getUnitNo());
    myref.child("Date_Moving").setValue(noticeClass.getDatemMving());
    myref.child("Account_Holder").setValue(noticeClass.getAccountHolder());
    myref.child("Bank").setValue(noticeClass.getBank());
    myref.child("Account_Number").setValue(noticeClass.getAccNo());
    myref.child("Branch_Code").setValue(noticeClass.getBranchCode());
    myref.child("Account_Type").setValue(noticeClass.getAccType());




    Toast.makeText(Notice.this,"Successfully Registered Notice!",Toast.LENGTH_SHORT).show();
}


    }


