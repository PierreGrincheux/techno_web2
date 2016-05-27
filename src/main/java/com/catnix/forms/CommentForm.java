package com.catnix.forms;

import com.catnix.beans.Comment;
import com.catnix.dao.CommentDao;
import com.catnix.dao.CommentDaoImpl;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mélanie
 */
public class CommentForm {
    private static final String COMMENT_ID_FIELD = "comment_id";
    private static final String AUTHOR_ID_FIELD = "author_id";
    private static final String PROSPECT_ID_FIELD = "prospect_id";
    private static final String CONTENT_FIELD = "content";
    
    private String result;
    private Map<String, String> errors = new HashMap<>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    private Comment comment;
    private final CommentDao commentDao;

    public CommentForm() {
        this.commentDao = new CommentDaoImpl();
        this.comment = new Comment();
    }
    
    public Comment addComment(HttpServletRequest request) throws SQLException, Exception {

        long author_id = Long.parseLong(getFieldValue(request, AUTHOR_ID_FIELD ));
        long prospect_id = Long.parseLong(getFieldValue(request, PROSPECT_ID_FIELD ));
        String content = getFieldValue(request, CONTENT_FIELD);
        
        comment.setAuthor_id(author_id);
        comment.setProspect_id(prospect_id); 
        Date date = new Date();
        comment.setDate(date);
        try {
            IsNullFieldValidation(content);
        } catch (Exception e) {
            setErreur(CONTENT_FIELD, e.getMessage());
        }
        comment.setContent(content);
        
        if (errors.isEmpty()) {
            commentDao.create(comment);
            result = "Your comment has been added to the database";
        } else {
            result = "Adding failed !";
        }
        return comment;
    }
    
    public void IsNullFieldValidation(String field) throws SQLException, Exception {
        if (field == null) {
            throw new Exception("Please fill the field.");
        }
    }

    public void LenghtFieldValidation(String field, int lenght) throws SQLException, Exception {
        if (field.length() > lenght) {
            throw new Exception("This field cannot be more than " + lenght + " characters long");

        }
    }

    public void setErreur(String field, String message) {
        errors.put(field, message);
    }

    public String getFieldValue(HttpServletRequest request, String fieldName) {
        String value = request.getParameter(fieldName);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value;
        }
    }
    
}
