/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tech.blog.dao;

import com.tech.blog.entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
}
