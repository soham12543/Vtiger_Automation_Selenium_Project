# Vtiger Automation Selenium Project

## Project Overview
The **Vtiger Automation Selenium Project** is designed to automate the testing of Vtiger CRM using Selenium WebDriver. This project ensures the reliability and efficiency of the CRM system by performing automated test cases on various modules.

## Technologies Used
- **Programming Language:** Java
- **Testing Framework:** TestNG
- **Automation Tool:** Selenium WebDriver
- **Build Tool:** Maven
- **Version Control:** Git & GitHub
- **Reporting Tool:** Extent Reports

## Features
- Automated end-to-end testing of Vtiger CRM
- Cross-browser compatibility testing
- Data-driven testing using Excel
- Logging and reporting using Log4j & Extent Reports
- CI/CD integration (if applicable)

## Project Structure
```
Vtiger_Automation_Selenium_Project/
│-- src/main/java   # Application code (if needed)
│-- src/test/java   # Test scripts
│-- test-output/    # Test reports
│-- pom.xml         # Maven configuration
│-- README.md       # Project documentation
```

## Installation & Setup
### Prerequisites
- Install **Java (JDK 8 or later)**
- Install **Maven**
- Install **Git**

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/soham12543/Vtiger_Automation_Selenium_Project.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Vtiger_Automation_Selenium_Project
   ```
3. Install dependencies using Maven:
   ```bash
   mvn clean install
   ```
4. Run test cases:
   ```bash
   mvn test
   ```

## Branching Strategy
- **main**: Stable production-ready code
- **dev**: Active development branch
- **feature-branch**: Feature-specific development
- **bugfix-branch**: Fixes and improvements

## Contribution Guidelines
1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature-branch
   ```
3. Commit changes:
   ```bash
   git commit -m "Add new test case for login module"
   ```
4. Push changes:
   ```bash
   git push origin feature-branch
   ```
5. Open a pull request.

## Reporting Issues
If you find any bugs or have feature requests, please open an issue in the GitHub repository.

## License
This project is licensed under the MIT License.

---

### Author
Developed by **Soham**

### Contact
For any inquiries, reach out via GitHub Issues or email.

---

This README file provides essential information about the project setup, usage, and contributions.
