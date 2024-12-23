package hr.javafx.vinceljfx7.repository;

import hr.javafx.vinceljfx7.restaurant.enums.ContractType;
import hr.javafx.vinceljfx7.restaurant.model.Contract;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ContractRepository  {

    public static final String FILE_PATH = "dat/contract.txt";

    public Set<Contract> findAll() {
        Set<Contract> contracts = new HashSet<>();

        try (Stream<String> stream = Files.lines(Path.of(FILE_PATH))) {
            List<String> fileRows = stream.toList();

            while (!fileRows.isEmpty()) {
                BigDecimal salary = new BigDecimal(fileRows.get(0));
                LocalDate startDate = LocalDate.parse(fileRows.get(1));
                LocalDate endDate = LocalDate.parse(fileRows.get(2));
                ContractType contractType = ContractType.valueOf(fileRows.get(3));

                contracts.add(new Contract(salary, startDate, endDate, contractType));

                fileRows = fileRows.subList(4, fileRows.size());
            }
        } catch (IOException e) {
            System.err.println("Greška pri čitanju datoteke: " + e.getMessage());
        }

        return contracts;
    }

    public void save(Set<Contract> entities) {
        try (PrintWriter printWriter = new PrintWriter(FILE_PATH)) {
            for (Contract contract : entities) {
                printWriter.println(contract.getSalary());
                printWriter.println(contract.getStartDate());
                printWriter.println(contract.getEndDate());
                printWriter.println(contract.getContractType());
            }
        } catch (IOException e) {
            System.err.println("Greška pri zapisivanju u datoteku: " + e.getMessage());
        }
    }


    public void save(Contract entity) {
        Set<Contract> contracts = findAll();
        contracts.add(entity);
        save(contracts);
    }
}