package com.aptech.demo.Models;

public class Product {

    private Long ID;
    private String Name;
    private String Image;
    private float ImportPrice;
    private float SellPrice;
    private String Description;
    private Long TypeID;
    private String TypeName;
    private String Status;
    public Product() {
    }
	public Product(Long iD, String name, String image, float importPrice, float sellPrice, String description,
			Long typeID, String typeName, String status) {
		super();
		ID = iD;
		Name = name;
		Image = image;
		ImportPrice = importPrice;
		SellPrice = sellPrice;
		Description = description;
		TypeID = typeID;
		TypeName = typeName;
		Status = status;
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
	public String getImage() {
		return Image;
	}
	public void setImage(String image) {
		Image = image;
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
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public Long getTypeID() {
		return TypeID;
	}
	public void setTypeID(Long typeID) {
		TypeID = typeID;
	}
	public String getTypeName() {
		return TypeName;
	}
	public void setTypeName(String typeName) {
		TypeName = typeName;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}

    
   
}
