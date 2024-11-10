
--Account From 2023
-- balance 2023
-- open 350
-- total debit 1200
--total credit 550
-- closing 1000
INSERT IGNORE INTO account_balance (account_no, `year`, opening_bal, closing_bal, debit_amt, credit_amt, created_at, updated_at) 
VALUES('2023873716', 2023, 35000, 100000, 120000, 55000, '2023-06-12 00:00:00.000', '2023-12-31 23:59.00.000');

--debit 1000
INSERT IGNORE INTO `transaction` (id, account_no, amount, trx_type, receipt_no, reference, transaction_date, createdAt, updated_at) 
VALUES(1, '2023873716', 100000, 'DEBIT', '01-2023-187', 'Monthly Savings', '2023-07-29', '2023-07-29 00:00:00.000', '2023-07-29 00:00:00.000');

INSERT IGNORE INTO audit_trail (id, account_no, transaction_id, curr_opening_bal, new_opening_bal, curr_closing_bal, new_closing_bal, curr_debit_amt, new_debit_amt, curr_credit_amt, new_credit_amt, created_at, updated_at) 
VALUES(1, '2023873716', 1, 35000, 35000, 0, 0, 0, 100000, 0, 0, '2023-07-29 00:00:00.000', '2023-07-29 00:00:00.000');

-- debit 200
INSERT IGNORE INTO `transaction` (id, account_no, amount, trx_type, receipt_no, reference, transaction_date, createdAt, updated_at) 
VALUES(2, '2023873716', 20000, 'DEBIT', '12345', 'Grab Income', '2023-08-05', '2023-08-05 00:00:00.000', '2023-08-05 00:00:00.000');

INSERT IGNORE INTO audit_trail (id, account_no, transaction_id, curr_opening_bal, new_opening_bal, curr_closing_bal, new_closing_bal, curr_debit_amt, new_debit_amt, curr_credit_amt, new_credit_amt, created_at, updated_at) 
VALUES(2, '2023873716', 2, 35000, 35000, 0, 0, 100000, 120000, 0, 0, '2023-08-05 00:00:00.000', '2023-08-05 00:00:00.000');

--credit 400
INSERT IGNORE INTO `transaction` (id, account_no, amount, trx_type, receipt_no, reference, transaction_date, createdAt, updated_at) 
VALUES(3, '2023873716', 40000, 'CREDIT', null, 'Car Payment', '2023-08-07', '2023-08-07 00:00:00.000', '2023-08-07 00:00:00.000');

INSERT IGNORE INTO audit_trail (id, account_no, transaction_id, curr_opening_bal, new_opening_bal, curr_closing_bal, new_closing_bal, curr_debit_amt, new_debit_amt, curr_credit_amt, new_credit_amt, created_at, updated_at) 
VALUES(3, '2023873716', 1, 35000, 35000, 0, 0, 100000, 120000, 0, 40000, '2023-08-07 00:00:00.000', '2023-08-07 00:00:00.000');

 -- credit 150
INSERT IGNORE INTO `transaction` (id, account_no, amount, trx_type, receipt_no, reference, transaction_date, createdAt, updated_at) 
VALUES(4, '2023873716', 15000, 'CREDIT', null, 'Phone bill', '2023-08-015', '2023-08-15 00:00:00.000', '2023-08-15 00:00:00.000');

INSERT IGNORE INTO audit_trail (id, account_no, transaction_id, curr_opening_bal, new_opening_bal, curr_closing_bal, new_closing_bal, curr_debit_amt, new_debit_amt, curr_credit_amt, new_credit_amt, created_at, updated_at) 
VALUES(4, '2023873716', 4, 35000, 35000, 0, 0, 100000, 120000, 40000, 55000, '2023-08-15 00:00:00.000', '2023-08-15 00:00:00.000');

-- end year closing
INSERT IGNORE INTO audit_trail (id, account_no, transaction_id, curr_opening_bal, new_opening_bal, curr_closing_bal, new_closing_bal, curr_debit_amt, new_debit_amt, curr_credit_amt, new_credit_amt, created_at, updated_at) 
VALUES(5, '2023873716', null, 35000, 35000, 0, 100000, 120000, 120000, 55000, 55000, '2023-12-31 00:00:00.000', '2023-12-31 00:00:00.000');

INSERT IGNORE INTO audit_trail (id, account_no, transaction_id, curr_opening_bal, new_opening_bal, curr_closing_bal, new_closing_bal, curr_debit_amt, new_debit_amt, curr_credit_amt, new_credit_amt, created_at, updated_at) 
VALUES(6, '2023873716', null, 0, 100000, 0, 0, 0, 0, 0, 0, '2024-01-01 00:00:00.000', '2024-01-01 00:00:00.000');

INSERT IGNORE INTO account_balance (account_no, `year`, opening_bal, closing_bal, debit_amt, credit_amt, created_at, updated_at) 
VALUES('2023873716', 2024, 100000, 0, 0, 0, '2024-01-01 00:00:00.000', '2024-01-01 00:00:00.000');
--

-- New Account 2024 (no transactions)
INSERT IGNORE INTO account_balance (account_no, `year`, opening_bal, closing_bal, debit_amt, credit_amt, created_at, updated_at) 
VALUES('2024215993', 2024, 0, 0, 0, 0, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());




