-- Exercise 3: Stored Procedures

-- Scenario 1: Process monthly interest for all savings accounts.
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    -- Update all accounts of type 'Savings' by applying 1% interest
    UPDATE Accounts
    SET Balance = Balance * 1.01,
        LastModified = SYSDATE
    WHERE AccountType = 'Savings';

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success: Monthly interest of 1% applied to all Savings accounts.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Failed to process monthly interest: ' || SQLERRM);
END ProcessMonthlyInterest;
/

-- Scenario 2: Implement a bonus scheme for employees based on department.
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_Department      IN VARCHAR2,
    p_BonusPercentage IN NUMBER
) IS
BEGIN
    -- Update salary by adding the bonus percentage
    UPDATE Employees
    SET Salary = Salary * (1 + p_BonusPercentage / 100)
    WHERE Department = p_Department;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success: Bonus of ' || p_BonusPercentage || '% applied to employees in department: ' || p_Department || '.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Failed to update employee bonus: ' || SQLERRM);
END UpdateEmployeeBonus;
/

-- Scenario 3: Transfer funds between accounts with balance checking.
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_SourceAccountID IN NUMBER,
    p_DestAccountID   IN NUMBER,
    p_Amount          IN NUMBER
) IS
    v_SourceBalance NUMBER;
    v_cnt NUMBER;
BEGIN
    -- Verify source account existence
    SELECT COUNT(*) INTO v_cnt FROM Accounts WHERE AccountID = p_SourceAccountID;
    IF v_cnt = 0 THEN
        RAISE_APPLICATION_ERROR(-20006, 'Source Account ID ' || p_SourceAccountID || ' does not exist.');
    END IF;

    -- Verify destination account existence
    SELECT COUNT(*) INTO v_cnt FROM Accounts WHERE AccountID = p_DestAccountID;
    IF v_cnt = 0 THEN
        RAISE_APPLICATION_ERROR(-20007, 'Destination Account ID ' || p_DestAccountID || ' does not exist.');
    END IF;

    -- Lock and retrieve source balance
    SELECT Balance INTO v_SourceBalance FROM Accounts WHERE AccountID = p_SourceAccountID FOR UPDATE;

    -- Verify sufficient balance
    IF v_SourceBalance < p_Amount THEN
        RAISE_APPLICATION_ERROR(-20008, 'Transfer aborted: Insufficient balance in Source Account ID ' || p_SourceAccountID || '.');
    END IF;

    -- Debit source account
    UPDATE Accounts
    SET Balance = Balance - p_Amount,
        LastModified = SYSDATE
    WHERE AccountID = p_SourceAccountID;

    -- Credit destination account
    UPDATE Accounts
    SET Balance = Balance + p_Amount,
        LastModified = SYSDATE
    WHERE AccountID = p_DestAccountID;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Success: Transferred ' || p_Amount || ' from Account ID ' || p_SourceAccountID || ' to Account ID ' || p_DestAccountID || '.');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END TransferFunds;
/
