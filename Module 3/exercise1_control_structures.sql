-- Exercise 1: Control Structures

-- Scenario 1: Apply a 1% discount to loan interest rates for customers above 60 years old.
DECLARE
    CURSOR c_customers IS
        SELECT CustomerID, DOB FROM Customers;
    v_age NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 1: Loan Interest Discount for Senior Customers ---');
    FOR r_cust IN c_customers LOOP
        -- Calculate age in years
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, r_cust.DOB) / 12);
        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = r_cust.CustomerID;
            DBMS_OUTPUT.PUT_LINE('Discount applied for Customer ID: ' || r_cust.CustomerID || ' (Age: ' || v_age || ').');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Promote a customer to VIP status based on their balance.
DECLARE
    CURSOR c_customers IS
        SELECT CustomerID, Balance FROM Customers FOR UPDATE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 2: Promote to VIP Status ---');
    FOR r_cust IN c_customers LOOP
        IF r_cust.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CURRENT OF c_customers;
            DBMS_OUTPUT.PUT_LINE('Customer ID: ' || r_cust.CustomerID || ' promoted to VIP (Balance: ' || r_cust.Balance || ').');
        ELSE
            UPDATE Customers
            SET IsVIP = 'FALSE'
            WHERE CURRENT OF c_customers;
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Send reminders to customers whose loans are due within the next 30 days.
DECLARE
    CURSOR c_due_loans IS
        SELECT l.LoanID, c.Name, l.EndDate
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 3: Loan Due Reminders ---');
    FOR r_loan IN c_due_loans LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Customer ' || r_loan.Name || 
                             ' (Loan ID: ' || r_loan.LoanID || 
                             ') has a loan due on ' || TO_CHAR(r_loan.EndDate, 'YYYY-MM-DD') || '.');
    END LOOP;
END;
/
