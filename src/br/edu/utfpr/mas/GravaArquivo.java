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

/**
 *
 * @author ronaldo
 */
public class GravaArquivo {

    /**
     * Para gravar o arquivo .f01
     * @param nomeArquivo Nome + caminho do arquivo a ser gravado (sem extensão)
     * @param sb A stirng a ser gravada
     */
    public void grava(String nomeArquivo, StringBuilder sb) {
        nomeArquivo = nomeArquivo.concat(".f01");           //Concatena
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
            bw.write(sb.toString());
            bw.flush();
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(GravaArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
