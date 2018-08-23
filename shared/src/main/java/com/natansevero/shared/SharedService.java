/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.shared;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author natan
 */
public interface SharedService {
    void insertAll(List<String> datas) throws SQLException;
    List<String> listAll();
}
