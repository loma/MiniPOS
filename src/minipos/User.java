/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minipos;

import Repository.Repository;

/**
 *
 * @author loma
 */
class User {

    Role role;
    boolean loginStatus = false;

    boolean isLogin() {
        return loginStatus;
    }

    void login(String username, String password) {
        loginStatus = Repository.checkUsernamePassword(username, password);
    }
    
}
