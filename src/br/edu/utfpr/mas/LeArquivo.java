/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.mas;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public class LeArquivo {

    private static final String SPACE = " ";       //#define do c
    private static Calendar calendar = Calendar.getInstance();

    /**
     * Lê o arquivo e faz a transformações necessárias, armazenado-as no
     * stringbuilder
     *
     * @param fileName Noem do arquivo a ser lido
     * @return stringbuilder já tudo OK.
     */
    public void leitura(String fileName, GravaArquivo g, ConexaoBD c) throws ParseException {
        try {
            BufferedReader buffer = new BufferedReader(new FileReader(fileName));
            String line = buffer.readLine();
	    int totalLinhas = 0;
	    Dados  d = null;
	    
	    String sql = "insert into Dados " +
			 "(Servidor,Data,Tempo,Ano,Mes,Dia,rMax,rMedia,cpuMax,cpuMedia,swpMex,swpMedia,freeMax,freeMedia,bffMax,bffMedia,cacheMax,cacheMedia)" +
			 " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";   
            PreparedStatement  pstm= c.preparaStatement(sql);
            
	    int space = line.indexOf(SPACE);
            String serverName = line.substring(0, space);
	   
            Date lineDate;
            Time lineTime;
            int totalSecond = 0;
            Calendar lineCalendar = Calendar.getInstance();

            while (true) {
		Scanner in = new Scanner(line).useDelimiter("[^0-9]+");
                if (line != null) {
                    lineDate = new GregorianCalendar(in.nextInt(), in.nextInt(), in.nextInt()).getTime();
		    lineTime = new Time(in.nextInt(),in.nextInt(),in.nextInt());
		    // Adiciona no buffer as informações lidas.
		    d = new Dados(serverName, lineDate, lineTime, in.nextInt(), in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextFloat(),in.nextFloat(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
		    g.grava(d, pstm);
		}
		else {
		    break;
		}

		// Vai para a proxima linha
		line = buffer.readLine();
		totalLinhas++;
		System.out.println("Total de linhas lidas:" + totalLinhas);
	    }    
	} catch (FileNotFoundException ex) {
	    Logger.getLogger(LeArquivo.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(LeArquivo.class.getName()).log(Level.SEVERE, null, ex);
	} catch (SQLException ex) {
	    Logger.getLogger(LeArquivo.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    private Date getDate(String dateString, String format) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = dateFormat.parse(dateString);

        return date;
    }

    private String getTime(Date dateTime, String format) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(format);

        return dateFormat.format(dateTime);
    }

    private int getDay() {
        return this.calendar.get(Calendar.DAY_OF_MONTH);
    }

    private int getMonth() {
        return this.calendar.get(Calendar.MONTH) + 1; // janary start with 0
    }

    private int getYear() {
        return this.calendar.get(Calendar.YEAR);
    }

    private int getMax(int[] array) {
        return Arrays.stream(array).max().getAsInt();
    }

    private double getAvg(int[] array) {
        double total = 0;

        for (int i = 0; i < array.length; i++) {
            total += array[i];
        }

        return total / array.length;
    }
    
    private String formatDouble(double valor) {
        
        return String.format("%.2f", valor);
    }

}
