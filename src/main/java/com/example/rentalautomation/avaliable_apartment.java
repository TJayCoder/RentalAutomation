package com.example.rentalautomation;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class avaliable_apartment extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;

    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliable_apartment);

        productList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList.add(
                new Product(
                        1,
                        "Lebo's Place",
                        "2 bedroom and 1 bathroom",
                        4500,
                        R.drawable.flat));


        productList.add(
                new Product(
                        1,
                        "Lira's place",
                        "3 bedrooms and 2 bathrooms",
                        5500,
                        R.drawable.city));


        productList.add(
                new Product(
                        1,
                        "Tasha's Place",
                        "bachelor with a balcony",
                        2500,
                        R.drawable.building));

        productList.add(
                new Product(
                        1,
                        "Malindi's Place",
                        "bachelor with a balcony and a nice view",
                        3500,
                        R.drawable.j1));

        productList.add(
                new Product(
                        1,
                        "Michelle's Place",
                        "two bedroom, one bathroom and a balcony",
                        4500,
                        R.drawable.j2));

        productList.add(
                new Product(
                        1,
                        "Hlale's Place",
                        "bachelor with a balcony",
                        3500,
                        R.drawable.j3));
        productList.add(
                new Product(
                        1,
                        "Joseph's Place",
                        "two bedroom , one bathroom and a kitchen",
                        5500,
                        R.drawable.j4));
        productList.add(
                new Product(
                        1,
                        "Gomo's Place",
                        "two bedroom each with a bathroom",
                        2500,
                        R.drawable.j6));
        productList.add(
                new Product(
                        1,
                        "Adolf's Place",
                        "3 bedroom and a 2 bathrooms",
                        8500,
                        R.drawable.j7));
        productList.add(
                new Product(
                        1,
                        "Llewelyn's Place",
                        "bachelor and a balcony",
                        5500,
                        R.drawable.j8));




        adapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(adapter);



    }
}
