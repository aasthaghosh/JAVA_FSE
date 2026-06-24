-- Exercise 5: Triggers

-- Scenario 1: Automatically update the last modified date when a customer's record is updated.
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

-- Scenario 2: Maintain an audit log for all transactions.
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (LogID, TransactionID, AccountID, TransactionDate, Amount, TransactionType, LogDate)
    VALUES (AuditLog_Seq.NEXTVAL, :NEW.TransactionID, :NEW.AccountID, :NEW.TransactionDate, :NEW.Amount, :NEW.TransactionType, SYSDATE);
END;
/

-- Scenario 3: Enforce business rules on deposits and withdrawals.
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_Balance NUMBER;
BEGIN
    -- 1. Ensure deposit amounts are positive
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20005, 'Transaction aborted: Deposit amount must be positive.');
    END IF;

    -- 2. Ensure withdrawal amounts are positive and do not exceed account balance
    IF :NEW.TransactionType = 'Withdrawal' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20005, 'Transaction aborted: Withdrawal amount must be positive.');
        END IF;
        
        -- Retrieve the current account balance
        SELECT Balance INTO v_Balance 
        FROM Accounts 
        WHERE AccountID = :NEW.AccountID;

        IF :NEW.Amount > v_Balance THEN
            RAISE_APPLICATION_ERROR(-20004, 'Transaction aborted: Withdrawal amount exceeds current account balance.');
        END IF;
    END IF;
END;
/
