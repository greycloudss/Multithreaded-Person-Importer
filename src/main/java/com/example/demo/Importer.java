package com.example.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Importer {
    private final ArrayList<Person> People = new ArrayList<>();
    private final Thread thisThread;

    Importer(String fileName) {
        thisThread = new Thread(() -> {
            try {
                File file = new File(fileName);
                Scanner myReader = new Scanner(file);
                myReader.nextLine();
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    Person person = chopData(data);
                    if (person != null) {
                        People.add(person);
                    }
                    person.printData();

                    //try {
                    //    Thread.sleep(500);
                    //} catch (InterruptedException e) {}
                }
                myReader.close();
            } catch (Exception e) {
                System.out.println("Failed reading/opening file: " + e.getMessage());
            }
        });
        thisThread.start();
    }

    public ArrayList<Person> getPeople() {
        try {
            thisThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
        return People;
    }

    private static Person chopData(String data) {

        ArrayList<String> fields = new ArrayList<>();
        try {
            StringBuilder currentField = new StringBuilder();
            boolean inQuotes = false;

            for (char c : data.toCharArray()) {
                if (c == '"') {
                    inQuotes = !inQuotes;
                } else if (c == ',' && !inQuotes) {
                    fields.add(currentField.toString().trim());
                    currentField.setLength(0);
                } else {
                    currentField.append(c);
                }
            }
            fields.add(currentField.toString().trim());

            if (fields.size() != 8) return null;

            String id = fields.get(0);
            String first_name = fields.get(1);
            String last_name = fields.get(2);
            String email = fields.get(3);
            String gender = fields.get(4);
            String country = fields.get(5);
            String domain = fields.get(6);
            String birth_date = fields.get(7);

            return new Person(id, first_name, last_name, email, gender, country, domain, birth_date);
        } catch (Exception e) {
            System.out.println("Failed to chop data: " + e.getMessage());
            return null;
        }
    }
}
