package com.scaler.services;

import com.scaler.exceptions.UnAuthorizedAccess;
import com.scaler.exceptions.UserNotFoundException;
import com.scaler.models.*;
import com.scaler.repositories.MenuRepository;
import com.scaler.repositories.UserRepository;

public class MenuServiceImpl implements MenuService{
    private UserRepository userRepository;
    private MenuRepository menuRepository;

    public MenuServiceImpl(UserRepository userRepository, MenuRepository menuRepository) {
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public MenuItem addMenuItem(long userId, String name, double price, String dietaryRequirement, String itemType, String description) throws UserNotFoundException, UnAuthorizedAccess {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found Exception"));
        if (!user.getUserType().equals(UserType.ADMIN)) throw new UnAuthorizedAccess("UnAuthorized Access");
        MenuItem menuItem = new MenuItem();
        menuItem.setName(name);
        menuItem.setItemType(ItemType.valueOf(itemType));
        menuItem.setPrice(price);
        menuItem.setDescription(description);
        menuItem.setDietaryRequirement(DietaryRequirement.valueOf(dietaryRequirement));
        return menuRepository.add(menuItem);
    }
}
