package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class HelloController {
    @FXML
    public DatePicker fromDate;
    @FXML
    public DatePicker toDate;
    @FXML
    public ComboBox<String> sorting;
    @FXML
    public TableView<Person> table;

    private final ObservableList<Person> masterData = FXCollections.observableArrayList();

    public void initialize() {
        sorting.getItems().addAll("Ascending alphabetically", "Descending alphabetically");

        TableColumn<Person, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Person, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("first_name"));

        TableColumn<Person, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));

        TableColumn<Person, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Person, String> genderColumn = new TableColumn<>("Gender");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Person, String> countryColumn = new TableColumn<>("Country");
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Person, String> domainColumn = new TableColumn<>("Domain");
        domainColumn.setCellValueFactory(new PropertyValueFactory<>("domain"));

        TableColumn<Person, String> birthDateColumn = new TableColumn<>("Birth Date");
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<>("birth_date"));

        table.getColumns().addAll(idColumn, firstNameColumn, lastNameColumn, emailColumn, genderColumn, countryColumn, domainColumn, birthDateColumn);

        loadData();
    }

    private void loadData() {
        Importer importerD1 = new Importer("MOCK_DATA1.csv");
        Importer importerD2 = new Importer("MOCK_DATA2.csv");
        Importer importerD3 = new Importer("MOCK_DATA3.csv");

        masterData.addAll(importerD1.getPeople());
        masterData.addAll(importerD2.getPeople());
        masterData.addAll(importerD3.getPeople());

        table.setItems(masterData);
    }

    @FXML
    public void onFind() {
        LocalDate from = fromDate.getValue();
        LocalDate to = toDate.getValue();

        ObservableList<Person> filteredData = FXCollections.observableArrayList(masterData);
        if (from != null || to != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            filteredData = filteredData.stream()
                    .filter(person -> {
                        LocalDate birthDate = LocalDate.parse(person.getBirth_date(), formatter);
                        boolean afterFrom = from == null || !birthDate.isBefore(from);
                        boolean beforeTo = to == null || !birthDate.isAfter(to);
                        return afterFrom && beforeTo;
                    })
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        String sortingOption = sorting.getValue();
        if ("Ascending alphabetically".equals(sortingOption))
            filteredData.sort((p1, p2) -> p1.getFirst_name().compareToIgnoreCase(p2.getFirst_name()));
        else if ("Descending alphabetically".equals(sortingOption))
            filteredData.sort((p1, p2) -> p2.getFirst_name().compareToIgnoreCase(p1.getFirst_name()));


        table.setItems(filteredData);
    }
}
