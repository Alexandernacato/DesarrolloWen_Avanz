/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espe.app.dao;

import com.espe.app.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    // Obtener todos los usuarios
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = ConnectionBdd.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setAge(rs.getInt("age"));
                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    // Guardar un nuevo usuario
    public boolean save(User user) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionBdd.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastname());
            stmt.setInt(3, user.getAge());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar usuario por ID
    public User findById(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection conn = ConnectionBdd.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastname(rs.getString("lastname"));
                user.setAge(rs.getInt("age"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    // Actualizar usuario
    public boolean update(User user) {
        String sql = "UPDATE users SET name = ?, lastname = ?, age = ? WHERE id = ?";

        try (Connection conn = ConnectionBdd.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastname());
            stmt.setInt(3, user.getAge());
            stmt.setLong(4, user.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar usuario
    public boolean delete(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = ConnectionBdd.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
        
        
        
        
        
}
