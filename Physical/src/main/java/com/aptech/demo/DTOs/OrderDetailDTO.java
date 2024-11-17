package com.aptech.demo.DTOs;

public class OrderDetailDTO {
    private int stt;

    private Long ID;

    private String Name;

    private float ImportPrice;

    private float SellPrice;

    private int Quantity;

    private float SubTotal;

    private float Total;
    
    public OrderDetailDTO() {
    }
    

    public OrderDetailDTO(int stt, Long iD, String name, float importPrice, float sellPrice, int quantity,
			float subTotal, float total) {
		super();
		this.stt = stt;
		ID = iD;
		Name = name;
		ImportPrice = importPrice;
		SellPrice = sellPrice;
		Quantity = quantity;
		SubTotal = subTotal;
		Total = total;
	}


	public int getStt() {
		return stt;
	}


	public void setStt(int stt) {
		this.stt = stt;
	}


	public Long getID() {
		return ID;
	}


	public void setID(Long iD) {
		ID = iD;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
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


	public float getSubTotal() {
		return SubTotal;
	}


	public void setSubTotal(float subTotal) {
		SubTotal = subTotal;
	}


	public float getTotal() {
		return Total;
	}


	public void setTotal(float total) {
		Total = total;
	}

	
    

    
}
