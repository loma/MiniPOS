/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import User.Role;
import Repository.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author loma
 */
public class User {

    public static List<User> all() {
        return Repository.getAllUsers();
    }

    public static User find(int userId) {
        return Repository.findUser(userId);
    }

    boolean loginStatus = false;
    private int id;
    private String name;
    Role role;
    private String password;

    public User(int id, String name, int role) {
        this.id=id;
        this.name=name;
        this.role = Role.values()[role];
    }

    public User() {
    }

    public User(int id, String name, String password, int role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = Role.values()[role];
    }


    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public Role getRole(){
        return this.role;
    }
    public String getPassword(){
        return this.password;
    }

    public boolean isLogin() {
        return loginStatus;
    }

    public void login(String username, String password) {
        this.setUsername(username);
        loginStatus = Repository.checkUsernamePassword(username, password);
    }

    public void save() {
        Repository.updateUser(this);
    }

    public void delete() {
        Repository.deleteUser(this);
    }

    public void setUsername(String username) {
        this.name = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = Role.values()[role];
    }

    public void update() {
        Repository.updateUser(this);
    }
    
}
