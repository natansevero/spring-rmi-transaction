/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.server.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author natan
 */
@Configuration
public class ConnFactory {
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:;"
                + "INIT=RUNSCRIPT FROM './src/main/resources/init.sql'\\;", "sa", "");
    }
}
