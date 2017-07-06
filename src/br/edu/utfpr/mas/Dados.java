/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.mas;
import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Celso
 */
public class Dados {
    private String servidor;
    private Date data;
    private Time tempo;
    private int ano;
    private int mes;
    private int dia;
    private int rMax;
    private int rMedia;
    private float cpuMax;
    private float cpuMedia;
    private int swpMax;
    private int swpMedia;
    private int freeMax;
    private int freeMedia;
    private int bffMax;
    private int bffMedia;
    private int cacheMax;
    private int cacheMedia;
 
    public Dados(String servidor, Date data, Time tempo, int ano, int mes, int dia, int rMax, int rMedia, float cpuMax, float cpuMedia, int swpMax, int swpMedia, int freeMax, int freeMedia, int bffMax, int bffMedia, int cacheMax, int cacheMedia) {
	this.servidor = servidor;
	this.tempo=tempo;
	this.data = data;
	this.ano = ano;
	this.mes = mes;
	this.dia = dia;
	this.rMax = rMax;
	this.rMedia = rMedia;
	this.cpuMax = cpuMax;
	this.cpuMedia = cpuMedia;
	this.swpMax = swpMax;
	this.swpMedia = swpMedia;
	this.freeMax = freeMax;
	this.freeMedia = freeMedia;
	this.bffMax = bffMax;
	this.bffMedia = bffMedia;
	this.cacheMax = cacheMax;
	this.cacheMedia = cacheMedia;
    }

    /**
     * @return the servidor
     */
    public String getServidor() {
	return servidor;
    }

    /**
     * @return the data
     */
    public java.sql.Date getData() {
	return new java.sql.Date(data.getTime());
    }   
    /**
     * @return the tempo
     */
    public Time getTempo() {
	return tempo;
    }

    /**
     * @return the ano
     */
    public int getAno() {
	return ano;
    }

    /**
     * @return the mes
     */
    public int getMes() {
	return mes;
    }

    /**
     * @return the dia
     */
    public int getDia() {
	return dia;
    }

    /**
     * @return the rMax
     */
    public int getrMax() {
	return rMax;
    }

    /**
     * @return the rMedia
     */
    public int getrMedia() {
	return rMedia;
    }

    /**
     * @return the cpuMax
     */
    public float getCpuMax() {
	return cpuMax;
    }

    /**
     * @return the cpuMedia
     */
    public float getCpuMedia() {
	return cpuMedia;
    }

    /**
     * @return the swpMax
     */
    public int getSwpMax() {
	return swpMax;
    }

    /**
     * @return the swpMedia
     */
    public int getSwpMedia() {
	return swpMedia;
    }

    /**
     * @return the freeMax
     */
    public int getFreeMax() {
	return freeMax;
    }

    /**
     * @return the freeMedia
     */
    public int getFreeMedia() {
	return freeMedia;
    }

    /**
     * @return the bffMax
     */
    public int getBffMax() {
	return bffMax;
    }

    /**
     * @return the bffMedia
     */
    public int getBffMedia() {
	return bffMedia;
    }

    /**
     * @return the cacheMax
     */
    public int getCacheMax() {
	return cacheMax;
    }

    /**
     * @return the cacheMedia
     */
    public int getCacheMedia() {
	return cacheMedia;
    }
    
}
