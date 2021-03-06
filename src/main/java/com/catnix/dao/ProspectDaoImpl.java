package com.catnix.dao;

import com.catnix.beans.Comment;
import com.catnix.beans.Prospect;
import static com.catnix.dao.DAOUtilitaire.preparedStatementInit;
import static com.catnix.dao.DAOUtilitaire.silentClosures;
import com.catnix.exceptions.DAOException;
import java.sql.Connection;
import java.sql.Date;
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
    private static final String SQL_UPDATE = "UPDATE prospect SET activity_area = ?, website = ?, phone_number = ?, email = ?, contact_name = ?, state = ?, callback_date = ? WHERE id = ?";
    private static final String SQL_DELETE_FROM_ID = "DELETE FROM prospect WHERE id = ?";
    private static final String SQL_COUNT_STATE = "SELECT COUNT(*) AS COUNTRESULT FROM prospect where state= ?";

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
                    prospect.getCompany_name(), prospect.getActivity_area(), prospect.getWebsite(), prospect.getPhoneNumber(), prospect.getEmail(), prospect.getContact_name(), prospect.getState(), prospect.getCallback_date());
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
            preparedStatement = preparedStatementInit(connection, SQL_UPDATE, true, prospect.getId());
            preparedStatement = preparedStatementInit(connection, SQL_UPDATE, true);
            preparedStatement.setString(1, prospect.getActivity_area());
            preparedStatement.setString(2, prospect.getWebsite());
            preparedStatement.setString(3, prospect.getPhoneNumber());
            preparedStatement.setString(4, prospect.getEmail());
            preparedStatement.setString(5, prospect.getContact_name());
            preparedStatement.setString(6, prospect.getState());
            if (prospect.getCallback_date() == null) {
                preparedStatement.setDate(7, (Date) prospect.getCallback_date());
            } else {
                java.sql.Date callback = new java.sql.Date(prospect.getCallback_date().getTime());
                preparedStatement.setDate(7, callback);
            }
            preparedStatement.setLong(8, prospect.getId());
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
    public void deleteRelatedComments(long prospectid) throws DAOException {
        ArrayList<Comment> allcomments = new ArrayList();
        CommentDao commentDao = new CommentDaoImpl();
        allcomments = commentDao.list_for_prospect(prospectid);
        for (Comment comment : allcomments) {
            commentDao.delete(comment.getId());
        }
    }

    @Override
    public void delete(long prospectid) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        deleteRelatedComments(prospectid);
        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_DELETE_FROM_ID, true, prospectid);
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                    throw new DAOException("Deleting prospect failed, no deleted row in datatable.");
                
            } 
        }catch (SQLException e) {
            throw new DAOException(e);
        }finally {
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
        prospect.setPhoneNumber(resultSet.getString("phone_number"));
        prospect.setEmail(resultSet.getString("email"));
        prospect.setContact_name(resultSet.getString("contact_name"));
        prospect.setState(resultSet.getString("state"));
        prospect.setCallback_date(resultSet.getDate("callback_date"));

        return prospect;
    }

    @Override
    public int getNbOfProspectByState(String state) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int nbProspects = 0;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_COUNT_STATE);
            preparedStatement.setString(1, state);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                nbProspects = resultSet.getInt("COUNTRESULT");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return nbProspects;

    }

}
