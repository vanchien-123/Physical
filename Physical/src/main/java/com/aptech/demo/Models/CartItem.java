package com.aptech.demo.Models;

public class CartItem {

    private final Product product;
    private int Quantity;
    private double SubTotal;

    public CartItem(Product product) {
        this.product = product;
        this.Quantity = 1;
        this.SubTotal = product.getSellPrice();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getSubTotal() {
    	SubTotal = product.getSellPrice() * Quantity;
        return SubTotal;
    }

    public void setSubTotal(double SubTotal) {
        this.SubTotal = SubTotal;
    }
}
