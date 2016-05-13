/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.dao;

import com.catnix.beans.Tender;
import static com.catnix.dao.DAOUtilitaire.preparedStatementInit;
import static com.catnix.dao.DAOUtilitaire.silentClosures;
import com.catnix.exceptions.DAOException;
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
public class TenderDaoImpl implements TenderDao {

    private static final String SQL_SELECT_TENDER_TO_HANDLE = "SELECT id, title, activity_area, contact_name, contact_phone, contact_email, origin  FROM tender where accepted is null ORDER BY id";
    private static final String SQL_SELECT_HANDLED_TENDER = "SELECT id, title, activity_area, contact_name, contact_phone, contact_email, origin  FROM tender where accepted is not null and assigned is null ORDER BY id";
    private static final String SQL_SELECT_CP_TENDERS = "SELECT id, title, activity_area, contact_name, contact_phone, contact_email, origin  FROM tender where name_cp = ? ORDER BY id";
    private static final String SQL_SELECT_BY_TITLE = "SELECT id, title, activity_area, contact_name, contact_phone, contact_email, origin FROM tender WHERE title = ?";
    private static final String SQL_INSERT = "INSERT INTO tender (title, activity_area, contact_name, contact_phone, contact_email, origin) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_WHEN_ACCEPTED = "UPDATE tender SET accepted = 'yes' WHERE id = ? ";
    private static final String SQL_UPDATE_WHEN_ASSIGNED = "UPDATE tender SET name_cp = ?, assigned = 'yes' WHERE id = ? ";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM tender WHERE id = ?";

    private final DAOFactory daoFactory;

    public TenderDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Tender find(String title) throws DAOException {
        return find(SQL_SELECT_BY_TITLE, title);
    }

    @Override
    public void create(Tender tender) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_INSERT, true,
                    tender.getTitle(), tender.getActivityArea(), tender.getContactName(), tender.getContactPhone(), tender.getContactEmail(), tender.getOrigin());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Creating tender failed, no inserted row in datatable.");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                tender.setId(valeursAutoGenerees.getLong(1));
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
    public void updateAcceptedTender(Tender tender) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_UPDATE_WHEN_ACCEPTED, true,
                    tender.getId());
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
    public void updateTenderWhenCpIsAssigned(Tender tender) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_UPDATE_WHEN_ASSIGNED, true,
                    tender.getNameCp(), tender.getId());
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
    public List<Tender> listTenderToHandle() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Tender> tenders = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_TENDER_TO_HANDLE);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tenders.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return tenders;
    }

    @Override
    public List<Tender> listHandledTender() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Tender> tenders = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_HANDLED_TENDER);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tenders.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return tenders;
    }

    @Override
    public List<Tender> listCpTenders(String nameCp) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Tender> tenders = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_SELECT_CP_TENDERS, true, nameCp);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tenders.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return tenders;
    }

    @Override
    public void delete(Tender tender) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_DELETE_BY_ID, true, tender.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Deleting tender failed, no deleted row in datatable.");
            } else {
                tender.setId(null);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(preparedStatement, connection);
        }
    }

    private Tender find(String sql, Object... objets) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Tender tender = null;

        try {

            connection = daoFactory.getConnection();

            preparedStatement = preparedStatementInit(connection, sql, false, objets);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tender = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return tender;
    }

    private static Tender map(ResultSet resultSet) throws SQLException {

        Tender tender = new Tender();
        tender.setId(resultSet.getLong("id"));
        tender.setTitle(resultSet.getString("title"));
        tender.setActivityArea(resultSet.getString("activity_area"));
        tender.setContactName(resultSet.getString("contact_name"));
        tender.setContactPhone(resultSet.getString("contact_phone"));
        tender.setContactEmail(resultSet.getString("contact_email"));
        tender.setOrigin(resultSet.getString("origin"));

        return tender;
    }

}
