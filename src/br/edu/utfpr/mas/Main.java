/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.mas;

import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author ronaldo
 */
public class Main {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, SQLException {

        LeArquivo leArquivo = new LeArquivo();
        GravaArquivo grava = new GravaArquivo();
	ConexaoBD con = ConexaoBD.getInstance();
	con.conectar();
        //String nomeArquivo = "./uncompress/yogafire_stats-20170327.txt";
        
        String nomeArquivo = args[0];                                           //Chama o arquivo da linha de comando
        leArquivo.leitura(nomeArquivo, grava, con);                      //Para ler o aqruivo e criar o stringbuilder
    }

}
