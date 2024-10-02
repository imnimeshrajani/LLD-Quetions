package com.scaler.services;

import com.scaler.exceptions.CustomerSessionNotFound;
import com.scaler.exceptions.InvalidMenuItem;
import com.scaler.exceptions.UnAuthorizedAccess;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.*;
import com.scaler.repositories.CustomerSessionRepository;
import com.scaler.repositories.MenuItemRepository;
import com.scaler.repositories.OrderRepository;
import com.scaler.repositories.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService{

    private UserRepository userRepository;
    private CustomerSessionRepository customerSessionRepository;
    private MenuItemRepository menuItemRepository;
    private OrderRepository orderRepository;

    public OrderServiceImpl(UserRepository userRepository, CustomerSessionRepository customerSessionRepository, MenuItemRepository menuItemRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.customerSessionRepository = customerSessionRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Order placeOrder(long userId, Map<Long, Integer> orderedItems) throws UserNotFoundException, InvalidMenuItem {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        CustomerSession customerSession;
        Optional<CustomerSession> optionalCustomerSession = customerSessionRepository.findActiveCustomerSessionByUserId(userId);

        if (optionalCustomerSession.isEmpty()) {
            customerSession = new CustomerSession();
            customerSession.setUser(user);
            customerSession.setCustomerSessionStatus(CustomerSessionStatus.ACTIVE);
            customerSessionRepository.save(customerSession);
        } else customerSession = optionalCustomerSession.get();

        Map<MenuItem, Integer> map = new HashMap<>();
        for(Map.Entry<Long, Integer> orderedItem : orderedItems.entrySet()) {
            MenuItem menuItem = menuItemRepository.findById(orderedItem.getKey()).orElseThrow(() -> new InvalidMenuItem("Invalid Menu Item"));
            map.put(menuItem, map.getOrDefault(menuItem, 0) + orderedItem.getValue());
        }
        Order order = new Order();
        order.setOrderedItems(map);
        order.setCustomerSession(customerSession);
        return orderRepository.save(order);
    }
}
