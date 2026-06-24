-- Exercise 7: Packages

-- Scenario 1: Group all customer-related procedures and functions into a package CustomerManagement.
CREATE OR REPLACE PACKAGE CustomerManagement IS
    PROCEDURE AddNewCustomer(
        p_CustomerID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_DOB        IN DATE,
        p_Balance    IN NUMBER
    );
    
    PROCEDURE UpdateCustomerDetails(
        p_CustomerID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_DOB        IN DATE
    );
    
    FUNCTION GetCustomerBalance(
        p_CustomerID IN NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement IS
    PROCEDURE AddNewCustomer(
        p_CustomerID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_DOB        IN DATE,
        p_Balance    IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
        VALUES (p_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE, 'FALSE');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer ' || p_Name || ' added successfully.');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID ' || p_CustomerID || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END AddNewCustomer;

    PROCEDURE UpdateCustomerDetails(
        p_CustomerID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_DOB        IN DATE
    ) IS
    BEGIN
        UPDATE Customers
        SET Name = p_Name,
            DOB = p_DOB,
            LastModified = SYSDATE
        WHERE CustomerID = p_CustomerID;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer ID ' || p_CustomerID || ' updated successfully.');
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance(
        p_CustomerID IN NUMBER
    ) RETURN NUMBER IS
        v_Balance NUMBER;
    BEGIN
        SELECT Balance INTO v_Balance FROM Customers WHERE CustomerID = p_CustomerID;
        RETURN v_Balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END GetCustomerBalance;
END CustomerManagement;
/

-- Scenario 2: Create a package to manage employee data EmployeeManagement.
CREATE OR REPLACE PACKAGE EmployeeManagement IS
    PROCEDURE HireEmployee(
        p_EmployeeID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_Position   IN VARCHAR2,
        p_Salary     IN NUMBER,
        p_Department IN VARCHAR2,
        p_HireDate   IN DATE
    );
    
    PROCEDURE UpdateEmployeeDetails(
        p_EmployeeID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_Position   IN VARCHAR2,
        p_Salary     IN NUMBER,
        p_Department IN VARCHAR2
    );
    
    FUNCTION CalculateAnnualSalary(
        p_EmployeeID IN NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement IS
    PROCEDURE HireEmployee(
        p_EmployeeID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_Position   IN VARCHAR2,
        p_Salary     IN NUMBER,
        p_Department IN VARCHAR2,
        p_HireDate   IN DATE
    ) IS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_EmployeeID, p_Name, p_Position, p_Salary, p_Department, p_HireDate);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee ' || p_Name || ' hired successfully.');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_EmployeeID || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END HireEmployee;

    PROCEDURE UpdateEmployeeDetails(
        p_EmployeeID IN NUMBER,
        p_Name       IN VARCHAR2,
        p_Position   IN VARCHAR2,
        p_Salary     IN NUMBER,
        p_Department IN VARCHAR2
    ) IS
    BEGIN
        UPDATE Employees
        SET Name = p_Name,
            Position = p_Position,
            Salary = p_Salary,
            Department = p_Department
        WHERE EmployeeID = p_EmployeeID;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee ID ' || p_EmployeeID || ' details updated.');
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END UpdateEmployeeDetails;

    FUNCTION CalculateAnnualSalary(
        p_EmployeeID IN NUMBER
    ) RETURN NUMBER IS
        v_Salary NUMBER;
    BEGIN
        SELECT Salary INTO v_Salary FROM Employees WHERE EmployeeID = p_EmployeeID;
        RETURN v_Salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END CalculateAnnualSalary;
END EmployeeManagement;
/

-- Scenario 3: Group all account-related operations into a package AccountOperations.
CREATE OR REPLACE PACKAGE AccountOperations IS
    PROCEDURE OpenAccount(
        p_AccountID   IN NUMBER,
        p_CustomerID  IN NUMBER,
        p_AccountType IN VARCHAR2,
        p_Balance     IN NUMBER
    );
    
    PROCEDURE CloseAccount(
        p_AccountID IN NUMBER
    );
    
    FUNCTION GetTotalBalance(
        p_CustomerID IN NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations IS
    PROCEDURE OpenAccount(
        p_AccountID   IN NUMBER,
        p_CustomerID  IN NUMBER,
        p_AccountType IN VARCHAR2,
        p_Balance     IN NUMBER
    ) IS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_AccountID, p_CustomerID, p_AccountType, p_Balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account ID ' || p_AccountID || ' opened successfully.');
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Account ID ' || p_AccountID || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END OpenAccount;

    PROCEDURE CloseAccount(
        p_AccountID IN NUMBER
    ) IS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_AccountID;
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account ID ' || p_AccountID || ' closed successfully.');
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END CloseAccount;

    FUNCTION GetTotalBalance(
        p_CustomerID IN NUMBER
    ) RETURN NUMBER IS
        v_TotalBalance NUMBER;
    BEGIN
        SELECT SUM(Balance) INTO v_TotalBalance FROM Accounts WHERE CustomerID = p_CustomerID;
        RETURN NVL(v_TotalBalance, 0);
    END GetTotalBalance;
END AccountOperations;
/
