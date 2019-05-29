package com.invillia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Principal {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/demo";

        Properties properties = new Properties();
        properties.setProperty("user", "s2it_leferreira");
        properties.setProperty("password", "123123");

        try (
                Connection connection = DriverManager.getConnection(url, properties);
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery("select name from contact");
        ) {
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (Exception e) {
            System.err.println("deu ruim");
        }
    }

}
