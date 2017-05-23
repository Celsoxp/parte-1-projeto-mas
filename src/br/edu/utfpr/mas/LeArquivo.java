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

    private static final String ESPACO = " ";       //#define do c

    /**
     * Lê o arquivo e faz a transformações necessárias, armazenado-as no
     * stringbuilder
     *
     * @param nomearquivo Noem do arquivo a ser lido
     * @return stringbuilder já tudo OK.
     */
    public StringBuilder leitura(String nomearquivo) throws ParseException {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomearquivo));
            String linha = br.readLine();
            int espaco = linha.indexOf(ESPACO);

            String nomeServidor = linha.substring(0, espaco);   //O nome deo servidor é sempre o mesmo para cada arquivo
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Calendar data = Calendar.getInstance();
	    data.setTime(sdf.parse(validadata(linha.substring(espaco + 1))));
	    
            linha = br.readLine();                              //Le a nova linha
            while (linha != null) {
                if (!linha.startsWith("procs") && !linha.startsWith(" r")) {      //Estas linhas sempre devem ser ignoradas
                    if (linha.startsWith(nomeServidor)) {
                        data.setTime(sdf.parse(validadata(linha.substring(linha.indexOf(ESPACO) + 1))));
                    } else {
                        sb.append(nomeServidor).append(" ").append(sdf.format(data.getTime())).append(linha).append("\n");
			data.add(Calendar.SECOND, 15);
                    }
                }
                linha = br.readLine();  //Le a proxima linha
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.print(sb);
        return sb;
    }

    /**
     * Para verifiar se realmente é um data
     *
     * @param dia o dia lido do arquivo
     * @return String novamente, mas validada pois realmente é uma data Acho que
     * não precisa retransformar em String, mas ssim funcionou
     */
    private String validadata(String data) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date resultado = null;
        try {
            resultado = df.parse(data);      //valida a data e transforma o tipo para data
        } catch (ParseException ex) {
            Logger.getLogger(LeArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return df.format(resultado);            //Retorna a data já transformada em string novamente
    }
}
