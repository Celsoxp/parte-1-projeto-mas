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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public class LeArquivo {

    private static final String ESPACE = " ";       //#define do c
    private static Calendar calendar = Calendar.getInstance();

    /**
     * Lê o arquivo e faz a transformações necessárias, armazenado-as no
     * stringbuilder
     *
     * @param fileName Noem do arquivo a ser lido
     * @return stringbuilder já tudo OK.
     */
    public StringBuilder leitura(String fileName) throws ParseException {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(fileName));

            String line = buffer.readLine();
            int count = 1;
            while (line != null) {
                int espace = line.indexOf(ESPACE);
                String serverName = line.substring(0, espace);
                
                Date dateTime = getDate(line.substring(9, 28), "yyyy-MM-dd HH:mm:ss");
                
                calendar.setTime(dateTime);

                int day = getDay();
                int month = getMonth();
                int year = getYear();
                String time = getTime(dateTime, "HH:mm:ss");

                if (count == 60) {
                    //soma os totais de max e média.
                    //adiciona no buffer a linha resultante.
                    
                    count  = 1;
                }
                
                line = buffer.readLine();
                /* if (!linha.startsWith("procs") && !linha.startsWith(" r")) {      //Estas linhas sempre devem ser ignoradas
                    if (linha.startsWith(nomeServidor)) {
                        data.setTime(sdf.parse(validadata(linha.substring(linha.indexOf(ESPACO) + 1))));
                    } else {
                        sb.append(nomeServidor).append(" ").append(sdf.format(data.getTime())).append(linha).append("\n");
			data.add(Calendar.SECOND, 15);
                    }
                }
                linha = buffer.readLine();  //Le a proxima linha
                 */
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.print(sb);
        return sb;
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
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    
    private int getMonth() {
        return calendar.get(Calendar.MONTH) + 1; // janary start with 0
    }
    
    private int getYear() {
        return calendar.get(Calendar.YEAR);
    }
}
