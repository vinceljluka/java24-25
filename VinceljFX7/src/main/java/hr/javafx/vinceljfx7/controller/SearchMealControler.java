package hr.javafx.vinceljfx7.controller;

import hr.javafx.vinceljfx7.repository.AbstractRepository;
import hr.javafx.vinceljfx7.repository.MealRepository;
import hr.javafx.vinceljfx7.restaurant.model.Meal;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchMealControler {

    @FXML
    private TextField mealNameTextField;

    @FXML
    private TextField mealTypeTextField;

    @FXML
    private TextField mealPriceTextField;

    @FXML
    private TableView<Meal> mealTableView;

    @FXML
    private TableColumn<Meal, String> mealIdColumn;

    @FXML
    private TableColumn<Meal, String> mealNameColumn;

    @FXML
    private TableColumn<Meal, String> mealTypeColumn;

    @FXML
    private TableColumn<Meal, String> mealCategoryColumn;

    @FXML
    private TableColumn<Meal, String> mealIngredientsColumn;

    @FXML
    private TableColumn<Meal, String> mealPriceColumn;

    private AbstractRepository<Meal> mealRepository = new MealRepository<>();

    public void initialize() {

        Set<Meal> meals = mealRepository.findAll();

        mealIdColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));

        mealNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        mealTypeColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getMealType()));

        mealCategoryColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));

        mealIngredientsColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(
                        String.join(", ", cellData.getValue().getIngredients().stream()
                                .map(ingredient -> ingredient.getName())
                                .toList())));

        mealPriceColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPrice().toString()));

    }

    public void filterMeals() {

        Set<Meal> meals = mealRepository.findAll();

        String mealName = mealNameTextField.getText();
        if (!mealName.isEmpty()) {
            meals = meals.stream()
                    .filter(meal -> meal.getName().toLowerCase().contains(mealName.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        String mealType = mealTypeTextField.getText();
        if (!mealType.isEmpty()) {
            meals = meals.stream()
                    .filter(meal -> meal.getMealType().toLowerCase().contains(mealType.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        String mealPrice = mealPriceTextField.getText();
        if (!mealPrice.isEmpty()) {
            try {
                BigDecimal price = new BigDecimal(mealPrice);
                meals = meals.stream()
                        .filter(meal -> meal.getPrice().compareTo(price) == 0)
                        .collect(Collectors.toSet());
            } catch (NumberFormatException e) {
                System.out.println("Invalid price input: " + e.getMessage());
            }
        }

        ObservableSet<Meal> mealObservableSet = FXCollections.observableSet(meals);
        ObservableList<Meal> mealObservableList = FXCollections.observableArrayList(mealObservableSet);

        mealObservableList.sort((m1, m2) -> Long.compare(m1.getId(), m2.getId()));

        mealTableView.setItems(mealObservableList);
    }

}
