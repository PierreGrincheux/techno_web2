/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.dao;

import com.catnix.beans.Prospect;
import static com.catnix.dao.DAOUtilitaire.preparedStatementInit;
import static com.catnix.dao.DAOUtilitaire.silentClosures;
import com.catnix.exceptions.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author melanie
 */
public class ProspectDaoImpl implements ProspectDao {

    private static final String SQL_SELECT = "SELECT * FROM  prospect";
    private static final String SQL_SELECT_WITH_ID = "SELECT *  FROM prospect WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO prospect (company_name, activity_area, website, phone_number1, phone_number2, email, contact_name, cp_id, state, callback_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE prospect SET id = ?, company_name = ?, activity_area = ?, website = ?, phone_number1 = ?, phone_number2 = ?, email = ?, contact_name = ?, cp_id = ?, state = ?, callback_date = ?";
    private static final String SQL_DELETE_PAR_ID = "DELETE FROM prospect WHERE id = ?";

    private final DAOFactory daoFactory;

    public ProspectDaoImpl() {
        this.daoFactory = DAOFactory.getInstance();
    }

    @Override
    public Prospect find(long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Prospect prospect = null;

        try {
            connection = daoFactory.getConnection();           
            preparedStatement = connection.prepareStatement(SQL_SELECT_WITH_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();           
            if (resultSet.next()) {
                prospect = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return prospect;
    }

    @Override
    public void create(Prospect prospect) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_INSERT, true,
                    prospect.getCompany_name(), prospect.getActivity_area(), prospect.getWebsite(), prospect.getPhone_number1(), prospect.getPhone_number2(), prospect.getEmail(), prospect.getContact_name(), prospect.getCp_id(), prospect.getState(), prospect.getCallback_date());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Creating tender failed, no inserted row in datatable.");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                prospect.setId(valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException("Creating tender in database failed, no ID auto-generated returned.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connection);
        }
    }

    @Override
    public void update(Prospect prospect) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_UPDATE, true,
                    prospect.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Updating tender failed, no updated row in datatable.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connection);
        }

    }

    @Override
    public ArrayList<Prospect> list() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Prospect> prospects = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT);
            resultSet = preparedStatement.executeQuery();
            System.out.println("connected to db");
            while (resultSet.next()) {
                prospects.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return prospects;
    }

    @Override
    public void delete(Prospect prospect) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_DELETE_PAR_ID, true, prospect.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Deleting tender failed, no deleted row in datatable.");
            } else {
                prospect.setId(null);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(preparedStatement, connection);
        }
    }

    private Prospect trouver(String sql, Object... objets) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Prospect prospect = null;

        try {

            connection = daoFactory.getConnection();

            preparedStatement = preparedStatementInit(connection, sql, false, objets);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                prospect = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return prospect;
    }

    private static Prospect map(ResultSet resultSet) throws SQLException {
        Prospect prospect = new Prospect();
        prospect.setId(resultSet.getLong("id"));
        prospect.setActivity_area(resultSet.getString("activity_area"));
        prospect.setWebsite(resultSet.getString("website"));
        prospect.setCompany_name(resultSet.getString("company_name"));
        prospect.setPhone_number1(resultSet.getString("phone_number1"));
        prospect.setPhone_number2(resultSet.getString("phone_number2"));
        prospect.setEmail(resultSet.getString("email"));
        prospect.setContact_name(resultSet.getString("contact_name"));
        prospect.setState(resultSet.getString("state"));
        prospect.setCp_id(resultSet.getLong("cp_id"));
        prospect.setCallback_date(resultSet.getDate("callback_date"));

        return prospect;
    }
 
}
