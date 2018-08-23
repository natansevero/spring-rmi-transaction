/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.server.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author natan
 */
@Configuration
@EnableTransactionManagement
public class ConnFactory {
    
	@Bean
	@Scope(value="singleton")
	public DriverManagerDataSource getDataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:h2:file:./test.db;INIT=RUNSCRIPT FROM './src/main/resources/init.sql'\\;");
		ds.setUsername("sa");
		ds.setPassword("");
		return ds;
	}
	
	@Bean
	@Scope(value="singleton")
	public JdbcTemplate getJdbcTemplate(DriverManagerDataSource ds){
		return new JdbcTemplate(ds);
	}
	
	@Bean
	@Scope(value="singleton")
	public DataSourceTransactionManager geTransactionFactory(DriverManagerDataSource ds){
		DataSourceTransactionManager dstm = new DataSourceTransactionManager(ds);
		return dstm; 
	}
	
	
}
