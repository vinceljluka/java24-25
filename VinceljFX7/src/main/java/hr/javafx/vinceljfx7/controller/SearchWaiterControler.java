package hr.javafx.vinceljfx7.controller;

import hr.javafx.vinceljfx7.repository.AbstractRepository;
import hr.javafx.vinceljfx7.repository.WaiterRepository;
import hr.javafx.vinceljfx7.restaurant.enums.ContractType;
import hr.javafx.vinceljfx7.restaurant.model.Waiter;
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

public class SearchWaiterControler {
    @FXML
    private TextField waiterFirstNameTextField;

    @FXML
    private TextField waiterLastNameTextField;

    @FXML
    private TextField waiterSalaryTextField;

    @FXML
    private ComboBox<ContractType> waiterContractTypeComboBox;

    @FXML
    private TableView<Waiter> waiterTableView;

    @FXML
    private TableColumn<Waiter, String> waiterIdColumn;

    @FXML
    private TableColumn<Waiter, String> waiterFirstNameColumn;

    @FXML
    private TableColumn<Waiter, String> waiterLastNameColumn;

    @FXML
    private TableColumn<Waiter, String> waiterSalaryColumn;

    @FXML
    private TableColumn<Waiter, LocalDate> waiterStartDateColumn;

    @FXML
    private TableColumn<Waiter, LocalDate> waiterEndDateColumn;

    @FXML
    private TableColumn<Waiter, String> waiterContractTypeColumn;


    private AbstractRepository<Waiter> waiterRepository = new WaiterRepository<>();

    public void initialize() {
        Set<Waiter> waiters = waiterRepository.findAll();

        ObservableList<ContractType> contractTypeObservableList = FXCollections.observableArrayList(ContractType.values());
        waiterContractTypeComboBox.setItems(contractTypeObservableList);

        waiterContractTypeComboBox.setConverter(new StringConverter<ContractType>() {
            @Override
            public String toString(ContractType contractType) {
                return contractType != null ? contractType.toString() : "";
            }

            @Override
            public ContractType fromString(String s) {
                return ContractType.valueOf(s);
            }
        });

        waiterIdColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));

        waiterFirstNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));

        waiterLastNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));

        waiterSalaryColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSalary())));

        waiterContractTypeColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getContract().getContractType())));

        waiterStartDateColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getContract().getStartDate()));

        waiterEndDateColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getContract().getEndDate()));

        waiterStartDateColumn.setCellFactory(column -> new TableCell<Waiter, LocalDate>() {
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

        waiterEndDateColumn.setCellFactory(column -> new TableCell<Waiter, LocalDate>() {
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

    public void filterWaiters() {
        Set<Waiter> waiters = waiterRepository.findAll();

        String waiterFirstName = waiterFirstNameTextField.getText();
        if (!waiterFirstName.isEmpty()) {
            waiters = waiters.stream()
                    .filter(waiter -> waiter.getFirstName().toLowerCase().contains(waiterFirstName.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        String waiterLastName = waiterLastNameTextField.getText();
        if (!waiterLastName.isEmpty()) {
            waiters = waiters.stream()
                    .filter(waiter -> waiter.getLastName().toLowerCase().contains(waiterLastName.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        String waiterSalaryInput = waiterSalaryTextField.getText();
        if (!waiterSalaryInput.isEmpty()) {
            try {
                BigDecimal waiterSalary = new BigDecimal(waiterSalaryInput);
                waiters = waiters.stream()
                        .filter(waiter -> waiter.getSalary().compareTo(waiterSalary) == 0)
                        .collect(Collectors.toSet());
            } catch (NumberFormatException e) {
                System.err.println("Invalid salary input: " + waiterSalaryInput);
                return;
            }
        }

        ContractType selectedContractType = waiterContractTypeComboBox.getSelectionModel().getSelectedItem();
        if (selectedContractType != null) {
            waiters = waiters.stream()
                    .filter(waiter -> waiter.getContract().getContractType() == selectedContractType)
                    .collect(Collectors.toSet());
        }

        ObservableSet<Waiter> waiterObservableSet = FXCollections.observableSet(waiters);

        ObservableList<Waiter> waiterObservableList = FXCollections.observableArrayList(waiterObservableSet);
        waiterObservableList.sort((w1, w2) -> Long.compare(w1.getId(), w2.getId()));

        waiterTableView.setItems(waiterObservableList);
    }

}
