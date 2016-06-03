/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.dao;

import com.catnix.beans.Prospect;
import com.catnix.exceptions.DAOException;
import java.util.ArrayList;

/**
 *
 * @author melanie
 */
public interface ProspectDao {
    
    void create(Prospect prospect) throws DAOException;

    Prospect find(long id) throws DAOException;

    void update(Prospect prospect) throws DAOException;

    ArrayList<Prospect> list() throws DAOException;
    
    void delete(long id) throws DAOException;
    
    void deleteRelatedComments(long prospectid) throws DAOException;
    
    int getNbOfProspectByState(String state)throws DAOException;
    
}
