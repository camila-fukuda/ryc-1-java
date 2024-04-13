# RyC Banking Application JAVA

## APPLICATION IN PROGRESS

Please note that this application is still under development and is not yet finalized. Certain features may be
incomplete or subject to change.

## Main Class

The main class for running the Java application is located at the following path:
src/application/Main.java

To run the application, you can execute the Main class.

## Next Tasks

Please note that the following list represents tasks I've prioritized for now, but it's not definitive and may evolve
over time. (LAST UPDATE:  2024-04-13)

- [X] Improve the exception treatment in AccountServices when creating a Person Account;
- [ ] Create test classes;
- [ ] Finish all person account actions;
- [ ] Start the business account logic;

## Points to Improve
Areas for Enhancement

- Implement access restrictions to differentiate operations available to "employees" and those for customers exclusively.
- Organize the menu into distinct sections for improved navigation: (Personal Account, Business Account, Customer, Branch).
- Enhance document type validation to align with specific account types.
    
## Usage

To use the application, follow these steps:

1. Ensure you have Java installed on your system.
2. Navigate to the directory containing the Main.java file.
3. Compile the Java files if necessary.
4. Run the Main class using the `java` command.

## Contributors

- Camila Fukuda

## Banking Operations

This is a relation of banking operations that should be available. Some of them are still a work in progress.

### Account Operations

- For Accounts (person account or business account):
    [X] Create account;
    [X] List all accounts;
    [ ] Find account by code;
    [ ] Get limit;
    [ ] Set limit;
    [ ] Deposit an amount;
    [ ] Withdraw amount;
    [ ] Get balance;
    [ ] Transfer amount to another account;
- For Business Account
    [ ] Get loan limit;
    [ ] Pay loan;
    [ ] Get loan;
    [ ] Set loan limit;

### Customer Operations

[X] Create customer;
[X] List all customers;
[X] Find customer by name;
[X] Find by document;

### Branch Operations

[X] Create branch;
[X] List all branch;
[X] Find branch by code;

## Class Diagram

```mermaid
classDiagram
    Account <|-- AccountAbstract: implements
    AccountAbstract <|-- PersonAccount: Inheritance
    AccountAbstract <|-- BusinessAccount: Inheritance
    Account --* Transaction: Composition
    Branch --* Transaction: Composition
    Account --* Branch: Composition
    Account --* Customer: Composition
    class Transaction {
        -branch: Branch
        -type: String
        -value: Float
        -dateTime: Instant
        -account: Account
    }
    class Customer {
        -name: String
        -document: String
        +Customer(String, String)
    }
    class Branch {
        -city: String
        -state: String
        -code: String
        +Branch(String, String, String)
    }
    class Account {
        <<Interface>>
        -deposit()
        -withdraw()
        -transactionsHistory()
        -equals()
        -hasCode()
        -getLimit()
        -getBalance()
        -getType()
        -setLimit()
    }
    class AccountAbstract {
        <<Abstract>>
        -number: Float
        -branch: Branch
        -customer: Customer
        -balance: Float
        -limit: Float
        -type: String
    }
    class PersonAccount {
        -type: String
    }
    class BusinessAccount {
        -type: String
        -loanLimit
        +getLoanLimit()
        +payoff()
        +borrow()
    }
```
