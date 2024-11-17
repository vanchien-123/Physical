package com.aptech.demo.Models;

import com.aptech.demo.Enums.OrderStatus;

public class Order {

    private Long ID;
    private String OrderDate;
    private int CusID;
    private float Total;
    private OrderStatus Status;
    private String Receiver;
    private String Address;
    private String Phone;
    private String Note;   
    private String Payment;
    private String TransactionNo ;
    private String TransactionDate ;

    public Order() {
    }

	public Order(Long iD, String orderDate, int cusID, float total, OrderStatus status, String receiver, String address,
			String phone, String note, String payment, String transactionNo, String transactionDate) {
		super();
		ID = iD;
		OrderDate = orderDate;
		CusID = cusID;
		Total = total;
		Status = status;
		Receiver = receiver;
		Address = address;
		Phone = phone;
		Note = note;
		Payment = payment;
		TransactionNo = transactionNo;
		TransactionDate = transactionDate;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(String orderDate) {
		OrderDate = orderDate;
	}

	public int getCusID() {
		return CusID;
	}

	public void setCusID(int cusID) {
		CusID = cusID;
	}

	public float getTotal() {
		return Total;
	}

	public void setTotal(float total) {
		Total = total;
	}

	public OrderStatus getStatus() {
		return Status;
	}

	public void setStatus(OrderStatus status) {
		Status = status;
	}

	public String getReceiver() {
		return Receiver;
	}

	public void setReceiver(String receiver) {
		Receiver = receiver;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public String getPayment() {
		return Payment;
	}

	public void setPayment(String payment) {
		Payment = payment;
	}

	public String getTransactionNo() {
		return TransactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		TransactionNo = transactionNo;
	}

	public String getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}
    
    

    
}
