-- Exercise 2: Error Handling

-- Scenario 1: Handle exceptions during fund transfers between accounts.
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_SourceAccountID IN NUMBER,
    p_DestAccountID   IN NUMBER,
    p_Amount          IN NUMBER
) IS
    v_SourceBalance NUMBER;
    v_cnt NUMBER;
    insufficient_funds EXCEPTION;
    invalid_account EXCEPTION;
BEGIN
    -- Check if source account exists
    SELECT COUNT(*) INTO v_cnt FROM Accounts WHERE AccountID = p_SourceAccountID;
    IF v_cnt = 0 THEN
        RAISE invalid_account;
    END IF;
    
    -- Check if destination account exists
    SELECT COUNT(*) INTO v_cnt FROM Accounts WHERE AccountID = p_DestAccountID;
    IF v_cnt = 0 THEN
        RAISE invalid_account;
    END IF;

    -- Get current balance of source account with lock
    SELECT Balance INTO v_SourceBalance FROM Accounts WHERE AccountID = p_SourceAccountID FOR UPDATE;
    
    -- Validate sufficient funds
    IF v_SourceBalance < p_Amount THEN
        RAISE insufficient_funds;
    END IF;

    -- Perform transfer
    UPDATE Accounts
    SET Balance = Balance - p_Amount
    WHERE AccountID = p_SourceAccountID;

    UPDATE Accounts
    SET Balance = Balance + p_Amount
    WHERE AccountID = p_DestAccountID;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success: Transferred ' || p_Amount || ' from Account ID ' || p_SourceAccountID || ' to Account ID ' || p_DestAccountID || '.');

EXCEPTION
    WHEN insufficient_funds THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Transfer failed. Insufficient funds in Account ID ' || p_SourceAccountID || '.');
    WHEN invalid_account THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Transfer failed. One or both Account IDs do not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Transfer failed due to an unexpected error: ' || SQLERRM);
END SafeTransferFunds;
/

-- Scenario 2: Manage errors when updating employee salaries.
CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_EmployeeID IN NUMBER,
    p_Percentage IN NUMBER
) IS
    v_cnt NUMBER;
BEGIN
    -- Check if employee exists
    SELECT COUNT(*) INTO v_cnt FROM Employees WHERE EmployeeID = p_EmployeeID;
    IF v_cnt = 0 THEN
        RAISE NO_DATA_FOUND;
    END IF;

    -- Update salary
    UPDATE Employees
    SET Salary = Salary * (1 + p_Percentage / 100)
    WHERE EmployeeID = p_EmployeeID;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success: Salary for Employee ID ' || p_EmployeeID || ' increased by ' || p_Percentage || '%.');

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Error: Employee ID ' || p_EmployeeID || ' does not exist.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Failed to update salary: ' || SQLERRM);
END UpdateSalary;
/

-- Scenario 3: Ensure data integrity when adding a new customer.
CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_CustomerID   IN NUMBER,
    p_Name         IN VARCHAR2,
    p_DOB          IN DATE,
    p_Balance      IN NUMBER
) IS
BEGIN
    -- Insert new customer record
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
    VALUES (p_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE, 'FALSE');
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success: Added customer with ID ' || p_CustomerID || '.');

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        DBMS_OUTPUT.PUT_LINE('Error: A customer with ID ' || p_CustomerID || ' already exists. Insertion prevented.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Failed to add new customer: ' || SQLERRM);
END AddNewCustomer;
/
