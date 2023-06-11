package com.example.fooddelivery;

public class Product {
    String image,productname,productprice;

    public Product() {
    }

    public Product(String image, String productname, String productprice) {
        this.image = image;
        this.productname = productname;
        this.productprice = productprice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }
}
