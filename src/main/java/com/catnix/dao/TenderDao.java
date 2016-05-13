/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.catnix.dao;

import com.catnix.beans.Tender;
import com.catnix.exceptions.DAOException;
import java.util.List;

/**
 *
 * @author Fritsch
 */
public interface TenderDao {

    void create(Tender tender) throws DAOException;

    Tender find(String title) throws DAOException;

    void updateAcceptedTender(Tender tender) throws DAOException;

    void updateTenderWhenCpIsAssigned(Tender tender) throws DAOException;

    List<Tender> listTenderToHandle() throws DAOException;

    List<Tender> listHandledTender() throws DAOException;

    List<Tender> listCpTenders(String nameCp) throws DAOException;

    void delete(Tender tender) throws DAOException;

}
