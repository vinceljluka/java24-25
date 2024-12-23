package hr.javafx.vinceljfx7.controller;

import hr.javafx.vinceljfx7.repository.AbstractRepository;
import hr.javafx.vinceljfx7.repository.ChefRepository;
import hr.javafx.vinceljfx7.restaurant.enums.ContractType;
import hr.javafx.vinceljfx7.restaurant.model.Chef;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchChefControler {

    @FXML
    private TextField chefFirstNameTextField;

    @FXML
    private TextField chefLastNameTextField;

    @FXML
    private TextField chefSalaryTextField;

    @FXML
    private ComboBox<ContractType> chefContractTypeComboBox;

    @FXML
    private TableView<Chef> chefTableView;

    @FXML
    private TableColumn<Chef, String> chefIdColumn;

    @FXML
    private TableColumn<Chef, String> chefFirstNameColumn;

    @FXML
    private TableColumn<Chef, String> chefLastNameColumn;

    @FXML
    private TableColumn<Chef, String> chefSalaryColumn;

    @FXML
    private TableColumn<Chef, LocalDate> chefStartDateColumn;

    @FXML
    private TableColumn<Chef, LocalDate> chefEndDateColumn;

    @FXML
    private TableColumn<Chef, String> chefContractTypeColumn;


    private AbstractRepository<Chef> chefRepository = new ChefRepository<>();

    public void initialize() {

        Set<Chef> chefs = chefRepository.findAll();

        ObservableList<ContractType> contractTypeObservableList = FXCollections.observableArrayList(ContractType.values());
        chefContractTypeComboBox.setItems(contractTypeObservableList);

        chefContractTypeComboBox.setConverter(new StringConverter<ContractType>() {
            @Override
            public String toString(ContractType contractType) {
                return contractType != null ? contractType.toString() : "";
            }

            @Override
            public ContractType fromString(String s) {
                return ContractType.valueOf(s);
            }
        });

        chefIdColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));

        chefFirstNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));

        chefLastNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));

        chefSalaryColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSalary())));

        chefContractTypeColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getContract().getContractType())));

        chefStartDateColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getContract().getStartDate()));

        chefEndDateColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getContract().getEndDate()));

        chefStartDateColumn.setCellFactory(column -> new TableCell<Chef, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                }
            }
        });

        chefEndDateColumn.setCellFactory(column -> new TableCell<Chef, LocalDate>() {
            @Override
            protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                }
            }
        });

    }

    public void filterChefs() {
        Set<Chef> chefs = chefRepository.findAll();

        String chefFirstName = chefFirstNameTextField.getText();

        if (!chefFirstName.isEmpty()) {
            chefs = chefs.stream()
                    .filter(chef -> chef.getFirstName().toLowerCase().contains(chefFirstName.toLowerCase()))
                    .collect(Collectors.toSet());
        }
        String chefLastName = chefLastNameTextField.getText();

        if (!chefLastName.isEmpty()) {
            chefs = chefs.stream()
                    .filter(chef -> chef.getLastName().toLowerCase().contains(chefLastName.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        String chefSalaryInput = chefSalaryTextField.getText();
        if (!chefSalaryInput.isEmpty()) {
            try {
                BigDecimal chefSalary = new BigDecimal(chefSalaryInput);
                chefs = chefs.stream()
                        .filter(chef -> chef.getSalary().compareTo(chefSalary) == 0)
                        .collect(Collectors.toSet());
            } catch (NumberFormatException e)
            {
                System.err.println("Invalid salary input: " + chefSalaryInput);
                return;
            }
        }

        ContractType selectedContractType = chefContractTypeComboBox.getSelectionModel().getSelectedItem();
        if (selectedContractType != null) {
            chefs = chefs.stream()
                    .filter(chef -> chef.getContract().getContractType() == selectedContractType)
                    .collect(Collectors.toSet());
        }

        ObservableSet<Chef> chefObservableSet = FXCollections.observableSet(chefs);

        ObservableList<Chef> chefObservableList = FXCollections.observableArrayList(chefObservableSet);
        chefObservableList.sort((c1, c2) -> Long.compare(c1.getId(), c2.getId()));

        chefTableView.setItems(chefObservableList);

    }

}
