package hr.java.utils;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.model.*;

public class DuplicateNameCheck {

    public static void checkDuplicateDish(String name, Meal[] dishes, int currentIndex) throws DuplicateEntityException
    {
        for (int i = 0; i < currentIndex; i++)
        {
            if (dishes[i] != null && dishes[i].getName().equals(name))
            {
                throw new DuplicateEntityException("Dish with the name " + name + " already exists.");
            }
        }
    }

    public static void checkDuplicateIngredient(String name, Ingredient[] ingredients, int currentIndex) throws DuplicateEntityException
    {
        for (int i = 0; i < currentIndex; i++)
        {
            if (ingredients[i] != null && ingredients[i].getName().equals(name))
            {
                throw new DuplicateEntityException("Ingredient with the name " + name + " already exists.");
            }
        }
    }

    public static void checkDuplicateRestaurant(String name, Restaurant[] restaurants, int currentIndex) throws DuplicateEntityException
    {
        for (int i = 0; i < currentIndex; i++)
        {
            if (restaurants[i] != null && restaurants[i].getName().equals(name))
            {
                throw new DuplicateEntityException("Restaurant with the name " + name + " already exists.");
            }
        }
    }

    public static void checkDuplicateCategory(String name, Category[] categories, int currentIndex) throws DuplicateEntityException
    {
        for (int i = 0; i < currentIndex; i++)
        {
            if (categories[i].getName().equals(name))
            {
                throw new DuplicateEntityException("Category with name " + name + " already exists.");
            }
        }
    }

    public static void checkDuplicateChef(String name, String surName, Chef[] chefs, int currentIndex) throws DuplicateEntityException
    {
        for (int i = 0; i < currentIndex; i++)
        {
            if (chefs[i].getFirstName().equals(name) && chefs[i].getLastName().equals(surName))
            {
                throw new DuplicateEntityException("Chef with name " + name + " " + surName + " already exists.");
            }
        }
    }

    public static void checkDuplicateWaiter(String name, String surName, Waiter[] waiters, int currentIndex) throws DuplicateEntityException
    {
        for (int i = 0; i < currentIndex; i++)
        {
            if (waiters[i].getFirstName().equals(name) && waiters[i].getLastName().equals(surName))
            {
                throw new DuplicateEntityException("Waiter with name " + name + " " + surName + " already exists.");
            }
        }
    }

    public static void checkDuplicateDeliverer(String name, String surName, Deliverer[] deliverers, int currentIndex) throws DuplicateEntityException
    {
        for (int i = 0; i < currentIndex; i++)
        {
            if (deliverers[i].getFirstName().equals(name) && deliverers[i].getLastName().equals(surName))
            {
                throw new DuplicateEntityException("Deliverer with name " + name + " " + surName + " already exists.");
            }
        }
    }

}
