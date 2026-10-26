# MiniBank – Java Banking System

## Overview

MiniBank is a console-based banking application developed using Java and Object-Oriented Programming concepts.

## Features

* Open a new bank account
* Deposit and withdraw money
* Transfer money between accounts
* View account statements
* List all bank accounts
* Save and load accounts using serialization
* Process batch deposits using multithreading
* Generate an end-of-day report
* Log banking transactions
* Validate account details and transaction amounts

## Technologies Used

* Java
* Object-Oriented Programming
* Collections Framework
* Exception Handling
* Multithreading
* Serialization
* File Handling
* Reflection and Custom Annotations

## How to Compile

Open PowerShell in the project directory and run:

```powershell
javac model/*.java model/annotation/*.java exception/*.java util/*.java service/*.java BankResource.java MiniBank.java
```

## How to Run

```powershell
java MiniBank
```

## Create Runnable JAR

```powershell
jar cfe MiniBank-Final.jar MiniBank *.class model/*.class model/annotation/*.class exception/*.class util/*.class service/*.class
```

## Run JAR

```powershell
java -jar MiniBank-Final.jar
```

## Data Persistence

Account information is saved in `accounts.dat` and loaded when the application starts.

## Author

Purv Patel

## Project Purpose

This project demonstrates practical Java programming concepts through a banking application.
