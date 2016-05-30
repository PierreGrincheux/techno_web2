
package com.catnix.dao;

import com.catnix.beans.Comment;
import static com.catnix.dao.DAOUtilitaire.preparedStatementInit;
import static com.catnix.dao.DAOUtilitaire.silentClosures;
import com.catnix.exceptions.DAOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static com.catnix.dao.DAOUtilitaire.silentClosures;

/**
 *
 * @author mélanie
 */
public class CommentDaoImpl implements CommentDao {
    private static final String SQL_INSERT = "INSERT INTO comment (author_id, prospect_id, content, date) VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_WITH_ID = "SELECT *  FROM comment WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE comment SET author_id = ?, prospect_id = ?, content = ?, date = ? WERE id = ?";
    private static final String SQL_SELECT_WITH_PROSPECT_ID = "SELECT * FROM comment WHERE prospect_id = ? ";
    private static final String SQL_SELECT_WITH_AUTHOR_ID = "SELECT * FROM comment WHERE author_id = ? ";
    private static final String SQL_DELETE_FROM_ID = "DELETE FROM comment WHERE id = ?";
    
    private final DAOFactory daoFactory;

    public CommentDaoImpl() {
        this.daoFactory = DAOFactory.getInstance();
    }

    @Override
    public void create(Comment comment) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        
        try {
            connection = daoFactory.getConnection();
            
            preparedStatement = preparedStatementInit(connection, SQL_INSERT, true);
            preparedStatement.setLong(1, comment.getAuthor_id());
            preparedStatement.setLong(2, comment.getProspect_id());
            preparedStatement.setString(3, comment.getContent());
            java.sql.Date date = new java.sql.Date(comment.getDate().getTime());
            preparedStatement.setDate(4, date);        
            
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Adding comment failed, no inserted row in datatable.");
            }
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                comment.setId(valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException("adding comment to database failed, no ID auto-generated returned.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connection);
        }

    }

    @Override
    public Comment find(long comment_id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Comment comment = new Comment();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_WITH_ID);
            preparedStatement.setLong(1, comment_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                comment = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return comment;
        
    }

    @Override
    public void update(Comment comment) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        
        try {
            connection = daoFactory.getConnection();
            
            preparedStatement = preparedStatementInit(connection, SQL_UPDATE, true);
            preparedStatement.setLong(1, comment.getAuthor_id());
            preparedStatement.setLong(2, comment.getProspect_id());
            preparedStatement.setString(3, comment.getContent());
            preparedStatement.setDate(4, (Date) comment.getDate());
            preparedStatement.setLong(5, comment.getId());
            
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Updating comment failed, no inserted row in datatable.");
            }           
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connection);
        }
    }

    @Override
    public ArrayList<Comment> list_for_prospect(long prospect_id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Comment> prospect_comments = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_WITH_PROSPECT_ID);
            preparedStatement.setLong(1, prospect_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                prospect_comments.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return prospect_comments;
    }

    @Override
    public ArrayList<Comment> list_for_author(long author_id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Comment> author_comments = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(SQL_SELECT_WITH_AUTHOR_ID);
            preparedStatement.setLong(1, author_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                author_comments.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return author_comments;
    }

    @Override
    public void delete(long comment_id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = preparedStatementInit(connection, SQL_DELETE_FROM_ID, true);
            preparedStatement.setLong(1, comment_id);
            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Deleting comment failed, no deleted row in datatable.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(preparedStatement, connection);
        }
    }
    
    private static Comment map(ResultSet resultSet) throws SQLException {
        Comment comment = new Comment();
        
        comment.setId(resultSet.getLong("id"));
        comment.setAuthor_id(resultSet.getLong("author_id"));
        comment.setProspect_id(resultSet.getLong("prospect_id"));
        comment.setContent(resultSet.getString("content"));
        comment.setDate(resultSet.getDate("date"));
        
        return comment;
    }
    
}
