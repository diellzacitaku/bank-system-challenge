# Bank System Challenge
A Java console application for a bank system where you can manage banks and accounts.
Each account is associated with a specific bank, and you can perform different transactions such as withdrawals, deposits, and transfers both within the same bank and with different banks.


### Features
- Account Management
  - Register a new account
  - Login to an existing account
- Bank Management
  - Register a new bank
- Transactions
  - Withdraw funds
  - Deposit funds
  - Transfer funds within the same bank
  - Transfer funds to another bank

### Installation 
Clone the repository
```sh
git clone https://github.com/diellzacitaku/bank-system-challenge.git
```
Then open it using an IDE and execute it

### Usage

#### Starting menu
After launching the application, you will be asked to choose whether to start with predefined data:
```
Add existing data (yes/no)?: <reponse>
```

#### Main menu
Once the setup is complete, you will see the main menu:
```
Please choose an option:
(1) Login account
(2) Register account
(3) Create bank
(4) View all banks
(5) Check bank total transaction fee amount
(6) Check bank total transaction amount
Your choice: <response from 1 - 6>
```


#### Banking Menu
If you choose to log in by selecting `(1) Login account` and successfully authenticate, you will be directed to the following menu:
```
<YOUR NAME>, please choose an option:
(1) Withdraw
(2) Deposit
(3) Transfer inside the bank
(4) Transfer to another bank
(5) Check account balance
(6) Check transaction history
(7) Check account information
(8) Log out
Your choice: <response from 1 - 6>
```