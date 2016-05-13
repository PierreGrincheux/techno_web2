package com.catnix.dao;

import com.catnix.exceptions.DAOConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

    private static final String FICHIER_PROPERTIES = "dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_NOM_UTILISATEUR = "username";
    private static final String PROPERTY_MOT_DE_PASSE = "password";

    private final String url;
    private final String username;
    private final String password;

    DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException {
        Properties properties = new Properties();
        String url;
        String driver;
        String nomUtilisateur;
        String motDePasse;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream fichierProperties = classLoader.getResourceAsStream(FICHIER_PROPERTIES);

        if (fichierProperties == null) {
            throw new DAOConfigurationException("Le fichier properties " + FICHIER_PROPERTIES + " est introuvable.");
        }

        try {
            properties.load(fichierProperties);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            nomUtilisateur = properties.getProperty(PROPERTY_NOM_UTILISATEUR);
            motDePasse = properties.getProperty(PROPERTY_MOT_DE_PASSE);
        } catch (FileNotFoundException e) {
            throw new DAOConfigurationException("Properties file " + FICHIER_PROPERTIES + " is not found.",
                    e);
        } catch (IOException e) {
            throw new DAOConfigurationException("Can not load properties file " + FICHIER_PROPERTIES,
                    e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Driver is not found in classpath.", e);
        }

        DAOFactory instance = new DAOFactory(url, nomUtilisateur, motDePasse);
        return instance;
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public MemberDao getMemberDao() {
        return new MemberDaoImpl(this);
    }

    public TenderDao getTenderDao() {
        return new TenderDaoImpl(this);
    }

    public TenderToAssignDao getTenderToAssignDao() {
        return new TenderToAssignDaoImpl(this);
    }
}
