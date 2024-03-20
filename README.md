Here is a simple flow chart:

```mermaid
classDiagram
    Account <|-- AccountAbstract : implements
    AccountAbstract <|-- PersonAccount: Inheritance
    AccountAbstract <|-- BusinessAccount: Inheritance
    Account --* Transaction : Composition
    Branch --* Transaction : Composition
    Account --* Branch : Composition
    Account --* Customer : Composition
    class Transaction{
        -branch:Branch
        -type:String
        -value:Float
        -dateTime:Instant
        -account:Account
    }
    class Customer {
        -name:String
        -document:String
        +Customer(String,String)
    }
    class Branch{
        -city:String
        -state:String
        -code:String
        +Branch(String,String,String)
    }
    class Account{
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
    class AccountAbstract{
        <<Abstract>>
        -number:Float
        -branch:Branch
        -customer:Customer
        -balance:Float
        -limit:Float
        -type:String
    }
    class PersonAccount{
        -type:String
    }
    class BusinessAccount{
        -type:String
        -loanLimit
        +getLoanLimit()
        +payoff()
        +borrow()
    }
```
