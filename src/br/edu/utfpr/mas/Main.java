/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.mas;

import java.text.ParseException;

/**
 *
 * @author ronaldo
 */
public class Main {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {

        LeArquivo leArquivo = new LeArquivo();
        GravaArquivo gravaArquivo = new GravaArquivo();
        //String nomeArquivo = "./uncompress/yogafire_stats-20170327.txt";
        
        String nomeArquivo = args[0];                                           //Chama o arquivo da linha de comando
        StringBuilder sb = leArquivo.leitura(nomeArquivo);                      //Para ler o aqruivo e criar o stringbuilder
        
        //nomeArquivo = nomeArquivo.substring(nomeArquivo.lastIndexOf("/") + 1);
        //nomeArquivo = nomeArquivo.substring(0, nomeArquivo.indexOf("."));
        
        nomeArquivo = nomeArquivo.replace("uncompress", "renomeados");          //Caminho doo diretório + nome do arquivo
        nomeArquivo = nomeArquivo.substring(0, nomeArquivo.lastIndexOf("."));   //Para remover a extensão
        gravaArquivo.grava(nomeArquivo, sb);                                    //PAra gravar o arquivo
    }

}
