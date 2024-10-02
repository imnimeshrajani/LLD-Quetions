package com.scaler.repositories;

import com.scaler.models.Order;
import com.scaler.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepositoryImpl implements OrderRepository{
    Map<Long, List<Order>> map = new HashMap<>();
    @Override
    public Order save(Order order) {
        User user = order.getCustomerSession().getUser();
        map.putIfAbsent(user.getId(), new ArrayList<>());
        map.get(user.getId()).add(order);
        return order;
    }
}
