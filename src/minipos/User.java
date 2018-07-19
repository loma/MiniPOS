/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipos;

import Repository.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author loma
 */
public class User {

    static List<User> all() {
        return Repository.getAllUsers();
    }

    boolean loginStatus = false;
    private int id;
    private String name;
    Role role;

    public User(int id, String name, int role) {
        this.id=id;
        this.name=name;
        this.role = Role.values()[role];
    }

    User() {
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

    boolean isLogin() {
        return loginStatus;
    }

    void login(String username, String password) {
        loginStatus = Repository.checkUsernamePassword(username, password);
    }
    
}
