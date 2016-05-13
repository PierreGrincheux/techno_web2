/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.dao;

/**
 *
 * @author Fritsch
 */
import com.catnix.exceptions.DAOException;
import com.catnix.beans.Member;
import java.sql.SQLException;
import java.util.List;

public interface MemberDao {

    public abstract Member find(String string) throws SQLException;
    
     List<Member> list() throws DAOException;

}
