package com.aptech.demo.DTOs;

import java.sql.Date;

import com.aptech.demo.Enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@SuppressWarnings("unused")
public class OrderDTO {

    private int stt;

    private Long ID;

    private String Receiver;

    private String Address;

    private String Phone;

    private Date OrderDate;

    private float Total;    

    private OrderStatus Status;

    private String Note;

    private Long TransactionNo;

    private String TransactionDate;

    private String Payment;

    public OrderDTO() {
    }

	public OrderDTO(int stt, Long iD, String receiver, String address, String phone, Date orderDate, float total,
			OrderStatus status, String note, Long transactionNo, String transactionDate, String payment) {
		super();
		this.stt = stt;
		ID = iD;
		Receiver = receiver;
		Address = address;
		Phone = phone;
		OrderDate = orderDate;
		Total = total;
		Status = status;
		Note = note;
		TransactionNo = transactionNo;
		TransactionDate = transactionDate;
		Payment = payment;
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

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
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

	public String getNote() {
		return Note;
	}

	public void setNote(String note) {
		Note = note;
	}

	public Long getTransactionNo() {
		return TransactionNo;
	}

	public void setTransactionNo(Long transactionNo) {
		TransactionNo = transactionNo;
	}

	public String getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		TransactionDate = transactionDate;
	}

	public String getPayment() {
		return Payment;
	}

	public void setPayment(String payment) {
		Payment = payment;
	}
    
    
    
	
}
