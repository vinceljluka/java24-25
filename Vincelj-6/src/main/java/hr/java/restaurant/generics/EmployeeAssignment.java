package hr.java.restaurant.generics;

import hr.java.restaurant.model.Person;
import hr.java.restaurant.model.Restaurant;

public class EmployeeAssignment<S extends Person, T extends Restaurant> {
    private S employee;
    private T restaurant;

    public EmployeeAssignment(S employee, T restaurant) {
        this.employee = employee;
        this.restaurant = restaurant;
    }

    public S getEmployee() {
        return employee;
    }

    public void setEmployee(S employee) {
        this.employee = employee;
    }

    public T getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(T restaurant) {
        this.restaurant = restaurant;
    }
}