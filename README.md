# Person Manager Application

## Overview

The **Person Manager Application** is a JavaFX-based desktop application designed for managing and filtering data records of individuals. It features an interactive table for displaying data, dynamic filtering by date range, and sorting functionality.

## Features

- **Dynamic Table View**: Displays data records with columns for attributes like ID, first name, last name, email, gender, country, domain, and birth date.
- **Data Import**: Loads data from multiple CSV files and combines it into a unified view.
- **Date Range Filtering**: Filter records based on a range of birth dates using a user-friendly date picker.
- **Alphabetical Sorting**: Supports ascending and descending sorting of records by first name.
- **Multithreaded Import**: Efficiently loads data using multithreading for responsive performance.

## Getting Started

### Prerequisites

- **Java 17 or newer**: Ensure you have JavaFX bundled or installed separately.
- **IntelliJ IDEA or Eclipse**: IDEs recommended for building and running the project.

## Usage

1. Launch the application.
2. Use the "From" and "To" date pickers to filter records by a range of birth dates.
3. Select a sorting option (ascending or descending) from the dropdown menu.
4. Click "Find" to display the filtered and sorted records in the table view.

## Architecture

The Person Manager Application consists of the following components:

- **Person**:
  - A data model representing an individual with attributes like ID, name, email, gender, and birth date.
  - Includes getter methods for accessing attributes.

- **Importer**:
  - Handles the loading of data from CSV files.
  - Processes data asynchronously using threads.
  - Cleans and parses raw data into Person objects.

- **HelloController**:
  - Manages the application's user interface and logic.
  - Implements filtering and sorting based on user inputs.
  - Handles the integration between the data model and the table view.

- **HelloApplication**:
  - Entry point of the application.
  - Initializes and displays the JavaFX UI.

## Contribution

Contributions are welcome! If you’d like to report a bug or request a feature, feel free to open an issue or submit a pull request.