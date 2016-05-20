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

/**
 *
 * @author melanie
 */
public class ProspectDaoImpl implements ProspectDao {

    private static final String SQL_SELECT = "SELECT * FROM  prospect";
    private static final String SQL_SELECT_WITH_ID = "SELECT *  FROM prospect WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO prospect (company_name, activity_area, website, phone_number, email, contact_name, state, callback_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE prospect SET activity_area = ?, website = ?, phone_number = ?, email = ?, contact_name = ?, state = ?, callback_date = ? WERE id = ?";
    private static final String SQL_DELETE_FROM_ID = "DELETE FROM prospect WHERE id = ?";

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
                    prospect.getCompany_name(), prospect.getActivity_area(), prospect.getWebsite(), prospect.getPhone_number(), prospect.getEmail(), prospect.getContact_name(), prospect.getState(), prospect.getCallback_date());
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Creating prospect failed, no inserted row in datatable.");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                prospect.setId(valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException("Creating prospect in database failed, no ID auto-generated returned.");
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
            
            preparedStatement = preparedStatementInit(connection, SQL_UPDATE, true);
            preparedStatement.setString(1, prospect.getActivity_area());
            preparedStatement.setString(2, prospect.getWebsite());
            preparedStatement.setString(3, prospect.getPhone_number());
            preparedStatement.setString(4, prospect.getEmail());
            preparedStatement.setString(5, prospect.getContact_name());
            preparedStatement.setString(7, prospect.getState());
            preparedStatement.setDate(8, (java.sql.Date) prospect.getCallback_date());
            preparedStatement.setLong(9, prospect.getId());
            
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Updating prospect failed, no updated row in datatable.");
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
    public void delete(long prospect_id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_DELETE_FROM_ID, true, prospect_id);
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Deleting prospect failed, no deleted row in datatable.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(preparedStatement, connection);
        }
    }

    private static Prospect map(ResultSet resultSet) throws SQLException {
        Prospect prospect = new Prospect();
        prospect.setId(resultSet.getLong("id"));
        prospect.setActivity_area(resultSet.getString("activity_area"));
        prospect.setWebsite(resultSet.getString("website"));
        prospect.setCompany_name(resultSet.getString("company_name"));
        prospect.setPhone_number(resultSet.getString("phone_number"));
        prospect.setEmail(resultSet.getString("email"));
        prospect.setContact_name(resultSet.getString("contact_name"));
        prospect.setState(resultSet.getString("state"));
        prospect.setCallback_date(resultSet.getDate("callback_date"));

        return prospect;
    }
 
}
