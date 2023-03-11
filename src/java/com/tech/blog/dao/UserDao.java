/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.dao;

import com.tech.blog.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Charlie Oskar
 */
public class UserDao {

    private Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }

    public boolean insert(User user) {
        boolean f = false;

        try {
            String query = "INSERT INTO users(name, email, password, gender, about) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement smt = this.con.prepareStatement(query);
            smt.setString(1, user.getName());
            smt.setString(2, user.getEmail());
            smt.setString(3, user.getPassword());
            smt.setString(4, user.getGender());
            smt.setString(5, user.getAbout());
            smt.executeUpdate();
            smt.close();

            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE email = ? and password = ? and deleted_at is null";
            PreparedStatement smt = con.prepareStatement(query);
            smt.setString(1, email);
            smt.setString(2, password);
            ResultSet res = smt.executeQuery();
            if (res.next()) {
                user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setPassword(res.getString("password"));
                user.setEmail(res.getString("email"));
                user.setGender(res.getString("gender"));
                user.setAbout(res.getString("about"));
                user.setCreated_at(res.getTimestamp("created_at"));
                user.setUpdated_at(res.getTimestamp("updated_at"));
                user.setImage(res.getString("image"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
