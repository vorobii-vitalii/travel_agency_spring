package org.travel.agency;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class TestDBConnection {

    /**
     * Method checks the connection to db
     * @param args - args[0] jdbc url, args[1] username, args[2] password
     */
    public static void main(String[] args) {
        String url = String.format("%s?serverTimezone=UTC&useSSL=false", args[0]);
        String username = args[1];
        String password = args[2];
        log.info("Connecting...");

        try (Connection ignored = DriverManager.getConnection(url, username, password)) {
            log.info("Connection successful!");
        } catch (SQLException e) {
            log.error("Connection failed!");
            e.printStackTrace();
        }
    }

}
