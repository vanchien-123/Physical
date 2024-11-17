package com.aptech.demo.Models;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<CartItem> Items;
    private double Total;

    public Cart() {
    	Items = new ArrayList<CartItem>();
        Total = 0;
    }

    public CartItem getItem(Product p){
        for(CartItem item : Items){
            if (item != null && item.getProduct() != null && p != null && p.getID() != null && item.getProduct().getID().equals(p.getID())){
                return item;
            }
        }
        return null;
    }

    public List<CartItem> getItems(){
        return Items;
    }

 
    public int getItemCount(){
        return Items.size();
    }
   
    public void addItem(CartItem item){
        addItem(item.getProduct(), item.getQuantity());
    }

    public void addItem(Product p, int quantity){
        CartItem item = getItem(p);
        if (item != null){
            item.setQuantity(item.getQuantity() + quantity); 
        } else { 
            item = new CartItem(p);
            item.setQuantity(quantity); 
            Items.add(item); 
        }
    }

    public void updateItem(Product p, int quantity){
        CartItem item = getItem(p); 
        if (item != null) {
            item.setQuantity(quantity);
        }
    }

    public void removeItem(Product p){
        CartItem item = getItem(p); 
        if (item != null) {
        	Items.remove(item);
        }
    }
 
    public void clear(){
    	Items.clear();
        Total = 0;
    }

    public boolean isEmpty(){
        return Items.isEmpty();
    }

    public double getTotal(){
    	Total = 0;
        for (CartItem item : Items){
        	Total += item.getSubTotal();
        }
        return Total;
    }
}
