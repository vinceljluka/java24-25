package hr.javafx.vinceljfx7.controller;

import hr.javafx.vinceljfx7.repository.AbstractRepository;
import hr.javafx.vinceljfx7.repository.CategoryRepository;
import hr.javafx.vinceljfx7.repository.IngredientRepository;
import hr.javafx.vinceljfx7.restaurant.model.Category;
import hr.javafx.vinceljfx7.restaurant.model.Ingredient;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchIngredientControler {

    @FXML
    private TextField ingredientNameTextField;

    @FXML
    private ComboBox<Category> ingredientComboBox;

    @FXML
    private TextField ingredientKcalTextField;

    @FXML
    private TableView<Ingredient> ingredientTableView;

    @FXML
    private TableColumn<Ingredient, String> ingredientNameColumn;

    @FXML
    private TableColumn<Ingredient, String> ingredientKcalColumn;

    @FXML
    private TableColumn<Ingredient, String> ingredientPreparationColumn;

    @FXML
    private TableColumn<Ingredient, String> ingredientCategoryColumn;

    @FXML
    private TableColumn<Ingredient, String> ingredientIdColumn;

    private AbstractRepository<Ingredient> ingredientRepository = new IngredientRepository<>();
    private AbstractRepository<Category> categoryRepository = new CategoryRepository<>();

    public void initialize() {

        Set<Category> categories = categoryRepository.findAll();

        ObservableList<Category> categoryList = FXCollections.observableArrayList(categories);
        ingredientComboBox.setItems(categoryList);

        ingredientComboBox.setConverter(new StringConverter<Category>() {
            @Override
            public String toString(Category category)
            {
                if (category != null) {
                    return category.getName();
                } else {
                    return "";
                }
            }

            @Override
            public Category fromString(String string) {
                return null;
            }

        });

        ingredientIdColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getId().toString()));

        ingredientNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        ingredientKcalColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getKcal().toString()));

        ingredientCategoryColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getCategory().getName()));

        ingredientPreparationColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPreparationMethod()));
    }

    public void filterIngredients() {
        Set<Ingredient> ingredients = ingredientRepository.findAll();

        String ingredientName = ingredientNameTextField.getText();

        if (!ingredientName.isEmpty())
        {
            ingredients = ingredients.stream()
                    .filter(ingredient -> ingredient.getName().toLowerCase().contains(ingredientName.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        String ingredientKcalInput = ingredientKcalTextField.getText();
        if (!ingredientKcalInput.isEmpty()) {
            try {
                BigDecimal ingredientKcal = new BigDecimal(ingredientKcalInput);
                ingredients = ingredients.stream()
                        .filter(ingredient -> ingredient.getKcal().compareTo(ingredientKcal) == 0)
                        .collect(Collectors.toSet());
            } catch (NumberFormatException e)
            {
                System.err.println("Invalid kcal input: " + ingredientKcalInput);
                return;
            }
        }

        Category selectedCategory = ingredientComboBox.getSelectionModel().getSelectedItem();

        if (selectedCategory != null) {
            ingredients = ingredients.stream()
                    .filter(ingredient -> ingredient.getCategory().getName().equalsIgnoreCase(selectedCategory.getName()))
                    .collect(Collectors.toSet());
        }

        ObservableSet<Ingredient> ingredientObservableSet = FXCollections.observableSet(ingredients);

        ObservableList<Ingredient> ingredientObservableList = FXCollections.observableArrayList(ingredientObservableSet);
        ingredientObservableList.sort((c1, c2) -> Long.compare(c1.getId(), c2.getId()));

        ingredientTableView.setItems(ingredientObservableList);

    }
}
