/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.forms;

import com.catnix.beans.Member;
import com.catnix.dao.MemberDao;
import com.catnix.methods.Methods;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Fritsch
 */
public class ConnectionForm {

    private static final String USERNAME_FIELD = "username";
    private static final String PASSWORD_FIELD = "password";
    private String result;
    private Map<String, String> errors = new HashMap<>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    private Member member;
    private final MemberDao memberDao;

    public ConnectionForm(MemberDao memberDao) {
        this.memberDao = memberDao;
        this.member = new Member();
    }

    public Member connectMember(HttpServletRequest request) throws SQLException, Exception {

        request.setCharacterEncoding("UTF-8");
        String username = Methods.getFieldValue(request, ConnectionForm.USERNAME_FIELD);
        String password = Methods.getFieldValue(request, ConnectionForm.PASSWORD_FIELD);

        handleUsername(username);
        handlePassword(password, username);

        if (getErrors().isEmpty()) {
            result = "Connection successful !";
        } else {
            result = "Connection failed !";
        }

        return memberConstruct(member, username);

    }

    private void handleUsername(String username) {
        try {
            usernameValidation(username);
        } catch (Exception e) {
            setErreur(USERNAME_FIELD, e.getMessage());
        }
        member.setUsername(username);
    }

    private void handlePassword(String password, String username) {
        try {
            passwordValidation(password, username);
        } catch (Exception e) {
            setErreur(PASSWORD_FIELD, e.getMessage());
        }
        member.setPassword(password);
    }

    private Member memberConstruct(Member member, String username) throws Exception {

        if (username != null) {

            member = memberDao.find(username);

        }

        return member;
    }

    public void usernameValidation(String username) throws SQLException, Exception {

        if (username != null) {

            if (username.length() >= 3) {

                if (memberDao.find(username) == null) {

                    throw new Exception("Username is incorrect.");
                }
            } else {

                throw new Exception("Username must be at least 3 characters long.");
            }

        } else {

            throw new Exception("Please enter a username.");
        }
    }

    public void passwordValidation(String password, String username) throws SQLException, Exception {

        if (password != null) {

            if (password.length() >= 3) {

                if (!password.equals(memberDao.find(username).getPassword())) {

                    throw new Exception("Password is incorrect.");
                }
            } else {

                throw new Exception("Password must be at least 3 characters long.");
            }

        } else {

            throw new Exception("Please enter a password.");
        }
    }

    public void setErreur(String field, String message) {
        errors.put(field, message);
    }

}
