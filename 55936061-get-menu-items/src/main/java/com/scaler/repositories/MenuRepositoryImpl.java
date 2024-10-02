package com.scaler.repositories;

import com.scaler.models.DietaryRequirement;
import com.scaler.models.ItemType;
import com.scaler.models.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuRepositoryImpl implements MenuRepository{
    Map<DietaryRequirement, List<MenuItem>> map = new HashMap<>();
    @Override
    public MenuItem add(MenuItem menuItem) {
        if (menuItem.getId() == 0) menuItem.setId(map.size() + 1);
        map.putIfAbsent(menuItem.getDietaryRequirement(), new ArrayList<>());
        map.get(menuItem.getDietaryRequirement()).add(menuItem);
        return menuItem;
    }

    @Override
    public List<MenuItem> getAll() {
        List<MenuItem> list = new ArrayList<>();
        for (Map.Entry<DietaryRequirement, List<MenuItem>> dietaryRequirementListEntry : map.entrySet()) {
            list.addAll(dietaryRequirementListEntry.getValue());
        }
        return list;
    }

    @Override
    public List<MenuItem> getByDietaryRequirement(DietaryRequirement dietaryRequirement) {
        return map.get(dietaryRequirement);
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        return add(menuItem);
    }
}
