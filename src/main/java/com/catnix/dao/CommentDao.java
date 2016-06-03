
package com.catnix.dao;

import com.catnix.beans.Comment;
import com.catnix.exceptions.DAOException;
import java.util.ArrayList;

/**
 *
 * @author mélanie
 */
public interface CommentDao {
    void create(Comment comment) throws DAOException;

    Comment find(long comment_id) throws DAOException;

    void update(Comment comment) throws DAOException;

    ArrayList<Comment> list_for_prospect(long prospect_id) throws DAOException;
    
    ArrayList<Comment> list_for_author(long author_id) throws DAOException;

    void delete(long id) throws DAOException;
    
}
