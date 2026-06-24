-- Exercise 6: Cursors

-- Scenario 1: Generate monthly statements for all customers using an explicit cursor.
DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT t.TransactionID, t.AccountID, t.TransactionDate, t.Amount, t.TransactionType, c.Name, c.CustomerID
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE);
          
    r_statement GenerateMonthlyStatements%ROWTYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Generate Monthly Statements ---');
    OPEN GenerateMonthlyStatements;
    LOOP
        FETCH GenerateMonthlyStatements INTO r_statement;
        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Statement for ' || r_statement.Name || ' (Cust ID: ' || r_statement.CustomerID || '): ' ||
                             'Account ID: ' || r_statement.AccountID || ' | ' ||
                             'Txn ID: ' || r_statement.TransactionID || ' | ' ||
                             'Date: ' || TO_CHAR(r_statement.TransactionDate, 'YYYY-MM-DD') || ' | ' ||
                             'Type: ' || r_statement.TransactionType || ' | ' ||
                             'Amount: $' || r_statement.Amount);
    END LOOP;
    CLOSE GenerateMonthlyStatements;
END;
/

-- Scenario 2: Apply annual fee to all accounts using an explicit cursor.
DECLARE
    v_AnnualFee CONSTANT NUMBER := 50.00;
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance FROM Accounts FOR UPDATE;
    r_acc ApplyAnnualFee%ROWTYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Apply Annual Maintenance Fee ---');
    OPEN ApplyAnnualFee;
    LOOP
        FETCH ApplyAnnualFee INTO r_acc;
        EXIT WHEN ApplyAnnualFee%NOTFOUND;
        
        -- Deduct maintenance fee
        UPDATE Accounts
        SET Balance = Balance - v_AnnualFee,
            LastModified = SYSDATE
        WHERE CURRENT OF ApplyAnnualFee;
        
        DBMS_OUTPUT.PUT_LINE('Account ID ' || r_acc.AccountID || ': Deducted $' || v_AnnualFee || 
                             '. Old Balance: $' || r_acc.Balance || 
                             ', New Balance: $' || (r_acc.Balance - v_AnnualFee));
    END LOOP;
    CLOSE ApplyAnnualFee;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Failed to apply annual fee: ' || SQLERRM);
END;
/

-- Scenario 3: Update the interest rate for all loans based on a new policy using an explicit cursor.
DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate FROM Loans FOR UPDATE;
    r_loan UpdateLoanInterestRates%ROWTYPE;
    v_NewRate NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Update Loan Interest Rates ---');
    OPEN UpdateLoanInterestRates;
    LOOP
        FETCH UpdateLoanInterestRates INTO r_loan;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;
        
        -- New Policy: Decrease interest rate by 0.5% for all active loans
        v_NewRate := r_loan.InterestRate - 0.5;
        
        UPDATE Loans
        SET InterestRate = v_NewRate
        WHERE CURRENT OF UpdateLoanInterestRates;
        
        DBMS_OUTPUT.PUT_LINE('Loan ID ' || r_loan.LoanID || ': Interest rate updated from ' || 
                             r_loan.InterestRate || '% to ' || v_NewRate || '%.');
    END LOOP;
    CLOSE UpdateLoanInterestRates;
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Failed to update loan interest rates: ' || SQLERRM);
END;
/
