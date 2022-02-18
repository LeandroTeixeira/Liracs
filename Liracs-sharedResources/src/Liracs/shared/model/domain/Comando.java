/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Liracs.shared.model.domain;

import java.io.Serializable;

/**
 *
 * @author Nelore
 */
public class Comando implements Serializable{
    private Long cod_Comando;
    private String des_Comando;
    private String end_Comando;
    
    public Comando(){}

    public Long getCod_Comando() {
        return cod_Comando;
    }

    public void setCod_Comando(Long cod_Comando) {
        this.cod_Comando = cod_Comando;
    }

    public String getDes_Comando() {
        return des_Comando;
    }

    public void setDes_Comando(String des_Comando) {
        this.des_Comando = des_Comando;
    }

    public String getEnd_Comando() {
        return end_Comando;
    }

    public void setEnd_Comando(String end_Comando) {
        this.end_Comando = end_Comando;
    }
    
}
