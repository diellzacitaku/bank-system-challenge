# Bank System Challenge

A Java console application for a bank system where you can manage banks and accounts.
Each account is associated with a specific bank, and you can perform different transactions such as withdrawals,
deposits, and transfers both within the same bank and with different banks.

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

What every choice does:

`(1) Login Account` allows the user to login into the banking menu using their credentials:

```
--- Login to your account ---
Please enter your bank name: <bank in which the account is registered in>
Please enter your email: <account email>
Please enter your password: <account password>
```

`(2) Register account` allows the user to register an account:

```
--- Register an account ---
Please enter your bank name: <bank in which this account will be registered>
Please enter your name: <name of the person which owns the account>
Please enter your email: <account email>
Please enter your password: <account password>
```

`(3) Create bank` allows the user to register a bank:

```
--- Create a bank ---
Please enter your bank name: <name of the bank>
```

`(4) View all banks` shows a list of all the available banks:

```
--- View all banks ---
Bank name='BKT', transaction flat fee amount=10.0$, transaction percent fee value=5.0%
Bank name='NLB', transaction flat fee amount=10.0$, transaction percent fee value=5.0%
```

`(5) Check bank total transaction fee amount` the total amount of fees collected during transactions:

```
--- View bank total transaction fee ---
Please enter your bank name: NLB
Total transaction fee amount: 15.0
```

`(6) Check bank total transaction amount` the total amount of funds withdrawn, deposited or transferred:

```
--- View bank total transfer amount ---
Please enter your bank name: NLB
Total transfer amount: 500.0
```

#### Banking Menu

If you choose to log in by selecting `(1) Login account` and successfully authenticate, you will be directed to the
following menu:

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

What every choice does:

`(1) Withdraw` withdraw funds from the account, if balance is insufficient - no funds are withdrawn:

```
--- Withdraw funds ---
Please enter the amount: <amount in float>
Please enter your preferred fee type (flat or percentage): <flat or percentage>
```

`(2) Deposit` deposit funds to the account, fee is subtracted from the balance:

```
Please enter the amount: <float>
Please enter your preferred fee type (flat or percentage): <flat or percentage>
```

`(3) Transfer inside the bank` transfer funds to another account in the same bank, fee is subtracted from both accounts:

```
--- Transfer funds internally ---
Please enter the ID of the receiving account: <ID of the account that will recieve the funds>
What is the reason for the transaction?: <text>
Please enter the amount: <float>
```

`(4) Transfer to another bank` transfer funds to another account in a different bank, fee is subtracted from both
accounts based on the respective bank:

```
--- Transfer funds externally ---
Please enter the ID of the receiving account: <ID which can be found in option 7>
Please enter the name of the receiving bank: <string>
What is the reason for the transaction?: <string>
Please enter the amount: <float>
```

`(5) Check account balance` the current balance of the account:

```
--- Account balance ---
Your current balance: 1150.0
```

`(6) Check transaction history` a list of all the transactions that have happened to this account:

```
--- Transaction history ---
timestamp=Sun May 19 21:53:50 CEST 2024, transaction type=DEPOSIT, originating account id='null', receiving account id='8697c887-10b8-446e-9122-600dfd47a9ef', amount=1000.0, fee=10.0, reason='ATM'
timestamp=Sun May 19 21:53:50 CEST 2024, transaction type=WITHDRAWAL, originating account id='8697c887-10b8-446e-9122-600dfd47a9ef', receiving account id='null', amount=400.0, fee=19.999998, reason='Debt'
timestamp=Sun May 19 21:53:50 CEST 2024, transaction type=WITHDRAWAL, originating account id='8697c887-10b8-446e-9122-600dfd47a9ef', receiving account id='null', amount=400.0, fee=10.0, reason='Friend'
```

`(7) Check account information` all the necessary information for this account, the account Id which can be used during
transfers:

```
--- Account Information ---
Your account Id: 8697c887-10b8-446e-9122-600dfd47a9ef
Your account name: John Smith
Your account email: john.smith@gmail.com
```

`(8) Log out` returns the user to the main menu.

```
Successfully logged out!
```