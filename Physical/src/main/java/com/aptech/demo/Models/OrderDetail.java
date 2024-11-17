package com.aptech.demo.Models;


public class OrderDetail {

    private Long ID;
    private Long OrderID;
    private Long ProID;
    private float ImportPrice;
    private float SellPrice;
    private int Quantity;
    private float Total;
    
    public OrderDetail() {
    }

	public OrderDetail(Long iD, Long orderID, Long proID, float importPrice, float sellPrice, int quantity,
			float total) {
		super();
		ID = iD;
		OrderID = orderID;
		ProID = proID;
		ImportPrice = importPrice;
		SellPrice = sellPrice;
		Quantity = quantity;
		Total = total;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public Long getOrderID() {
		return OrderID;
	}

	public void setOrderID(Long orderID) {
		OrderID = orderID;
	}

	public Long getProID() {
		return ProID;
	}

	public void setProID(Long proID) {
		ProID = proID;
	}

	public float getImportPrice() {
		return ImportPrice;
	}

	public void setImportPrice(float importPrice) {
		ImportPrice = importPrice;
	}

	public float getSellPrice() {
		return SellPrice;
	}

	public void setSellPrice(float sellPrice) {
		SellPrice = sellPrice;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public float getTotal() {
		return Total;
	}

	public void setTotal(float total) {
		Total = total;
	}

    
    
}
