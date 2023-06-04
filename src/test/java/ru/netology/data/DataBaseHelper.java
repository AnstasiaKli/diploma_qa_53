package ru.netology.data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;


import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHelper {
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");

    public static void cleanupTablesDB() {
        val cleanupCreditRequestEntity = "DELETE FROM credit_request_entity;";
        val cleanupOrderEntity = "DELETE FROM order_entity;";
        val cleanupPaymentEntity = "DELETE FROM payment_entity;";
        val runner = new QueryRunner();
        try (val connection = DriverManager.getConnection(url, user, password)) {
            runner.update(connection, cleanupCreditRequestEntity);
            runner.update(connection, cleanupOrderEntity);
            runner.update(connection, cleanupPaymentEntity);
        } catch (SQLException e) {
            System.out.println("SQL exception in cleanupTablesDB");
        }
    }

    public static String findPaymentStatus() {
        val statusSQL = "SELECT status FROM payment_entity;";
        return getData(statusSQL);
    }

    public static String findCreditRequestStatus() {
        val statusSQL = "SELECT status FROM credit_request_entity;";
        return getData(statusSQL);
    }

    public static String orderCount() {
        val statusSQL = "SELECT COUNT(*) FROM order_entity;";
        val runner = new QueryRunner();
        Long count = null;
        try (val connection = DriverManager.getConnection(url, user, password)) {
        count = runner.query(connection, statusSQL, new ScalarHandler<>());
    } catch (SQLException e) {
            e.printStackTrace();
        }
        return Long.toString(count);
    }

    private static String getData(String query) {
        val runner = new QueryRunner();
        String data = "";
        try (val connection = DriverManager.getConnection(url, user, password)) {
            data = runner.query(connection, query, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }



}
