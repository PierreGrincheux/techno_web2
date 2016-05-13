/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.dao;

import com.catnix.beans.TenderToAssign;
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
public class TenderToAssignDaoImpl implements TenderToAssignDao {

    private static final String SQL_SELECT_BY_ID_TENDER_AND_NAME_CP = "SELECT id, id_tender, title, motivation_text, name_cp, date FROM tendertoassign WHERE id_tender = ? and name_cp = ?";
    private static final String SQL_SELECT_TENDER_TO_ASSIGN_BY_TENDER_ID = "Select id, id_tender, title, motivation_text, name_cp, date from tendertoassign WHERE assigned is null ORDER BY id_tender ASC";
    private static final String SQL_INSERT = "INSERT INTO tendertoassign (id_tender, title, motivation_text, name_cp, date) VALUES (?, ?, ?, ?, NOW())";
    private static final String SQL_UPDATE_WHEN_ASSIGNED = "UPDATE tendertoassign SET assigned = 'yes' WHERE id_tender = ?";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM tendertoassign WHERE id = ?";

    private final DAOFactory daoFactory;

    public TenderToAssignDaoImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public TenderToAssign find(Long idTender, String nameCP) throws DAOException {
        return find(SQL_SELECT_BY_ID_TENDER_AND_NAME_CP, idTender, nameCP);
    }

    @Override
    public void create(TenderToAssign tenderToAssign) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_INSERT, true,
                    tenderToAssign.getId_tender(), tenderToAssign.getTitle(), tenderToAssign.getMotivationText(), tenderToAssign.getNameCP());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Creating tender to assign failed, no inserted row in datatable.");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                tenderToAssign.setId(valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException("Creating tender to assign in database failed, no ID auto-generated returned.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connection);
        }
    }
    
      @Override
    public void updateAcceptedTender(TenderToAssign tenderToAssign) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_UPDATE_WHEN_ASSIGNED, true,
                    tenderToAssign.getId_tender());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Updating tender to assign failed, no updated row in datatable.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connection);
        }
    }

    @Override
    public List<TenderToAssign> list() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TenderToAssign> tendersToAssign = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_TENDER_TO_ASSIGN_BY_TENDER_ID);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tendersToAssign.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return tendersToAssign;
    }

    @Override
    public void delete(TenderToAssign tenderToAssign) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_DELETE_BY_ID, true, tenderToAssign.getId());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Deleting tender to assign failed, no deleted row in datatable.");
            } else {
                tenderToAssign.setId(null);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(preparedStatement, connection);
        }
    }

    private TenderToAssign find(String sql, Object... objets) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        TenderToAssign tenderToAssign = null;

        try {

            connection = daoFactory.getConnection();

            preparedStatement = preparedStatementInit(connection, sql, false, objets);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tenderToAssign = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return tenderToAssign;
    }

    private static TenderToAssign map(ResultSet resultSet) throws SQLException {

        TenderToAssign tenderToAssign = new TenderToAssign();
        tenderToAssign.setId(resultSet.getLong("id"));
        tenderToAssign.setId_tender(resultSet.getLong("id_tender"));
        tenderToAssign.setTitle(resultSet.getString("title"));
        tenderToAssign.setNameCP(resultSet.getString("name_cp"));
        tenderToAssign.setMotivationText(resultSet.getString("motivation_text"));
        tenderToAssign.setDate(resultSet.getDate("date"));

        return tenderToAssign;
    }

}
