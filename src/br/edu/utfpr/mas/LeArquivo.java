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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
    public StringBuilder leitura(String fileName) throws ParseException {
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader buffer = new BufferedReader(new FileReader(fileName));

            String line = buffer.readLine();
            int count = 0;
            int max = 0;
            double avg = 0;
            boolean setFirstTime = false;
            Date dateTime;
            String time, strDateTime = "";

            int[] r = new int[60];
            int[] cpu = new int[60];
            int[] swp = new int[60];
            int[] free = new int[60];
            int[] bff = new int[60];
            int[] cache = new int[60];

            dateTime = getDate(line.substring(9, 28), "yyyy-MM-dd HH:mm:ss");
            this.calendar.setTime(dateTime);
            time = getTime(dateTime, "HH:mm:ss");
            strDateTime = getTime(dateTime, "yyyy-MM-dd HH:mm:ss");
            
            while (line != null) {
                int space = line.indexOf(SPACE);
                String serverName = line.substring(0, space);

                r[count] = Integer.parseInt(line.substring(29, 30));
                swp[count] = Integer.parseInt(line.substring(36, 40));
                free[count] = Integer.parseInt(line.substring(41, 48));
                bff[count] = Integer.parseInt(line.substring(49, 55));
                cache[count] = Integer.parseInt(line.substring(56, 62));
                cpu[count] = 100 - (Integer.parseInt(line.substring(101, 103)));

                if (count == 59) {
                    sb.append(serverName).append(SPACE);

                    if (!setFirstTime) {
                        setFirstTime = true;
                    } else {
                        this.calendar.add(Calendar.MINUTE, 15);
                        
                        strDateTime = getTime(this.calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
                        
                        
                    }
                    
                    sb.append(strDateTime)
                      .append(SPACE);
                    
                    sb.append(getDay())
                      .append(SPACE)
                      .append(getMonth())
                      .append(SPACE)
                      .append(getYear())
                      .append(SPACE);
                              
                    sb.append(formatDouble(getMax(r)))
                      .append(SPACE)
                      .append(formatDouble(getAvg(r)))
                      .append(SPACE);
                    
                    sb.append(formatDouble(getMax(cpu)))
                      .append(SPACE)
                      .append(formatDouble(getAvg(cpu)))
                      .append(SPACE);
                    
                    sb.append(formatDouble(getMax(swp)))
                      .append(SPACE)
                      .append(formatDouble(getAvg(swp)))
                      .append(SPACE);
                    
                    sb.append(formatDouble(getMax(free)))
                      .append(SPACE)
                      .append(formatDouble(getAvg(free)))
                      .append(SPACE);
                    
                    sb.append(formatDouble(getMax(bff)))
                      .append(SPACE)
                      .append(formatDouble(getAvg(bff)))
                      .append(SPACE);
                    
                    sb.append(formatDouble(getMax(cache)))
                      .append(SPACE)
                      .append(formatDouble(getAvg(cache)))
                      .append(SPACE)
                      .append("\n");
                    
                    count = 1;
                }
                
                count++;
                line = buffer.readLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
