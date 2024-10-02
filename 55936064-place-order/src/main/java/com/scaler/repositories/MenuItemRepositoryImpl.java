package com.scaler.repositories;

import com.scaler.models.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MenuItemRepositoryImpl implements MenuItemRepository{
    Map<Long, MenuItem> map = new HashMap<>();
    @Override
    public MenuItem add(MenuItem menuItem) {
        if (menuItem.getId() == 0)
            menuItem.setId(map.size() + 1);
        map.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    @Override
    public Optional<MenuItem> findById(long id) {
        return Optional.of(map.get(id));
    }
}
