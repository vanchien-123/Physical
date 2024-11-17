package com.aptech.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptech.demo.Enums.OrderStatus;
import com.aptech.demo.Models.Order;
import com.aptech.demo.Repositories.OrderRepository;
import com.aptech.demo.Repositories.repositoryDTO.OrderRepoDTO;

@Service
public class OrderService {
	
	@Autowired
    private OrderRepoDTO orderRepositoryDTO;
	@Autowired
    private OrderRepository orderRepository;

    public boolean updateOrderStatus(Long orderId, String newStatus) {
        Order order = orderRepository.findById(orderId);

        OrderStatus currentStatus = order.getStatus();
        if (currentStatus.canTransitionTo(newStatus)) {
        	orderRepositoryDTO.updateStatus(orderId, newStatus);
            return true;
        } else {
            return false;
        }
    }
}
