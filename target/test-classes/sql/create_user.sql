USE master;
GO

-- Create Login if it doesn't exist
IF NOT EXISTS (SELECT * FROM sys.server_principals WHERE name = 'testuser')
BEGIN
    CREATE LOGIN testuser WITH PASSWORD = 'testpass';
END
GO

USE zetapay_test;
GO

-- Create User for the Login
IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = 'testuser')
BEGIN
    CREATE USER testuser FOR LOGIN testuser;
END
GO

-- Grant permissions (db_owner is easiest for testing)
ALTER ROLE db_owner ADD MEMBER testuser;
GO
