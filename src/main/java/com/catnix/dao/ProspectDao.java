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

<<<<<<< HEAD
    void delete(Prospect prospect) throws DAOException;
=======
    void delete(long id) throws DAOException;
>>>>>>> cd425936a9d37ab614de8f70d747ddee34f68c77
    
}
