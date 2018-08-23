/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.client;

import com.natansevero.shared.SharedService;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 *
 * @author natan
 */
@SpringBootApplication
public class RmiClient {
    
    @Bean
    public RmiProxyFactoryBean service() {
        RmiProxyFactoryBean rmiProxyFactory = new RmiProxyFactoryBean();
        rmiProxyFactory.setServiceUrl("rmi://localhost:10999/SharedService");
        rmiProxyFactory.setServiceInterface(SharedService.class);
        return rmiProxyFactory;
    }
    
    public static void main(String[] args) {
        SharedService service = SpringApplication.run(RmiClient.class, args).getBean(SharedService.class);
        
        List<String> list1 = Arrays.asList("Natan", "Thidsdsdsdsdsds", "flufe");
        try {
            service.insertAll(list1);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(service.listAll());
        
//        List<String> list2 = Arrays.asList("Alann", "Ari");
//        try {
//            service.insertAll(list2);
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//        System.out.println(service.listAll());
    }
}
