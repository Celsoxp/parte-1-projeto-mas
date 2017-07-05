/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.mas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.*;
/**
 *
 * @author ronaldo
 */
public class GravaArquivo {

    /**
     * @param d
     * @param stmt
     */
    public void grava(Dados d, PreparedStatement pstm) {
        try {
	    pstm.setString(1, d.getServidor());
	    pstm.setDate(2, d.getData());
	    pstm.setTime(3, d.getTempo());
	    pstm.setInt(4, d.getDia());
	    pstm.setInt(5, d.getMes());
	    pstm.setInt(6, d.getAno());
	    pstm.setInt(7, d.getrMax());
	    pstm.setInt(8, d.getrMedia());
	    pstm.setFloat(9, d.getCpuMax());
	    pstm.setFloat(10, d.getCpuMedia());
	    pstm.setInt(11, d.getSwpMax());
	    pstm.setInt(12, d.getSwpMedia());
	    pstm.setInt(13, d.getFreeMax());
	    pstm.setInt(14, d.getFreeMedia());
	    pstm.setInt(15, d.getBffMax());
	    pstm.setInt(16, d.getBffMedia());
	    pstm.setInt(17, d.getCacheMax());
	    pstm.setInt(18, d.getCacheMedia());
	    
	    pstm.execute();
        } catch (SQLException ex) {
	    Logger.getLogger(GravaArquivo.class.getName()).log(Level.SEVERE, null, ex);
	}

    }

}
