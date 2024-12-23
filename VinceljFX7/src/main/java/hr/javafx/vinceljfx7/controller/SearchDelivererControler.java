package hr.javafx.vinceljfx7.controller;

import hr.javafx.vinceljfx7.repository.AbstractRepository;
import hr.javafx.vinceljfx7.repository.DelivererRepository;
import hr.javafx.vinceljfx7.restaurant.enums.ContractType;
import hr.javafx.vinceljfx7.restaurant.model.Deliverer;
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

public class SearchDelivererControler {

    @FXML
    private TextField deliverersFirstNameTextField;

    @FXML
    private TextField deliverersLastNameTextField;

    @FXML
    private TextField deliverersSalaryTextField;

    @FXML
    private ComboBox<ContractType> deliverersContractTypeComboBox;

    @FXML
    private TableView<Deliverer> deliverersTableView;

    @FXML
    private TableColumn<Deliverer, String> deliverersIdColumn;

    @FXML
    private TableColumn<Deliverer, String> deliverersFirstNameColumn;

    @FXML
    private TableColumn<Deliverer, String> deliverersLastNameColumn;

    @FXML
    private TableColumn<Deliverer, String> deliverersSalaryColumn;

    @FXML
    private TableColumn<Deliverer, LocalDate> deliverersStartDateColumn;

    @FXML
    private TableColumn<Deliverer, LocalDate> deliverersEndDateColumn;

    @FXML
    private TableColumn<Deliverer, String> deliverersContractTypeColumn;


    private AbstractRepository<Deliverer> delivererRepository = new DelivererRepository<>();


    public void initialize()
    {
        Set<Deliverer> deliverers = delivererRepository.findAll();

        ObservableList<ContractType> contractTypeObservableList = FXCollections.observableArrayList(ContractType.values());
        deliverersContractTypeComboBox.setItems(contractTypeObservableList);

        deliverersContractTypeComboBox.setConverter(new StringConverter<ContractType>() {
            @Override
            public String toString(ContractType contractType) {
                return contractType != null ? contractType.toString() : "";
            }

            @Override
            public ContractType fromString(String s) {
                return ContractType.valueOf(s);
            }
        });

        deliverersIdColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getId())));

        deliverersFirstNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));

        deliverersLastNameColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));

        deliverersSalaryColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getSalary())));

        deliverersContractTypeColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getContract().getContractType())));

        deliverersStartDateColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getContract().getStartDate()));

        deliverersEndDateColumn.setCellValueFactory(
                cellData -> new SimpleObjectProperty<>(cellData.getValue().getContract().getEndDate()));

        deliverersStartDateColumn.setCellFactory(column -> new TableCell<Deliverer, LocalDate>() {
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

        deliverersEndDateColumn.setCellFactory(column -> new TableCell<Deliverer, LocalDate>() {
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

    public void filterDeliverers()
    {
        Set<Deliverer> deliverers = delivererRepository.findAll();

        String delivererFirstName = deliverersFirstNameTextField.getText();

        if (!delivererFirstName.isEmpty()) {
            deliverers = deliverers.stream()
                    .filter(deliverer -> deliverer.getFirstName().toLowerCase().contains(delivererFirstName.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        String delivererLastName = deliverersLastNameTextField.getText();

        if (!delivererLastName.isEmpty()) {
            deliverers = deliverers.stream()
                    .filter(deliverer -> deliverer.getLastName().toLowerCase().contains(delivererLastName.toLowerCase()))
                    .collect(Collectors.toSet());
        }

        String delivererSalaryInput = deliverersSalaryTextField.getText();
        if (!delivererSalaryInput.isEmpty()) {
            try {
                BigDecimal delivererSalary = new BigDecimal(delivererSalaryInput);
                deliverers = deliverers.stream()
                        .filter(deliverer -> deliverer.getSalary().compareTo(delivererSalary) == 0)
                        .collect(Collectors.toSet());
            } catch (NumberFormatException e)
            {
                System.err.println("Invalid salary input: " + delivererSalaryInput);
                return;
            }
        }

        ContractType selectedContractType = deliverersContractTypeComboBox.getSelectionModel().getSelectedItem();
        if (selectedContractType != null) {
            deliverers = deliverers.stream()
                    .filter(deliverer -> deliverer.getContract().getContractType() == selectedContractType)
                    .collect(Collectors.toSet());
        }

        ObservableSet<Deliverer> delivererObservableSet = FXCollections.observableSet(deliverers);

        ObservableList<Deliverer> delivererObservableList = FXCollections.observableArrayList(delivererObservableSet);
        delivererObservableList.sort((d1, d2) -> Long.compare(d1.getId(), d2.getId()));

        deliverersTableView.setItems(delivererObservableList);
    }

}
