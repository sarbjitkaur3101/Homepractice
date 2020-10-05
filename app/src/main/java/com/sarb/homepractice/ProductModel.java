package com.sarb.homepractice;

public class ProductModel  {
    String name,price,memory,image;


    public ProductModel(String name, String price, String memory, String image) {
        this.name = name;
        this.price = price;
        this.memory = memory;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
