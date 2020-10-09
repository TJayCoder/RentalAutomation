package com.example.rentalautomation;

public class Product {

    private int id;
    private String tittle, shortdesc;
    private double price;
    private int image;

    public Product(int id, String tittle, String shortdesc, double price, int image) {
        this.id = id;
        this.tittle = tittle;
        this.shortdesc = shortdesc;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
