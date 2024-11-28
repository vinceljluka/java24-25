package hr.java.utils;

import hr.java.restaurant.exception.DuplicateEntityException;
import hr.java.restaurant.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DuplicateNameCheck {

    public static void checkDuplicateDish(String name, List <Meal> dishes, int currentIndex) throws DuplicateEntityException
    {
        for (int i = 0; i < currentIndex; i++)
        {
            if (dishes.get(i) != null && dishes.get(i).getName().equals(name))
            {
                throw new DuplicateEntityException("Dish with the name " + name + " already exists.");
            }
        }
    }

    public static void checkDuplicateIngredient(String name, List<Ingredient> ingredients, int currentIndex) throws DuplicateEntityException
    {
        for (int i = 0; i < currentIndex; i++)
        {
            if (ingredients.get(i) != null && ingredients.get(i).getName().equals(name))
            {
                throw new DuplicateEntityException("Ingredient with the name " + name + " already exists.");
            }
        }
    }


    public static void checkDuplicateCategory(String name, List<Category> categories, int currentIndex) throws DuplicateEntityException
    {
        for (int i = 0; i < currentIndex; i++)
        {
            if (categories.get(i).getName().equals(name))
            {
                throw new DuplicateEntityException("Category with name " + name + " already exists.");
            }
        }
    }

    public static void checkDuplicateChef(String name, String surName, Set<Chef> chefs, int currentIndex) throws DuplicateEntityException
    {
        List<Chef> chefList = new ArrayList<>(chefs);
        for (int i = 0; i < currentIndex; i++)
        {
            if (chefList.get(i).getFirstName().equals(name) && chefList.get(i).getLastName().equals(surName))
            {
                throw new DuplicateEntityException("Chef with name " + name + " " + surName + " already exists.");
            }
        }
    }

    public static void checkDuplicateWaiter(String name, String surName, Set <Waiter> waiters, int currentIndex) throws DuplicateEntityException
    {
        List<Waiter> waiterList = new ArrayList<>(waiters);
        for (int i = 0; i < currentIndex; i++)
        {
            if (waiterList.get(i).getFirstName().equals(name) && waiterList.get(i).getLastName().equals(surName))
            {
                throw new DuplicateEntityException("Waiter with name " + name + " " + surName + " already exists.");
            }
        }
    }

    public static void checkDuplicateDeliverer(String name, String surName, Set<Deliverer> deliverers, int currentIndex) throws DuplicateEntityException
    {
        List<Deliverer> delivererList = new ArrayList<>(deliverers);
        for (int i = 0; i < currentIndex; i++)
        {
            if (delivererList.get(i).getFirstName().equals(name) && delivererList.get(i).getLastName().equals(surName))
            {
                throw new DuplicateEntityException("Deliverer with name " + name + " " + surName + " already exists.");
            }
        }
    }

}
