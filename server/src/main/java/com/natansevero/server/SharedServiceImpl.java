
package com.natansevero.server;

import com.natansevero.server.infra.ConnFactory;
import com.natansevero.shared.SharedService;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author natan
 */
public class SharedServiceImpl implements SharedService {

    @Autowired
    private ConnFactory conn;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertAll(List<String> datas) throws SQLException {
        for(String data: datas) {        
            String sql = "insert into server(hello) values (?)";
                
            PreparedStatement stmt = conn.getConnection().prepareCall(sql);
            stmt.setString(1, data);
                
            stmt.executeUpdate();
        }   
    }

    @Override
    public List<String> listAll() {
        String sql = "select hello from server";
        
        try {
            PreparedStatement stmt = conn.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            List<String> datas = new ArrayList<>();
            
            while(rs.next()) {
                datas.add(rs.getString(1));
            }
            
            return datas;
            
        } catch (SQLException ex) {
            Logger.getLogger(SharedServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return Collections.EMPTY_LIST;
    }
    
}
