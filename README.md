# Hybrid Test Automation Framework

This is a **Hybrid Test Automation Framework** built using various tools and technologies, aimed at automating both **UI** and **API** tests. The framework integrates multiple services and utilities to provide a comprehensive solution for end-to-end testing.

## Architecture Overview

The framework is structured in different layers to ensure modularity, scalability, and maintainability. Below is a breakdown of each layer and its components:

### 1. **Test Execution Layer**
- **TestNG**: Manages and executes the tests, including both UI and API tests.
- **Selenium WebDriver**: Automates the browser and triggers UI tests.
- **REST Assured**: Automates API testing.

### 2. **Supporting Services Layer**
- **Log4j2**: Provides logging functionality for better traceability and debugging.
- **ExtentReports**: Generates detailed HTML reports of the test execution results.
- **Apache Commons IO**: Helps in handling files, including screenshots, during the test execution.

### 3. **CI/CD Integration Layer**
- **Jenkins**: Triggers and manages test execution, enabling continuous integration and delivery.

### 4. **Utilities Layer**
- **Data Provider Utility**: Handles test data, including data reading from files or databases for test execution.

### 5. **Configuration Layer**
- **Configuration Reader**: Reads configuration files for customizable test settings, such as environment-specific configurations, URLs, etc.

### 6. **WebDriver Factory Layer**
- **WebDriver Factory**: Creates WebDriver instances, ensuring consistent browser instances across tests.

### 7. **Page Object Layer**
- **Login Page**: Handles actions and elements related to the login page.
- **Products Page**: Handles actions and elements related to the products page.
- **Cart Page**: Handles actions and elements related to the shopping cart page.

## Framework Flow

The framework follows a well-defined flow to trigger tests and generate results:

1. **Test Execution**:
    - **TestNG** triggers both **Selenium WebDriver** for UI tests and **REST Assured** for API tests.
    - **TestNG** manages test execution and integrates with **Log4j2** for logging and **ExtentReports** for generating detailed reports.
    - **TestNG** also interacts with **Apache Commons IO** for handling files like screenshots and test data.

2. **UI Test Execution**:
    - **Selenium WebDriver** interacts with various **Page Objects** (Login, Products, Cart) to automate browser actions.
    - The **WebDriver Factory** ensures proper browser instances are created for each test.

3. **API Test Execution**:
    - **REST Assured** handles all API test interactions.

4. **Configuration**:
    - **TestNG** reads configurations from the **Configuration Reader** to set up environment-specific values and settings.

5. **CI/CD**:
    - **Jenkins** triggers the test execution and continuously integrates the results into the CI pipeline.

## Getting Started

To use the framework, follow these steps:

### Prerequisites

1. **Java** (version 8 or higher)
2. **Maven** (for dependency management)
3. **TestNG** (for running tests)
4. **Selenium WebDriver** (for browser automation)
5. **REST Assured** (for API automation)
6. **Jenkins** (for continuous integration)

### Installation

1. Clone this repository to your local machine:

    ```bash
    git clone https://github.com/rakesh-vardan/Learn_TestAutomationFramework.git
    ```

2. Navigate to the project directory:

    ```bash
    cd Learn_TestAutomationFramework
    ```

3. Install the required dependencies using Maven:

    ```bash
    mvn clean install
    ```

### Running the Tests

To run the tests use the respective TestNG.xml file, execute the following Maven command:

```bash
mvn clean test -DsuiteXMLFile=testng.xml
