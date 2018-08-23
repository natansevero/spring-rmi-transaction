
package com.natansevero.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.natansevero.shared.SharedService;

/**
 *
 * @author natan
 */
@SpringBootApplication
public class RmiServer {
    
    @Bean
    SharedService sharedService() {
        return new SharedServiceImpl();
    }
    
    @Bean
    RmiServiceExporter exporter(SharedService implementation) {
        Class<SharedService> serviceInterface = SharedService.class;
        
        RmiServiceExporter exporter = new RmiServiceExporter();
        
        exporter.setServiceInterface(serviceInterface);
        exporter.setService(implementation);
        exporter.setServiceName(serviceInterface.getSimpleName());
        exporter.setRegistryPort(10999);
        
        return exporter;
    }
    
    public static void main(String[] args) {
        //SpringApplication.run(RmiServer.class, args);
    	AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(RmiServer.class);
    }
}
