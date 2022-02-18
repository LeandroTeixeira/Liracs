/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.util.DB;

import Liracs.util.DB.ConnectionFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Nelore
 */
public class PostgresqlConnection implements ConnectionFactory{
private final static String dbDriver = "org.postgresql.Driver";
    private final static String dbURL = "jdbc:postgresql://localhost:5433/postgres?gssEncMode=disable";
    private final static String user = "postgres";
    private final static String pass = "12345";

    public PostgresqlConnection() {
    }

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL, user, pass);
    }
    
    public static void main(String[] args) {
        try {
            ConnectionFactory cf = new PostgresqlConnection();            
            cf.getConnection();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PostgresqlConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
