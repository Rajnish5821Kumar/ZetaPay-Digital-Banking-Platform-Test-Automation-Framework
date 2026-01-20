-- Run this script in SQL Server Management Studio (SSMS)

-- 1. Create the Database
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'zetapay_test')
BEGIN
    CREATE DATABASE zetapay_test;
END
GO

USE zetapay_test;
GO

-- 2. Create the Users Table (Matches UserDTO)
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[users]') AND type in (N'U'))
BEGIN
    CREATE TABLE users (
        id INT IDENTITY(1,1) PRIMARY KEY,
        first_name NVARCHAR(50),
        last_name NVARCHAR(50),
        email NVARCHAR(100),
        phone_number NVARCHAR(20),
        date_of_birth DATE,
        address NVARCHAR(255),
        pan_number NVARCHAR(20)
    );
END
GO

-- 3. Create the Transactions Table
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[transactions]') AND type in (N'U'))
BEGIN
    CREATE TABLE transactions (
        id INT IDENTITY(1,1) PRIMARY KEY,
        user_id INT FOREIGN KEY REFERENCES users(id),
        amount DECIMAL(10, 2),
        status VARCHAR(50),
        created_at DATETIME DEFAULT GETDATE()
    );
END
GO

-- 4. Insert Dummy Data
-- User
IF NOT EXISTS (SELECT * FROM users WHERE email = 'test.user@zetapay.com')
BEGIN
    INSERT INTO users (first_name, last_name, email, phone_number, date_of_birth, address, pan_number)
    VALUES ('Test', 'User', 'test.user@zetapay.com', '1234567890', '1990-01-01', '123 Test St', 'ABCDE1234F');
END

-- Transaction
DECLARE @UserId INT = (SELECT id FROM users WHERE email = 'test.user@zetapay.com');

IF NOT EXISTS (SELECT * FROM transactions WHERE user_id = @UserId)
BEGIN
    INSERT INTO transactions (user_id, amount, status, created_at)
    VALUES (@UserId, 100.00, 'SUCCESS', GETDATE());
END
GO

SELECT * FROM users;
SELECT * FROM transactions;
