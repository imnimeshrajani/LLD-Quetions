package com.scaler.services;

import com.scaler.models.DietaryRequirement;
import com.scaler.models.MenuItem;
import com.scaler.repositories.MenuRepository;

import java.util.List;

public class MenuServiceImpl implements MenuService{
    private MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<MenuItem> getMenuItems(String itemType) throws Exception {
        List<MenuItem> menuItemList;
        if (itemType == null) return menuRepository.getAll();
        DietaryRequirement dr;
        try {
            dr = DietaryRequirement.valueOf(itemType);
        } catch (Exception e) {
            throw new Exception("Item Type Is Invalid");
        }
        menuItemList = menuRepository.getByDietaryRequirement(dr);
        return menuItemList;
    }
}
