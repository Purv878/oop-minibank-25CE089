# MiniBank — Java OOP Capstone Project

## 1. Project Overview

MiniBank is a console-based banking application developed using Java. It demonstrates Object-Oriented Programming concepts, exception handling, multithreading, serialization, collections, generic repositories, transaction logging, and JUnit testing.

The application allows users to create accounts, deposit and withdraw money, transfer funds, view account statements, list accounts, generate end-of-day reports, and process batch deposits.

## 2. Features

* Create new savings accounts
* Validate account details and opening balance
* Deposit money into an account
* Withdraw money according to account rules
* Transfer money between accounts
* Generate account statements
* Display all registered accounts
* Save and load account data using serialization
* Record transactions in log files
* Process multiple deposits using multithreading
* Generate an end-of-day report
* Test account operations using JUnit 6

## 3. Technologies Used

* **Programming Language:** Java
* **Java Version:** Java 26.0.1
* **IDE:** Visual Studio Code
* **Testing Framework:** JUnit 6
* **Version Control:** Git and GitHub
* **Data Persistence:** Java Serialization
* **Concurrency:** Java Threads and ExecutorService

## 4. Project Structure

```text
MiniBank/
├── exception/
├── model/
│   └── annotation/
├── service/
├── util/
├── tests/
│   └── BankServiceTest.java
├── logs/
├── MiniBank.java
├── BankResource.java
├── MiniBank-Final.jar
├── junit-platform-console-standalone-6.1.3.jar
└── README.md
```

The project also contains additional Java classes developed during earlier practicals.

## 5. How to Compile

Open PowerShell in the project directory and run:

```powershell
javac model/*.java model/annotation/*.java exception/*.java util/*.java service/*.java BankResource.java MiniBank.java
```

## 6. How to Run

Run the application using:

```powershell
java MiniBank
```

Alternatively, run the executable JAR:

```powershell
java -jar MiniBank-Final.jar
```

## 7. Application Menu

The application provides the following menu options:

1. Open Account
2. Deposit
3. Withdraw
4. Transfer
5. Account Statement
6. List Accounts
7. Generate End-of-Day Report
8. Batch Deposit Demo
9. Save and Exit

Choose an option and follow the prompts displayed in the console.

## 8. Data Persistence

The application uses Java Serialization to save account information to `accounts.dat`.

* Existing accounts are loaded when the application starts.
* Account information is saved when the user exits through the menu.
* Account data persists between application runs.

The generated `accounts.dat` file is runtime data.

## 9. Transaction Logging and Reports

Transactions are recorded in log files under the `logs/` directory.

The End-of-Day Report feature generates:

`end-of-day-report.txt`

The report contains information about available log files and their contents.

## 10. Multithreading

The application uses `TransactionProcessor` and `ExecutorService` to execute batch deposits.

The `Account` class uses synchronized methods for balance-changing operations, helping prevent concurrent deposits from incorrectly updating the account balance.

## 11. JUnit Testing

JUnit 6 is used to test important account operations.

**Test file:** `tests/BankServiceTest.java`

The following test cases are included:

1. **Deposit Test:** Verifies that depositing money increases the account balance correctly.
2. **Withdrawal Test:** Verifies that withdrawing an amount that violates the minimum balance throws an exception.
3. **Concurrent Deposit Test:** Verifies that concurrent deposits maintain the correct final balance.

### Compile the tests

Run this command from the project root:

```powershell
javac -cp ".;junit-platform-console-standalone-6.1.3.jar" -d test-classes model/*.java model/annotation/*.java exception/*.java util/*.java service/*.java tests/BankServiceTest.java
```

### Run the tests

```powershell
java -jar junit-platform-console-standalone-6.1.3.jar execute --class-path "test-classes" --select-class tests.BankServiceTest
```

**Test result:** 3 tests successful, 0 tests failed.

## 12. Learning Outcomes

Through this project, the following concepts were practiced:

* Classes, objects, encapsulation, inheritance, and polymorphism
* Interfaces, abstract classes, and method overriding
* Exception handling and custom exceptions
* Java annotations and reflection
* Collections and generic programming
* Serialization and file handling
* Multithreading and concurrent operations
* Transaction logging and report generation
* Unit testing with JUnit
* Git, GitHub, and executable JAR creation

## 13. Conclusion

The MiniBank project combines concepts learned throughout the Java practicals into a console-based banking application. It demonstrates account management, transaction processing, data persistence, concurrent operations, logging, reporting, and automated testing.

The project provides practical experience in developing a modular Java application using object-oriented programming principles.

---

**Project:** MiniBank
**Course:** Object-Oriented Programming with Java
**Practical:** 14 — Capstone Project
