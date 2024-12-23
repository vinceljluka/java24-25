package hr.javafx.vinceljfx7.controller;

import hr.javafx.vinceljfx7.repository.AbstractRepository;
import hr.javafx.vinceljfx7.repository.CategoryRepository;
import hr.javafx.vinceljfx7.restaurant.model.Category;
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

public class SearchCategoryControler {

    @FXML
    private TextField categoryNameTextField;

    @FXML
    private TextField categoryDescriptionTextField;

    @FXML
    private TableView<Category> categoryTableView;

    @FXML
    private TableColumn<Category, String> categoryIdColumn;

    @FXML
    private TableColumn<Category, String> categoryNameColumn;

    @FXML
    private TableColumn<Category, String> categoryDescriptionColumn;

    private AbstractRepository<Category> categoryRepository = new CategoryRepository<Category>();

    public void initialize() {

        categoryNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getName()));

        categoryDescriptionColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        categoryIdColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));
    }

    public void filterCategory() {
        Set<Category> categories = categoryRepository.findAll();

        String categoryName = categoryNameTextField.getText();

        if (!categoryName.isEmpty())
        {
            categories = categories.stream()
                    .filter(category -> category.getName().toLowerCase().contains(categoryName.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        String categoryDescription = categoryDescriptionTextField.getText();

        if (!categoryDescription.isEmpty())
        {
            categories = categories.stream()
                    .filter(category -> category.getDescription().toLowerCase().contains(categoryDescription.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        ObservableSet<Category> categoryObservableSet = FXCollections.observableSet(categories);

        ObservableList<Category> categoryObservableList = FXCollections.observableArrayList(categoryObservableSet);
        categoryObservableList.sort((c1, c2) -> Long.compare(c1.getId(), c2.getId()));

        categoryTableView.setItems(categoryObservableList);
    }

}
