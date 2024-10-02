package com.scaler.repositories;

import com.scaler.models.CustomerSession;
import com.scaler.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomerSessionRepositoryImpl implements CustomerSessionRepository{
    Map<Long, CustomerSession> map = new HashMap<>();
    @Override
    public CustomerSession save(CustomerSession customerSession) {
        map.put(customerSession.getUser().getId(), customerSession);
        return customerSession;
    }

    @Override
    public Optional<CustomerSession> findActiveCustomerSessionByUserId(long userId) {
        CustomerSession customerSession = map.get(userId);
        return (customerSession == null || !customerSession.isActive()) ? Optional.empty() : Optional.of(customerSession);

    }
}
