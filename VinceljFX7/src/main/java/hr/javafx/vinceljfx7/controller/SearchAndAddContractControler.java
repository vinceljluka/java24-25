package hr.javafx.vinceljfx7.controller;

import hr.javafx.vinceljfx7.repository.ContractRepository;
import hr.javafx.vinceljfx7.restaurant.enums.ContractType;
import hr.javafx.vinceljfx7.restaurant.model.Contract;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class SearchAndAddContractControler {

    private static final DateTimeFormatter HR_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

    @FXML
    private TextField salarySearchTextField;

    @FXML
    private DatePicker dateSearchDatePicker;

    @FXML
    private ComboBox<ContractType> contractTypeComboBoxComboBoxSearch;

    @FXML
    private TextField salarySaveTextField;

    @FXML
    private DatePicker startDateSaveDatePicker;

    @FXML
    private DatePicker endDateSaveDatePicker;

    @FXML
    private ComboBox<ContractType> contractTypeComboBoxSave;

    @FXML
    private TableView<Contract> contractTableView;

    @FXML
    private TableColumn<Contract, String> contractSalaryTableColumn;

    @FXML
    private TableColumn<Contract, String> contractStartDateTableColumn;

    @FXML
    private TableColumn<Contract, String> contractEndDateTableColumn;

    @FXML
    private TableColumn<Contract, String> contractTypeTableColumn;

    @FXML
    private CheckBox checkBox;

    private final ContractRepository contractRepository = new ContractRepository();

    public void initialize() {

        contractTypeComboBoxComboBoxSearch.getItems().setAll(ContractType.values());
        contractTypeComboBoxSave.getItems().setAll(ContractType.values());

        contractSalaryTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSalary().toString()));
        contractStartDateTableColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getStartDate().format(HR_DATE_FORMATTER)));
        contractEndDateTableColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEndDate().format(HR_DATE_FORMATTER)));
        contractTypeTableColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getContractType().toString()));

        refreshTable();
    }

    public void filterContract() {
        String salaryInput = salarySearchTextField.getText();
        LocalDate searchDate = dateSearchDatePicker.getValue();
        ContractType selectedType = contractTypeComboBoxComboBoxSearch.getValue();

        Set<Contract> filteredContracts = contractRepository.findAll();

        if (!salaryInput.isBlank()) {
            BigDecimal salary = new BigDecimal(salaryInput);
            filteredContracts.removeIf(contract -> contract.getSalary().compareTo(salary) != 0);
        }

        if (searchDate != null) {
            filteredContracts.removeIf(contract -> !contract.getEndDate().isAfter(searchDate));
        }

        if (selectedType != null) {
            filteredContracts.removeIf(contract -> !contract.getContractType().equals(selectedType));
        }

        contractTableView.getItems().setAll(filteredContracts);
    }

    public void addNewContract() {
        try {
            BigDecimal salary = new BigDecimal(salarySaveTextField.getText());
            LocalDate startDate = startDateSaveDatePicker.getValue();
            LocalDate endDate = endDateSaveDatePicker.getValue();
            ContractType contractType = contractTypeComboBoxSave.getValue();

            if (salary.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Plaća mora biti pozitivna vrijednost.");
            }

            if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
                throw new IllegalArgumentException("Datumi nisu ispravni.");
            }

            Contract newContract = new Contract(salary, startDate, endDate, contractType);
            contractRepository.save(newContract);

            refreshTable();

            salarySaveTextField.clear();
            startDateSaveDatePicker.setValue(null);
            endDateSaveDatePicker.setValue(null);
            contractTypeComboBoxSave.setValue(null);

        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Greška u unosu", e.getMessage());
        }
    }

    private void refreshTable() {
        Set<Contract> allContracts = contractRepository.findAll();
        contractTableView.getItems().setAll(allContracts);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
