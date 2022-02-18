/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.util.DB;

/**
 *
 * @author Nelore
 */

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

    public Connection getConnection() throws ClassNotFoundException, SQLException;
}
