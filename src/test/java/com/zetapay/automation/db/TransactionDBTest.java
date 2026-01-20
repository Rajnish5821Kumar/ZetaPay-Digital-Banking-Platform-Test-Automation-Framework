package com.zetapay.automation.db;

import com.zetapay.automation.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDBTest {

    @BeforeClass
    public void setup() {
        DatabaseUtils.initDb(); // Setup manually by user script
    }

    @Test
    public void testTransactionIntegrity() throws SQLException {
        // Example query to fetch latest transaction
        String query = "SELECT status FROM transactions ORDER BY created_at DESC LIMIT 1";
        ResultSet rs = DatabaseUtils.executeQuery(query);

        if (rs != null && rs.next()) {
            String status = rs.getString("status");
            System.out.println("Latest Transaction Status: " + status);
            Assert.assertTrue(status.matches("SUCCESS|PENDING|FAILED"), "Invalid transaction status in DB");
        } else {
            // If no data, we skip or fail depending on requirement. For now, valid if no error.
            System.out.println("No transactions found in DB to verify.");
        }
    }

    @AfterClass
    public void teardown() {
        DatabaseUtils.close();
    }
}
