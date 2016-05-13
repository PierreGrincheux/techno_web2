/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.dao;

import com.catnix.beans.TenderToAssign;
import com.catnix.exceptions.DAOException;
import java.util.List;

/**
 *
 * @author Fritsch
 */
public interface TenderToAssignDao {

    void create(TenderToAssign tenderToAssign) throws DAOException;

    
    void updateAcceptedTender(TenderToAssign tenderToAssign) throws DAOException;
    
    List<TenderToAssign> list() throws DAOException;

    TenderToAssign find(Long id, String nameCP) throws DAOException;
    
    void delete(TenderToAssign tenderToAssign) throws DAOException;

}
