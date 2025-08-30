package com.gymapp.test;

import com.gymapp.database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabase {
    public static void main(String[] args) {
        try (Connection conn = Database.getConnection()) {
            System.out.println("Conexión exitosa!");

            // Ejemplo: listar miembros
            String query = "SELECT * FROM members";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    System.out.println(id + " | " + name + " " + lastName + " | " + email);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
    }
}
