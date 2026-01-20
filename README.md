# ZetaPay Digital Banking Automation Framework

## Overview
This project is a comprehensive Test Automation Framework for **ZetaPay**, a next-gen digital banking platform. It includes:
- **API Automation**: Using REST Assured
- **UI Automation**: Using Selenium WebDriver
- **Database Validation**: Using JDBC
- **Performance Testing**: Using JMeter
- **CI/CD**: Integrated with GitHub Actions

## Tech Stack
- **Language**: Java 17
- **Build Tool**: Maven
- **Test Runner**: TestNG
- **Reporting**: Allure Framework
- **CI/CD**: GitHub Actions

## Directory Structure
- `src/test/java/com/zetapay/automation/api`: API Test Cases & Utils
- `src/test/java/com/zetapay/automation/ui`: Selenium Page Objects & Tests
- `src/test/java/com/zetapay/automation/db`: Database helpers
- `src/test/resources`: Configuration and Test Data

## How to Run
1. **Run All Tests**: `mvn test`
2. **Generate Report**: `mvn allure:serve`
