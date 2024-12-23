package hr.javafx.vinceljfx7.controller;

import hr.javafx.vinceljfx7.repository.AbstractRepository;
import hr.javafx.vinceljfx7.repository.RestaurantRepository;
import hr.javafx.vinceljfx7.restaurant.model.Restaurant;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Set;
import java.util.stream.Collectors;

public class SearchRestaurantControler {

    @FXML
    private TextField restaurantNameTextField;

    @FXML
    private TextField restaurantAddressTextField;

    @FXML
    private TableView<Restaurant> restaurantTableView;

    @FXML
    private TableColumn<Restaurant, String> restaurantIdColumn;

    @FXML
    private TableColumn<Restaurant, String> restaurantNameColumn;

    @FXML
    private TableColumn<Restaurant, String> restaurantAddressColumn;

    @FXML
    private TableColumn<Restaurant, String> restaurantMealsColumn;

    @FXML
    private TableColumn<Restaurant, String> restaurantChefsColumn;

    @FXML
    private TableColumn<Restaurant, String> restaurantWaitersColumn;

    @FXML
    private TableColumn<Restaurant, String> restaurantDeliverersColumn;

    private AbstractRepository<Restaurant> restaurantRepository = new RestaurantRepository<>();

    public void initialize() {

        Set <Restaurant> restaurants = restaurantRepository.findAll();

        restaurantIdColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));

        restaurantNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        restaurantAddressColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getAddress().toString()));

        restaurantMealsColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        String.join(", ", cellData.getValue().getMeals().stream()
                                .map(meal -> meal.getName())
                                .toList())));

        restaurantChefsColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        String.join(", ", cellData.getValue().getChefs().stream()
                                .map(chef -> chef.getFirstName() + " " + chef.getLastName())
                                .toList())));

        restaurantDeliverersColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        String.join(", ", cellData.getValue().getDeliverers().stream()
                                .map(deliverer -> deliverer.getFirstName() + " " + deliverer.getLastName())
                                .toList())));

        restaurantWaitersColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        String.join(", ", cellData.getValue().getWaiters().stream()
                                .map(waiter -> waiter.getFirstName() + " " + waiter.getLastName())
                                .toList())));

    }

    public void filterRestaurant() {

        Set<Restaurant> restaurants = restaurantRepository.findAll();

        String nameFilter = restaurantNameTextField.getText();
        if (!nameFilter.isEmpty()) {
            restaurants = restaurants.stream()
                    .filter(restaurant -> restaurant.getName().toLowerCase().contains(nameFilter.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        String addressFilter = restaurantAddressTextField.getText();
        if (!addressFilter.isEmpty()) {
            restaurants = restaurants.stream()
                    .filter(restaurant -> restaurant.getAddress().toString().toLowerCase().contains(addressFilter.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        ObservableSet<Restaurant> restaurantObservableSet = FXCollections.observableSet(restaurants);
        ObservableList<Restaurant> restaurantObservableList = FXCollections.observableArrayList(restaurantObservableSet);

        restaurantObservableList.sort((r1, r2) -> Long.compare(r1.getId(), r2.getId()));

        restaurantTableView.setItems(restaurantObservableList);

    }

}
