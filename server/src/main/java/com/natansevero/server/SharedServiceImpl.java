
package com.natansevero.server;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.transaction.annotation.Transactional;

import com.natansevero.shared.SharedService;

/**
 *
 * @author natan
 */
public class SharedServiceImpl implements SharedService {

    @Autowired
    private JdbcTemplate jdbc;
    
    @Override
    @Transactional(rollbackFor = SQLException.class)
    public void insertAll(List<String> datas) throws SQLException {
    	//
        for(String data: datas) {
        	try {
	            String sql = "insert into server(hello) values (?)";
	            int r = jdbc.execute(sql, new PreparedStatementCallback<Integer>() {
	            	@Override
	            	public Integer doInPreparedStatement(PreparedStatement stmt) throws SQLException, DataAccessException {
	            		stmt.setString(1, data);
	            		return stmt.executeUpdate();
	            	}
				});
	            System.out.println("Success: " + r);
        	} catch(DataAccessException e){
        		throw new SQLException(e);
        	}
        }   
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public List<String> listAll() throws SQLException {
        String sql = "select hello from server";
        
        try {
        	//
        	List<String> datas = jdbc.execute(sql, new PreparedStatementCallback<List<String>>() {
				@Override
				public List<String> doInPreparedStatement(PreparedStatement stmt)
						throws SQLException, DataAccessException {
					//
					ResultSet rs = stmt.executeQuery();
					//
					List<String> datas = new ArrayList<>();
		            //
		            while(rs.next()) {
		                datas.add(rs.getString(1));
		            }
		            //
		            return datas;
				}
        		
			});
        	//
            System.out.println("--> total da lista: " + datas.size());
            return datas;
        } catch(DataAccessException e){
        	e.printStackTrace();
        	throw new SQLException(e);
        }
    }
    
}
