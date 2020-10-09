package com.example.rentalautomation;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class adminNoticeView extends AppCompatActivity {
    private Button btnDelete, btnFind;
    private TextView fullname, surname,id,cellNo,email,buildingName,unitNo,dateMoving,accountHolder,bank,accNo,branchCode,accType;
    private EditText getid;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_notice_view);

        btnDelete=findViewById(R.id.btnDeleteN);
        btnFind=findViewById(R.id.btnFindN);

        fullname=findViewById(R.id.txtNFullName);
        surname=findViewById(R.id.txtNSurname);
        id=findViewById(R.id.txtNId);
        cellNo=findViewById(R.id.txtNCell);
        email=findViewById(R.id.txtNEmail);
        buildingName=findViewById(R.id.txtNBuilding);
        unitNo=findViewById(R.id.txtNUnit);
        dateMoving=findViewById(R.id.txtNDate);
        accountHolder=findViewById(R.id.txtNAcccHolder);
        bank=findViewById(R.id.txtNBank);
        accNo=findViewById(R.id.txtNAccNo);
        branchCode=findViewById(R.id.txtNBranch);
        accType=findViewById(R.id.txtNAccType);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FindRecord();

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DeleteClicked();
            }
        });

    }
    private void FindRecord(){
        getid=findViewById(R.id.txtId1stN);
        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Notice/"+getid.getText());


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String sFullName=dataSnapshot.child("Full_Name").getValue().toString();
                String sSurname=dataSnapshot.child("Surname").getValue().toString();
                String sId=dataSnapshot.child("ID_Passport").getValue().toString();
                String sCell=dataSnapshot.child("Cell").getValue().toString();
                String sEmail=dataSnapshot.child("Email").getValue().toString();
                String sBuilding=dataSnapshot.child("Building_Name").getValue().toString();
                String sUnit=dataSnapshot.child("Unit").getValue().toString();
                String sDate=dataSnapshot.child("Date_Moving").getValue().toString();
                String sAccHolder=dataSnapshot.child("Account_Holder").getValue().toString();
                String sBank=dataSnapshot.child("Bank").getValue().toString();
                String sAccNo=dataSnapshot.child("Account_Number").getValue().toString();
                String sBranch=dataSnapshot.child("Branch_Code").getValue().toString();
                String sAccType=dataSnapshot.child("Account_Type").getValue().toString();

                fullname.setText(sFullName);
                surname.setText(sSurname);
                id.setText(sId);
                cellNo.setText(sCell);
                email.setText(sEmail);
                buildingName.setText(sBuilding);
                unitNo.setText(sUnit);
                dateMoving.setText(sDate);
                accountHolder.setText(sAccHolder);
                bank.setText(sBank);
                accNo.setText(sAccNo);
                branchCode.setText(sBranch);
                accType.setText(sAccType);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(adminNoticeView.this,"Error occured",Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void DeleteClicked(){
        AlertDialog.Builder a_builder = new AlertDialog.Builder(adminNoticeView.this);
        a_builder.setMessage("ARE YOU SURE YOU WANT TO DELETE THE Notice?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.removeValue();
                        Toast.makeText(adminNoticeView.this,"Record DELETED",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(new Intent(adminNoticeView.this, admin_main_automation.class));
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
        AlertDialog alert = a_builder.create();
        alert.setTitle("Alert !!!");
        alert.show();

    }
}
