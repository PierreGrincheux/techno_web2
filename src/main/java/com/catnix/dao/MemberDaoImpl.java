/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.dao;

import com.catnix.exceptions.DAOException;
import com.catnix.beans.Member;
import static com.catnix.dao.DAOUtilitaire.preparedStatementInit;
import static com.catnix.dao.DAOUtilitaire.silentClosures;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fritsch
 */
public class MemberDaoImpl implements MemberDao {

    private final DAOFactory daoFactory;
    private static final String SQL_SELECT = "SELECT id, lastname, firstname, role, username, password FROM member ORDER BY id";
    private static final String SQL_SELECT_BY_USERNAME = "SELECT id, lastname, firstname, role, username, password  FROM member WHERE username = ?";

    MemberDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Member find(String username) throws DAOException {
        return find(SQL_SELECT_BY_USERNAME, username);
    }

    private Member find(String sql, Object... objets) throws DAOException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Member member = null;

        try {

            connexion = daoFactory.getConnection();

            preparedStatement = preparedStatementInit(connexion, sql, false, objets);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                member = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connexion);
        }

        return member;
    }

    @Override
    public List<Member> list() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Member> members = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                members.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return members;
    }

    private static Member map(ResultSet resultSet) throws SQLException {
        Member member = new Member();

        member.setId(resultSet.getLong("id"));
        member.setLastname(resultSet.getString("lastname"));
        member.setFirstname(resultSet.getString("firstname"));
        member.setRole(resultSet.getString("role"));
        member.setUsername(resultSet.getString("username"));
        member.setPassword(resultSet.getString("password"));

        return member;
    }
}
